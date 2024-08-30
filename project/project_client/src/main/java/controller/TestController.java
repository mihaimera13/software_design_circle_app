package controller;

import model.language.Language;
import model.quiz.Question;
import model.quiz.Test;
import view.views.TestView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Arrays;

public class TestController implements ActionListener {

    private TestView testView;

    private int index;
    private int points;
    private Test test;
    private Language language;

    public TestController(TestView testView) {
        language = new Language(testView.getCanvasView().getClient());
        this.testView = testView;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getActionCommand().equals("START")){
            start();
        }
        else if(e.getActionCommand().equals("NEXT")){
            next();
        }
        else if(e.getActionCommand().equals("END")) {
            end();
        }
    }

    public void start(){
        index = 0;
        points = 0;
        test = new Test(testView.getCanvasView().getClient());
        next();
    }

    public void next(){
        if(index!=0){
            int selection = checkQuestion();
            if(selection == -1) {
                JOptionPane.showMessageDialog(testView,language.getOptionNotSelected(),
                        language.getTitleError(),JOptionPane.ERROR_MESSAGE);
                index--;
            }
            else{
                if(selection==test.getCorrectAnswers().get(index-1)){
                    points += test.getQuestions().get(index-1).getDifficulty().value();
                }
            }
        }
        Question q = test.getQuestions().get(index);
        if(q.getImageFile() == null){
            testView.fillPanel(q.getText(),q.getOptions());
            if(index!=9){
                testView.getEndTest().setVisible(false);
                testView.getNextButton().setVisible(true);
            }
            else{
                testView.getEndTest().setVisible(true);
                testView.getNextButton().setVisible(false);
            }
        }
        else{

            testView.getCanvasView().getClient().sendMessage("GET_IMAGE " + q.getImageFile());
            String result = testView.getCanvasView().getClient().receiveMessage();
            Byte[] image;

            String[] parts = result.replace("[","").replace("]","").split(", ");

            image = Arrays.stream(parts).map(Byte::parseByte).toArray(Byte[]::new);

            byte[] bytes = new byte[image.length];

            for(int i=0;i<image.length;i++)
                bytes[i] = image[i];

            ImageIcon img = new ImageIcon(bytes);

            testView.fillPanel(q.getText(),q.getOptions(),img);
            if(index!=9){
                testView.getEndTest().setVisible(false);
                testView.getNextButton().setVisible(true);
            }
            else{
                testView.getEndTest().setVisible(true);
                testView.getNextButton().setVisible(false);
            }
        }
        index++;

        testView.getOption1().setSelected(false);
        testView.getOption2().setSelected(false);
        testView.getOption3().setSelected(false);
        testView.getOption4().setSelected(false);
    }

    private int checkQuestion(){
        int selection = -1;
        if(testView.getOption1().isSelected())
            selection = 0;
        else if(testView.getOption2().isSelected())
            selection = 1;
        else if(testView.getOption3().isSelected())
            selection = 2;
        else if(testView.getOption4().isSelected())
            selection = 3;

        return selection;
    }

    public void end(){
        int selection = checkQuestion();
        if(selection == -1) {
            JOptionPane.showMessageDialog(testView,language.getOptionNotSelected(),
                    language.getTitleError(),JOptionPane.ERROR_MESSAGE);
            index--;
        }
        else{
            if(selection==test.getCorrectAnswers().get(index-1)){
                points += test.getQuestions().get(index-1).getDifficulty().value();
            }

            String resultMessage = String.format(language.getResult(),points,test.getMAXIMUM_POINTS());

            JOptionPane.showMessageDialog(testView,resultMessage,
                    language.getResults(),JOptionPane.INFORMATION_MESSAGE);
            testView.dispose();

            testView.getCanvasView().getClient().sendMessage("INSERT_TEST " + testView.getSid() + " " + points + " " + new Timestamp(System.currentTimeMillis()));
        }

    }
}

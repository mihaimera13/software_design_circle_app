package presenter;

import model.quiz.Question;
import model.quiz.Test;
import repo.DatabaseCreator;
import repo.TestPersistence;
import view.views.TestView;

import javax.swing.*;
import java.sql.Timestamp;

public class TestPresenter {

    private TestView testView;
    private Test test;
    private int index;
    private int points;

    public TestPresenter(TestView tv){
        DatabaseCreator db = new DatabaseCreator();
        db.createTestsTable();
        db.fillTableQuestions("questions.txt");
        testView = tv;
    }

    public void start(){
        index = 0;
        points = 0;
        test = new Test();
        next();
    }

    public void next(){
        if(index!=0){
            int selection = checkQuestion();
            if(selection == -1) {
                JOptionPane.showMessageDialog(testView,"Please select one option to continue!",
                        "ERROR",JOptionPane.ERROR_MESSAGE);
                index--;
            }
            else{
                if(selection==test.getCorrectAnswers().get(index-1))
                    points += test.getQuestions().get(index-1).getDifficulty().value();
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
            ImageIcon img = new ImageIcon(q.getImageFile());
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
    }

    public void end(){
        int selection = checkQuestion();
        if(selection == -1) {
            JOptionPane.showMessageDialog(testView,"Please select one option to continue!",
                    "ERROR",JOptionPane.ERROR_MESSAGE);
            index--;
        }
        else{
            if(selection==test.getCorrectAnswers().get(index-1))
                points += test.getQuestions().get(index-1).getDifficulty().value();
            JOptionPane.showMessageDialog(testView,"Your final result is " + points + " out of " + test.getMAXIMUM_POINTS() + ".",
                    "Results",JOptionPane.INFORMATION_MESSAGE);

            TestPersistence tp = new TestPersistence();
            tp.insertTest(testView.getSid(), points, new Timestamp(System.currentTimeMillis()));
            testView.dispose();

        }
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
}

package viewmodel.commands.test;

import model.quiz.Question;
import view.views.TestView;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMTest;

import javax.swing.*;

public class CommandNext implements ICommand {

    private VMTest vmTest;

    public CommandNext(VMTest vmTest){
        this.vmTest = vmTest;
    }

    @Override
    public void execute() {
        TestView testView = vmTest.getTestView();
        if(vmTest.getIndex()!=0){
            int selection = checkQuestion();
            if(selection == -1) {
                JOptionPane.showMessageDialog(testView,"Please select one option to continue!",
                        "ERROR",JOptionPane.ERROR_MESSAGE);
                vmTest.setIndex(vmTest.getIndex()-1);
            }
            else{
                if(selection==vmTest.getTest().getCorrectAnswers().get(vmTest.getIndex()-1)){
                    vmTest.setPoints(vmTest.getPoints()+vmTest.getTest().getQuestions().get(vmTest.getIndex()-1).getDifficulty().value());
                }
            }
        }
        Question q = vmTest.getTest().getQuestions().get(vmTest.getIndex());
        if(q.getImageFile() == null){
            testView.fillPanel(q.getText(),q.getOptions());
            if(vmTest.getIndex()!=9){
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
            if(vmTest.getIndex()!=9){
                testView.getEndTest().setVisible(false);
                testView.getNextButton().setVisible(true);
            }
            else{
                testView.getEndTest().setVisible(true);
                testView.getNextButton().setVisible(false);
            }
        }
        vmTest.setIndex(vmTest.getIndex()+1);

        vmTest.setOption1(false);
        vmTest.setOption2(false);
        vmTest.setOption3(false);
        vmTest.setOption4(false);
    }

    private int checkQuestion(){
        int selection = -1;
        if(vmTest.getOption1())
            selection = 0;
        else if(vmTest.getOption2())
            selection = 1;
        else if(vmTest.getOption3())
            selection = 2;
        else if(vmTest.getOption4())
            selection = 3;

        return selection;
    }
}

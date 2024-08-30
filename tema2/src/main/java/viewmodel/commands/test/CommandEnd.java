package viewmodel.commands.test;

import model.quiz.Test;
import repo.TestPersistence;
import view.views.TestView;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMTest;

import javax.swing.*;
import java.sql.Timestamp;

public class CommandEnd implements ICommand {

    private VMTest vmTest;

    public CommandEnd(VMTest vmTest) {
        this.vmTest = vmTest;
    }

    @Override
    public void execute() {
        TestView testView = vmTest.getTestView();
        Test test = vmTest.getTest();
        int selection = checkQuestion();
        if(selection == -1) {
            JOptionPane.showMessageDialog(testView,"Please select one option to continue!",
                    "ERROR",JOptionPane.ERROR_MESSAGE);
            vmTest.setIndex(vmTest.getIndex()-1);
        }
        else{
            if(selection==test.getCorrectAnswers().get(vmTest.getIndex()-1))
                vmTest.setPoints(vmTest.getPoints()+test.getQuestions().get(vmTest.getIndex()-1).getDifficulty().value());
            JOptionPane.showMessageDialog(testView,"Your final result is " + vmTest.getPoints() + " out of " + test.getMAXIMUM_POINTS() + ".",
                    "Results",JOptionPane.INFORMATION_MESSAGE);

            TestPersistence tp = new TestPersistence();
            tp.insertTest(testView.getSid(), vmTest.getPoints(), new Timestamp(System.currentTimeMillis()));
            testView.dispose();
        }
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

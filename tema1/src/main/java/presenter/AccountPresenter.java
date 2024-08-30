package presenter;

import model.quiz.TestTableEntry;
import repo.StudentPersistence;
import repo.TestPersistence;
import view.views.AccountView;
import view.views.TestView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AccountPresenter {

    private AccountView accountView;

    public AccountPresenter(AccountView av){
        accountView = av;
    }

    public DefaultTableModel retrieveStudentTests(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Index");
        model.addColumn("Name");
        model.addColumn("Surname");
        model.addColumn("Points");
        model.addColumn("Timestamp");

        StudentPersistence studentPersistence = new StudentPersistence();
        int id = studentPersistence.findIdByNickname(accountView.getNicknameSLabel().getText());

        TestPersistence testPersistence = new TestPersistence();
        ArrayList<TestTableEntry> entries = testPersistence.findTestsByStudent(id);

        for(TestTableEntry e : entries){
            model.addRow(new Object[]{e.getIndex(),e.getName(),e.getSurname(),e.getPoints(),e.getTimestamp()});
        }

        return model;
    }

    public void takeTest(){
        StudentPersistence studentPersistence = new StudentPersistence();
        int id = studentPersistence.findIdByNickname(accountView.getNicknameSLabel().getText());
        accountView.setVisible(false);
        TestView tv = new TestView(id);
        tv.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                accountView.setVisible(true);
            }
        });
    }

    public void goBack(){
        accountView.getCanvasView().setVisible(true);
        accountView.dispose();
    }
}

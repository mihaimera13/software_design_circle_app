package presenter;

import model.user.AccountStatus;
import model.user.Student;
import repo.StudentPersistence;
import repo.TestPersistence;
import view.views.AdminView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class AdminPresenter {

    private AdminView adminView;
    private StudentPersistence studentPersistence = new StudentPersistence();

    public AdminPresenter(AdminView av){
        adminView = av;
    }

    public void approve(){
        String nickname = adminView.getNickname();
        if(nickname!=null){
            StudentPersistence sp = new StudentPersistence();
            if(sp.updateStudentStatus(sp.findIdByNickname(nickname)))
                JOptionPane.showMessageDialog(adminView,"Student added successfully!","DONE",JOptionPane.PLAIN_MESSAGE);
            else JOptionPane.showMessageDialog(adminView,"Something went wrong!","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(adminView,"No student selected!","ERROR",JOptionPane.ERROR_MESSAGE);
    }

    public DefaultTableModel seeAll(){
        ArrayList<Student> students = studentPersistence.findAllStudents();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Name");
        dtm.addColumn("Surname");
        dtm.addColumn("Nickname");
        dtm.addColumn("Account status");

        for(Student student : students)
            if(student.getAccountStatus()!= AccountStatus.ADMIN)
                dtm.addRow(new Object[]{student.getName(),student.getSurname(),student.getNickname(),student.getAccountStatus()});

        return dtm;
    }

    public DefaultTableModel seeRequests(){
        ArrayList<Student> students = studentPersistence.findStudentsByStatus(String.valueOf(AccountStatus.REQUESTED));
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Name");
        dtm.addColumn("Surname");
        dtm.addColumn("Nickname");
        dtm.addColumn("Account status");

        for(Student student : students)
            dtm.addRow(new Object[]{student.getName(),student.getSurname(),student.getNickname(),student.getAccountStatus()});

        return dtm;
    }

    public void delete(){
        String nickname = adminView.getNickname();
        if(nickname!=null){
            StudentPersistence sp = new StudentPersistence();
            if(sp.deleteStudent(sp.findIdByNickname(nickname))){
                TestPersistence tp = new TestPersistence();
                tp.deleteAllTestsOfStudent(sp.findIdByNickname(nickname));
                JOptionPane.showMessageDialog(adminView,"Student deleted successfully!","DONE",JOptionPane.PLAIN_MESSAGE);
            }
            else JOptionPane.showMessageDialog(adminView,"Something went wrong!","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(adminView,"No student selected!","ERROR",JOptionPane.ERROR_MESSAGE);
    }

    public void back(){
        adminView.getCanvasView().setVisible(true);
        adminView.dispose();
    }
}

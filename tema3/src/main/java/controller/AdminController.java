package controller;

import model.language.Language;
import model.user.AccountStatus;
import model.user.Student;
import repo.StudentPersistence;
import repo.TestPersistence;
import view.views.AdminView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class AdminController implements ActionListener, ListSelectionListener {

    private AdminView adminView;

    private Language language;

    private int selectedRow;

    public AdminController(AdminView adminView){
        this.adminView = adminView;
        language = new Language();
        selectedRow = -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "DELETE" -> delete();
            case "APPROVE" -> approve();
            case "SEE_ALL" -> seeAll();
            case "BACK" -> goBack();
            case "SEE_REQUESTS" -> seeRequests();
        }
    }

    private void delete(){
        int row = selectedRow;
        if(row!=-1){
            StudentPersistence sp = new StudentPersistence();
            DefaultTableModel table = (DefaultTableModel) adminView.getDisplayTable().getModel();
            String nickname = table.getValueAt(row,2).toString();

            TestPersistence tp = new TestPersistence();
            tp.deleteAllTestsOfStudent(sp.findIdByNickname(nickname));

            if(sp.deleteStudent(sp.findIdByNickname(nickname)))
                JOptionPane.showMessageDialog(adminView,language.getAdminAddSuccessful(),language.getDone(),JOptionPane.PLAIN_MESSAGE);
            else JOptionPane.showMessageDialog(adminView,language.getSomethingWrong(),language.getTitleError(),JOptionPane.ERROR_MESSAGE);
            seeAll();
        }
        else JOptionPane.showMessageDialog(adminView,language.getAdminNoStudent(),language.getTitleError(),JOptionPane.ERROR_MESSAGE);
    }

    private void approve(){
        int row = selectedRow;
        if(row!=-1){
            StudentPersistence sp = new StudentPersistence();
            DefaultTableModel table = (DefaultTableModel) adminView.getDisplayTable().getModel();
            String nickname = table.getValueAt(row,2).toString();
            if(sp.updateStudentStatus(sp.findIdByNickname(nickname)))
                JOptionPane.showMessageDialog(adminView,language.getAdminAddSuccessful(),language.getDone(),JOptionPane.PLAIN_MESSAGE);
            else JOptionPane.showMessageDialog(adminView,language.getSomethingWrong(),language.getTitleError(),JOptionPane.ERROR_MESSAGE);
            seeRequests();
        }
        else JOptionPane.showMessageDialog(adminView,language.getAdminNoStudent(),language.getTitleError(),JOptionPane.ERROR_MESSAGE);
    }

    private void seeAll(){
        StudentPersistence studentPersistence = new StudentPersistence();
        ArrayList<Student> students = studentPersistence.findAllStudents();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn(language.getName());
        dtm.addColumn(language.getSurname());
        dtm.addColumn(language.getNickname());
        dtm.addColumn(language.getAccountStatus());

        for(Student student : students)
            if(student.getAccountStatus()!= AccountStatus.ADMIN)
                dtm.addRow(new Object[]{student.getName(),student.getSurname(),student.getNickname(),student.getAccountStatus()});
        adminView.setModel(dtm);
    }

    private void seeRequests(){
        StudentPersistence studentPersistence = new StudentPersistence();
        ArrayList<Student> students = studentPersistence.findStudentsByStatus(String.valueOf(AccountStatus.REQUESTED));
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn(language.getName());
        dtm.addColumn(language.getSurname());
        dtm.addColumn(language.getNickname());
        dtm.addColumn(language.getAccountStatus());

        for(Student student : students)
            dtm.addRow(new Object[]{student.getName(),student.getSurname(),student.getNickname(),student.getAccountStatus()});

        adminView.setModel(dtm);
    }

    private void goBack(){
        adminView.getCanvasView().setVisible(true);
        adminView.dispose();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        this.selectedRow = adminView.getDisplayTable().getSelectedRow();
    }
}

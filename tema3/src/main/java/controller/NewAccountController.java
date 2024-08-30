package controller;

import model.language.Language;
import model.user.Student;
import repo.StudentPersistence;
import view.views.NewAccountView;

import javax.swing.*;
import java.awt.event.ActionListener;

public class NewAccountController implements ActionListener {

    private NewAccountView newAccountView;

    private Language language;

    public NewAccountController(NewAccountView newAccountView) {
        this.newAccountView = newAccountView;
        language = new Language();
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String action = e.getActionCommand();
        if ("REQUEST".equals(action)) {
            request();
        }
    }

    public void request() {
        String name = newAccountView.getNameField();
        String surname = newAccountView.getSurnameField();
        String nickname = newAccountView.getNicknameField();
        String password = newAccountView.getPasswordField();

        int id=-1;
        StudentPersistence sp = new StudentPersistence();

        if(nickname!=null)
            id = sp.findIdByNickname(nickname);

        if(id==-1){
            if(name != null && surname != null && !password.isEmpty()){
                sp.insertStudent(new Student(name,surname,nickname,password));
                JOptionPane.showMessageDialog(newAccountView,language.getRequestSent(),language.getRequestTitle(),JOptionPane.PLAIN_MESSAGE);
                newAccountView.getCanvasView().setVisible(true);
                newAccountView.dispose();
            }
            else JOptionPane.showMessageDialog(newAccountView,language.getEmptyFields(),language.getTitleError(),JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(newAccountView,language.getNameUsed(),language.getTitleError(),JOptionPane.ERROR_MESSAGE);
        }

        reset();
    }

    public void reset() {
        newAccountView.setNameField("");
        newAccountView.setSurnameField("");
        newAccountView.setNicknameField("");
        newAccountView.setPasswordField("");
    }
}

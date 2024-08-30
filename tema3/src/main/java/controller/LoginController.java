package controller;

import model.language.Language;
import model.user.AccountStatus;
import model.user.Student;
import repo.StudentPersistence;
import view.views.AccountView;
import view.views.AdminView;
import view.views.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private LoginView loginView;

    private Language language;

    public LoginController(LoginView loginView){
        this.loginView = loginView;
        language = new Language();
    }

    private void goBack(){
        loginView.getCanvasView().setVisible(true);
        loginView.dispose();
    }

    public void login(){
        String nickname = loginView.getNickname();
        String password = loginView.getPassword();

        if(nickname==null || password==null || nickname.isEmpty() || password.isEmpty()){
            loginView.getErrorLabel().setVisible(true);
            reset();
        }
        else{
            StudentPersistence sp = new StudentPersistence();
            Student student = sp.findStudentByNickname(nickname);
            if(student!=null) {
                if(student.getAccountStatus() == AccountStatus.ADMIN){

                    if(student.getPassword().equals(password)){
                        AdminView av = new AdminView(loginView.getCanvasView());
                        loginView.setVisible(false);
                        reset();
                        loginView.getCanvasView().setBarLabel(student.getNickname());
                    }
                    else{
                        loginView.getErrorLabel().setVisible(true);
                        reset();
                    }
                }
                else if(student.getAccountStatus() == AccountStatus.APPROVED){

                    if(student.getPassword().equals(password)){
                        AccountView av = new AccountView(student.getName(), student.getSurname(), student.getNickname(),loginView.getCanvasView());
                        loginView.setVisible(false);
                        loginView.getCanvasView().setBarLabel(student.getNickname());
                        reset();
                    }
                    else{
                        loginView.getErrorLabel().setVisible(true);
                        reset();
                    }
                }
                else JOptionPane.showMessageDialog(loginView,language.getAccountNotApproved(),language.getTitleError(),JOptionPane.ERROR_MESSAGE);
            }
            else{
                loginView.getErrorLabel().setVisible(true);
                reset();
            }
        }
    }

    public void reset(){
        loginView.setNicknameTextField("");
        loginView.setPasswordTextField("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String event = e.getActionCommand();
        switch (event) {
            case "BACK" -> goBack();
            case "LOGIN" -> login();
        }
    }
}

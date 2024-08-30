package presenter;


import model.user.AccountStatus;
import model.user.Student;
import repo.DatabaseCreator;
import repo.StudentPersistence;
import view.views.AccountView;
import view.views.AdminView;
import view.views.LoginView;

import javax.swing.*;

public class LoginPresenter {

    private LoginView loginView;
    private Student student;

    public LoginPresenter(LoginView lv){
        DatabaseCreator db = new DatabaseCreator();
        db.createTableStudent();
        this.loginView = lv;
    }

    public void login(){
        String name = loginView.getNicknameField();
        String password = loginView.getPasswordField();

        if(name==null || password==null || name.isEmpty() || password.isEmpty()){
            loginView.getErrorLabel().setVisible(true);
        }
        else{
            StudentPersistence sp = new StudentPersistence();
            student = sp.findStudentByNickname(name);
            if(student!=null) {
                if(student.getAccountStatus() == AccountStatus.ADMIN){
                    AdminView av = new AdminView(loginView.getCanvasView());
                    loginView.setVisible(false);
                    reset();
                    loginView.getCanvasView().setBarLabel(student.getNickname());
                }
                else if(student.getAccountStatus() == AccountStatus.APPROVED){
                    AccountView av = new AccountView(student.getName(), student.getSurname(), student.getNickname(),loginView.getCanvasView());
                    loginView.setVisible(false);
                    loginView.getCanvasView().setBarLabel(student.getNickname());
                    reset();
                }
                else JOptionPane.showMessageDialog(loginView,"Your account has not been approved yet.","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else{
                loginView.getErrorLabel().setVisible(true);
            }
        }
    }

    private void reset(){
        loginView.setNicknameTextField(null);
        loginView.setPasswordTextField(null);
    }

}

package viewmodel.commands.login;

import model.user.AccountStatus;
import model.user.Student;
import repo.StudentPersistence;
import view.views.AccountView;
import view.views.AdminView;
import view.views.LoginView;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMLogin;

import javax.swing.*;

public class CommandLogin implements ICommand {

    private VMLogin vmLogin;

    public CommandLogin(VMLogin vmLogin) {
        this.vmLogin = vmLogin;
    }

    @Override
    public void execute() {
        String nickname = vmLogin.getNickname();
        String password = vmLogin.getPassword();
        LoginView loginView = vmLogin.getLoginView();
        if(nickname==null || password==null || nickname.isEmpty() || password.isEmpty()){
            loginView.getErrorLabel().setVisible(true);
            vmLogin.reset.execute();
        }
        else{
            StudentPersistence sp = new StudentPersistence();
            Student student = sp.findStudentByNickname(nickname);
            if(student!=null) {
                if(student.getAccountStatus() == AccountStatus.ADMIN){
                    AdminView av = new AdminView(loginView.getCanvasView());
                    loginView.setVisible(false);
                    vmLogin.reset.execute();
                    loginView.getCanvasView().setBarLabel(student.getNickname());
                }
                else if(student.getAccountStatus() == AccountStatus.APPROVED){
                    AccountView av = new AccountView(student.getName(), student.getSurname(), student.getNickname(),loginView.getCanvasView());
                    loginView.setVisible(false);
                    loginView.getCanvasView().setBarLabel(student.getNickname());
                    vmLogin.reset.execute();
                }
                else JOptionPane.showMessageDialog(loginView,"Your account has not been approved yet.","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else{
                loginView.getErrorLabel().setVisible(true);
                vmLogin.reset.execute();
            }
        }
    }
}

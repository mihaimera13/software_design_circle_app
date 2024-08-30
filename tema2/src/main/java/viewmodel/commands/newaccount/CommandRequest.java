package viewmodel.commands.newaccount;

import model.user.Student;
import repo.StudentPersistence;
import view.views.NewAccountView;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMNewAccount;

import javax.swing.*;

public class CommandRequest implements ICommand {

    private VMNewAccount vmNewAccount;

    public CommandRequest(VMNewAccount vmNewAccount){
        this.vmNewAccount = vmNewAccount;
    }

    @Override
    public void execute() {
        String name = vmNewAccount.getName();
        String surname = vmNewAccount.getSurname();
        String nickname = vmNewAccount.getNickname();
        String password = vmNewAccount.getPassword();

        int id=-1;
        StudentPersistence sp = new StudentPersistence();

        NewAccountView newAccountView = vmNewAccount.getNewAccountView();

        if(nickname!=null)
            id = sp.findIdByNickname(nickname);

        if(id==-1){
            if(name != null && surname != null && !password.isEmpty()){
                sp.insertStudent(new Student(name,surname,nickname,password));
                JOptionPane.showMessageDialog(newAccountView,"Your request has been sent.","REQUEST",JOptionPane.PLAIN_MESSAGE);
                newAccountView.getCanvasView().setVisible(true);
                newAccountView.dispose();
            }
            else JOptionPane.showMessageDialog(newAccountView,"Please do not leave empty fields.","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(newAccountView,"This name is already used.","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        vmNewAccount.reset.execute();
    }
}

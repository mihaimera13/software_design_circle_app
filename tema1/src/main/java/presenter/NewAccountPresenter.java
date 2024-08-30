package presenter;

import model.user.Student;
import repo.StudentPersistence;
import view.views.NewAccountView;

import javax.swing.*;

public class NewAccountPresenter {

    private NewAccountView newAccountView;

    public NewAccountPresenter(NewAccountView newAccountView){
        this.newAccountView = newAccountView;
        this.newAccountView.getCanvasView().setVisible(false);
    }

    public void request(){
        String name = newAccountView.getNameField().getText();
        String surname = newAccountView.getSurnameField().getText();
        String nickname = newAccountView.getNicknameField().getText();
        String password = String.valueOf(newAccountView.getPasswordField().getPassword());

        int id=-1;
        StudentPersistence sp = new StudentPersistence();

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
        reset();
    }

    private void reset(){
        newAccountView.getNameField().setText("");
        newAccountView.getSurnameField().setText("");
        newAccountView.getNicknameField().setText("");
        newAccountView.getPasswordField().setText("");
    }
}

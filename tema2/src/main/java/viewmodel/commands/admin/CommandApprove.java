package viewmodel.commands.admin;

import repo.StudentPersistence;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CommandApprove implements ICommand {
    private VMAdmin vmAdmin;

    public CommandApprove(VMAdmin vmAdmin) {
        this.vmAdmin = vmAdmin;
    }

    @Override
    public void execute() {
        int row = vmAdmin.getRow();
        if(row!=-1){
            StudentPersistence sp = new StudentPersistence();
            DefaultTableModel table = vmAdmin.getModel();
            String nickname = table.getValueAt(row,2).toString();
            if(sp.updateStudentStatus(sp.findIdByNickname(nickname)))
                JOptionPane.showMessageDialog(vmAdmin.getAdminView(),"Student added successfully!","DONE",JOptionPane.PLAIN_MESSAGE);
            else JOptionPane.showMessageDialog(vmAdmin.getAdminView(),"Something went wrong!","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(vmAdmin.getAdminView(),"No student selected!","ERROR",JOptionPane.ERROR_MESSAGE);
    }
}

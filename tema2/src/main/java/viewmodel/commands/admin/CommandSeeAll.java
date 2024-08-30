package viewmodel.commands.admin;

import model.user.AccountStatus;
import model.user.Student;
import repo.StudentPersistence;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMAdmin;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class CommandSeeAll implements ICommand {

    private VMAdmin vmAdmin;

    public CommandSeeAll(VMAdmin vmAdmin) {
        this.vmAdmin = vmAdmin;
    }

    @Override
    public void execute() {
        StudentPersistence studentPersistence = new StudentPersistence();
        ArrayList<Student> students = studentPersistence.findAllStudents();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Name");
        dtm.addColumn("Surname");
        dtm.addColumn("Nickname");
        dtm.addColumn("Account status");

        for(Student student : students)
            if(student.getAccountStatus()!= AccountStatus.ADMIN)
                dtm.addRow(new Object[]{student.getName(),student.getSurname(),student.getNickname(),student.getAccountStatus()});
        vmAdmin.setModel(dtm);
    }
}

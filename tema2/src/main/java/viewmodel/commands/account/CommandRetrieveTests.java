package viewmodel.commands.account;

import model.quiz.TestTableEntry;
import repo.StudentPersistence;
import repo.TestPersistence;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMAccount;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class CommandRetrieveTests implements ICommand {

    private VMAccount vmAccount;

    public CommandRetrieveTests(VMAccount vmAccount) {
        this.vmAccount = vmAccount;
    }
    @Override
    public void execute() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Index");
        model.addColumn("Name");
        model.addColumn("Surname");
        model.addColumn("Points");
        model.addColumn("Timestamp");

        StudentPersistence studentPersistence = new StudentPersistence();
        int id = studentPersistence.findIdByNickname(vmAccount.getNickname());

        TestPersistence testPersistence = new TestPersistence();
        ArrayList<TestTableEntry> entries = testPersistence.findTestsByStudent(id);

        for(TestTableEntry e : entries){
            model.addRow(new Object[]{e.getIndex(),e.getName(),e.getSurname(),e.getPoints(),e.getTimestamp()});
        }

        vmAccount.setModel(model);
    }
}

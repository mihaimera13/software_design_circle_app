package viewmodel.commands.account;

import repo.StudentPersistence;
import view.views.TestView;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMAccount;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CommandTakeTest implements ICommand {

    private VMAccount vmAccount;

    public CommandTakeTest(VMAccount vmAccount) {
        this.vmAccount = vmAccount;
    }

    @Override
    public void execute() {
        StudentPersistence studentPersistence = new StudentPersistence();
        int id = studentPersistence.findIdByNickname(vmAccount.getNickname());
        vmAccount.getAccountView().setVisible(false);
        TestView tv = new TestView(id);

        tv.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                vmAccount.getAccountView().setVisible(true);
            }
        });
    }
}

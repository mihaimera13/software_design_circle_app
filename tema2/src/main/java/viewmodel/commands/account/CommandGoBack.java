package viewmodel.commands.account;

import view.views.AccountView;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMAccount;

public class CommandGoBack implements ICommand {
    private VMAccount vmAccount;

    public  CommandGoBack(VMAccount vmAccount) {
        this.vmAccount = vmAccount;
    }

    @Override
    public void execute() {
        AccountView accountView = vmAccount.getAccountView();
        accountView.getCanvasView().setVisible(true);
        accountView.dispose();
    }
}

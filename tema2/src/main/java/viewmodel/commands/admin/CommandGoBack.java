package viewmodel.commands.admin;

import view.views.AccountView;
import view.views.AdminView;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMAccount;
import viewmodel.viewmodels.VMAdmin;

public class CommandGoBack implements ICommand {
    private VMAdmin vmAdmin;

    public  CommandGoBack(VMAdmin vmAdmin) {
        this.vmAdmin = vmAdmin;
    }

    @Override
    public void execute() {
        AdminView adminView = vmAdmin.getAdminView();
        adminView.getCanvasView().setVisible(true);
        adminView.dispose();
    }
}


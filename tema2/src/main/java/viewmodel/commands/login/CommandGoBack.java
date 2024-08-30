package viewmodel.commands.login;

import view.views.LoginView;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMLogin;

public class CommandGoBack implements ICommand {

    private VMLogin vmLogin;

    public CommandGoBack(VMLogin vmLogin) {
        this.vmLogin = vmLogin;
    }
    @Override
    public void execute() {
        LoginView loginView = vmLogin.getLoginView();
        loginView.getCanvasView().setVisible(true);
        loginView.dispose();
    }
}

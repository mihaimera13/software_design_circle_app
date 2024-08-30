package viewmodel.commands.login;

import view.views.LoginView;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMLogin;

public class CommandReset implements ICommand {

    private VMLogin vmLogin;

    public CommandReset(VMLogin vmLogin) {
        this.vmLogin = vmLogin;
    }

    @Override
    public void execute() {
        LoginView loginView = vmLogin.getLoginView();
        loginView.setNicknameTextField(null);
        loginView.setPasswordTextField(null);
    }
}

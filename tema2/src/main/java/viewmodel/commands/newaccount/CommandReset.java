package viewmodel.commands.newaccount;

import view.views.NewAccountView;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMNewAccount;

public class CommandReset implements ICommand {

    private VMNewAccount vmNewAccount;

    public CommandReset(VMNewAccount vmNewAccount){
        this.vmNewAccount = vmNewAccount;
    }

    @Override
    public void execute() {
        NewAccountView newAccountView = vmNewAccount.getNewAccountView();
        newAccountView.getNameField().setText("");
        newAccountView.getSurnameField().setText("");
        newAccountView.getNicknameField().setText("");
        newAccountView.getPasswordField().setText("");
    }
}

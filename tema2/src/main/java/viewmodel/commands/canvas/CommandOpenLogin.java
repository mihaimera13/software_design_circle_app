package viewmodel.commands.canvas;

import view.views.LoginView;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMCanvas;

public class CommandOpenLogin implements ICommand {

    private VMCanvas vmCanvas;

    public CommandOpenLogin(VMCanvas vmCanvas){
        this.vmCanvas = vmCanvas;
    }
    @Override
    public void execute() {
        vmCanvas.getCanvas().setVisible(false);
        new LoginView(vmCanvas.getCanvas());
    }
}

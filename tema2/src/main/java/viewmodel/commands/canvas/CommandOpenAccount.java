package viewmodel.commands.canvas;

import view.views.NewAccountView;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMCanvas;

public class CommandOpenAccount implements ICommand {
    private VMCanvas vmCanvas;

    public CommandOpenAccount(VMCanvas vmCanvas){
        this.vmCanvas = vmCanvas;
    }
    @Override
    public void execute() {
        vmCanvas.getCanvas().setVisible(false);
        new NewAccountView(vmCanvas.getCanvas());
    }
}

package viewmodel.commands.canvas;

import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMCanvas;

public class CommandChangeColor implements ICommand {
    private VMCanvas vmCanvas;

    public CommandChangeColor(VMCanvas vmCanvas) {
        this.vmCanvas = vmCanvas;
    }

    @Override
    public void execute() {
        vmCanvas.getCanvas().getCanvasPanel().setColor(vmCanvas.getCanvas().getBrushColor());
    }
}

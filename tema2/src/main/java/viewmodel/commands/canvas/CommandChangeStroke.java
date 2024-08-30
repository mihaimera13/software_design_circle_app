package viewmodel.commands.canvas;

import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMCanvas;

import java.awt.*;

public class CommandChangeStroke implements ICommand {
    private VMCanvas vmCanvas;

    public CommandChangeStroke(VMCanvas vmCanvas) {
        this.vmCanvas = vmCanvas;
    }

    @Override
    public void execute() {
        Stroke stroke = new BasicStroke(vmCanvas.getCanvas().getSlider().getValue(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
        vmCanvas.getCanvas().getCanvasPanel().setStroke(stroke);
    }
}

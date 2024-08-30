package viewmodel.commands.canvas;

import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMCanvas;

import java.awt.*;

public class CommandChangePattern implements ICommand {

    private VMCanvas vmCanvas;

    public CommandChangePattern(VMCanvas vmCanvas) {
        this.vmCanvas = vmCanvas;
    }

    @Override
    public void execute() {
        float[] pattern = vmCanvas.getCanvas().getBrushDashing();
        Stroke stroke;
        if(pattern[0]==0)
            vmCanvas.changeStroke.execute();
        else{
            stroke = new BasicStroke(vmCanvas.getCanvas().getSlider().getValue(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, pattern, 0.0f);
            vmCanvas.getCanvas().getCanvasPanel().setStroke(stroke);
        }

    }
}

package viewmodel.commands.drawingcanvas;

import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMDrawingCanvas;

import java.awt.*;
import java.util.ArrayList;

public class CommandClear implements ICommand {

    private VMDrawingCanvas vmDrawingCanvas;

    public CommandClear(VMDrawingCanvas vmDrawingCanvas){
        this.vmDrawingCanvas = vmDrawingCanvas;
    }

    @Override
    public void execute() {
        vmDrawingCanvas.setGeometryObjects(new ArrayList<>());
        Graphics g = vmDrawingCanvas.getDrawingCanvas().getGraphics();
        g.setColor(vmDrawingCanvas.getDrawingCanvas().getBackground());
        g.fillRect(0, 0, vmDrawingCanvas.getDrawingCanvas().getWidth(), vmDrawingCanvas.getDrawingCanvas().getHeight());
    }
}

package viewmodel.commands.drawingcanvas;

import model.geometry.Point;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMDrawingCanvas;

public class CommandAddPoint implements ICommand {

    private VMDrawingCanvas vmDrawingCanvas;

    public CommandAddPoint(VMDrawingCanvas vmDrawingCanvas){
        this.vmDrawingCanvas = vmDrawingCanvas;
    }

    @Override
    public void execute() {
        int x = vmDrawingCanvas.getDrawingCanvas().getX();
        int y = vmDrawingCanvas.getDrawingCanvas().getY();
        Point point = new Point(x,y);
        vmDrawingCanvas.addGeometryObject(point);
    }
}

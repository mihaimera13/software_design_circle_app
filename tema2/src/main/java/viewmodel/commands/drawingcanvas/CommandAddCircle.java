package viewmodel.commands.drawingcanvas;

import model.geometry.Circle;
import model.geometry.Point;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMDrawingCanvas;

import java.util.ArrayList;

public class CommandAddCircle implements ICommand {
    private VMDrawingCanvas vmDrawingCanvas;

    public CommandAddCircle(VMDrawingCanvas vmDrawingCanvas) {
        this.vmDrawingCanvas = vmDrawingCanvas;
    }

    @Override
    public void execute() {
        int startX = vmDrawingCanvas.getDrawingCanvas().getStartX();
        int startY = vmDrawingCanvas.getDrawingCanvas().getStartY();
        int endX = vmDrawingCanvas.getDrawingCanvas().getEndX();
        int endY = vmDrawingCanvas.getDrawingCanvas().getEndY();

        int centerX = startX;
        int centerY = startY;

        float radius = (float) Math.sqrt(Math.pow(endX-startX,2)+Math.pow(endY-startY,2));

        Circle circle = new Circle(new Point(centerX,centerY),radius);
        vmDrawingCanvas.setGeometryObjects(new ArrayList<>());
        vmDrawingCanvas.addGeometryObject(circle);
        vmDrawingCanvas.getDrawingCanvas().setContainsCircle(true);
    }
}

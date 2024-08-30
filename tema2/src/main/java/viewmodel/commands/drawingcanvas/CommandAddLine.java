package viewmodel.commands.drawingcanvas;

import model.geometry.Line;
import model.geometry.Point;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMDrawingCanvas;

import java.awt.*;

public class CommandAddLine implements ICommand {
    private VMDrawingCanvas vmDrawingCanvas;

    public CommandAddLine(VMDrawingCanvas vmDrawingCanvas){
        this.vmDrawingCanvas = vmDrawingCanvas;
    }

    @Override
    public void execute() {
        int startX = vmDrawingCanvas.getDrawingCanvas().getStartX();
        int startY = vmDrawingCanvas.getDrawingCanvas().getStartY();
        int endX = vmDrawingCanvas.getDrawingCanvas().getEndX();
        int endY = vmDrawingCanvas.getDrawingCanvas().getEndY();
        Line line = new Line(new Point(startX,startY),new Point(endX,endY));
        Graphics g = vmDrawingCanvas.getDrawingCanvas().getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine((int) line.getStart().getX(), (int) line.getStart().getY(), (int) line.getEnd().getX(), (int) line.getEnd().getY());
        vmDrawingCanvas.addGeometryObject(line);
    }
}

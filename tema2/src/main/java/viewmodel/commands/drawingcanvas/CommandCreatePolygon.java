package viewmodel.commands.drawingcanvas;

import model.geometry.*;
import model.geometry.Point;
import model.geometry.Polygon;
import view.resources.DrawingCanvas;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMCanvas;
import viewmodel.viewmodels.VMDrawingCanvas;

import java.awt.*;
import java.util.ArrayList;

public class CommandCreatePolygon implements ICommand {
    private VMDrawingCanvas vmDrawingCanvas;

    public CommandCreatePolygon(VMDrawingCanvas vmDrawingCanvas){
        this.vmDrawingCanvas = vmDrawingCanvas;
    }
    @Override
    public void execute() {
        ArrayList<Point> points = new ArrayList<>();
        ArrayList<GeometryObject> objects = vmDrawingCanvas.getGeometryObjects();
        for(GeometryObject t : objects){
            if(t instanceof Point){
                points.add((Point) t);
            }
            else{
                vmDrawingCanvas.setGeometryObjects(new ArrayList<>());
            }
        }

        if(points.size()==2){
            Point p1 = points.get(0);
            Point p2 = points.get(1);
            addLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
            vmDrawingCanvas.setGeometryObjects(new ArrayList<>());

        }
        else if(points.size()>2){
            DrawingCanvas drawingCanvas = vmDrawingCanvas.getDrawingCanvas();
            Polygon pol = new Polygon(points);
            ArrayList<Line> lines = pol.transformToLines();
            Graphics g = drawingCanvas.getGraphics();
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(drawingCanvas.getColor());
            g2d.setStroke(drawingCanvas.getStroke());

            for(Line line : lines){
                g2d.drawLine((int) line.getStart().getX(), (int) line.getStart().getY(), (int) line.getEnd().getX(), (int) line.getEnd().getY());
            }
            drawingCanvas.setContainsPolygon(true);
            drawingCanvas.repaint();
            vmDrawingCanvas.setGeometryObjects(new ArrayList<>());
            vmDrawingCanvas.addGeometryObject(pol);
        }

    }

    private void addLine(int startX,int startY,int endX,int endY){
        Line line = new Line(new Point(startX,startY),new Point(endX,endY));
        Graphics g = vmDrawingCanvas.getDrawingCanvas().getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine((int) line.getStart().getX(), (int) line.getStart().getY(), (int) line.getEnd().getX(), (int) line.getEnd().getY());
        vmDrawingCanvas.addGeometryObject(line);
    }
}

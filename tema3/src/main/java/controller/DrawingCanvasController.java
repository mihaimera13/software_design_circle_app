package controller;

import model.geometry.Circle;
import model.geometry.GeometryObject;
import model.geometry.Line;
import model.geometry.Point;
import model.geometry.Polygon;
import view.resources.DrawingCanvas;

import java.awt.*;
import java.util.ArrayList;

public class DrawingCanvasController {

    private DrawingCanvas drawingCanvas;

    private ArrayList<GeometryObject> geometryObjects = new ArrayList<>();

    DrawingCanvasMouseController drawingCanvasMouseController;
    DrawingCanvasKeyController drawingCanvasKeyController;

    public DrawingCanvasController(DrawingCanvas drawingCanvas){
        this.drawingCanvas = drawingCanvas;
        drawingCanvasMouseController = new DrawingCanvasMouseController(drawingCanvas, this);
        drawingCanvasKeyController = new DrawingCanvasKeyController(drawingCanvas, this);
    }

    public ArrayList<GeometryObject> getGeometryObjects() {
        return geometryObjects;
    }

    public void setGeometryObjects(ArrayList<GeometryObject> geometryObjects) {
        this.geometryObjects = geometryObjects;
    }

    public void clear(){
        geometryObjects.clear();
        Graphics g = drawingCanvas.getGraphics();
        g.setColor(drawingCanvas.getBackground());
        g.fillRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
    }

    public void addGeometryObject(GeometryObject geometryObject){
        geometryObjects.add(geometryObject);
    }

    public void addPoint(){
        int x = drawingCanvas.getX();
        int y = drawingCanvas.getY();
        Point point = new Point(x,y);
        addGeometryObject(point);
    }

    public void addCircle(){
        int startX = drawingCanvas.getStartX();
        int startY = drawingCanvas.getStartY();
        int endX = drawingCanvas.getEndX();
        int endY = drawingCanvas.getEndY();

        int centerX = startX;
        int centerY = startY;

        float radius = (float) Math.sqrt(Math.pow(endX-startX,2)+Math.pow(endY-startY,2));

        Circle circle = new Circle(new Point(centerX,centerY),radius);
        setGeometryObjects(new ArrayList<>());
        addGeometryObject(circle);
        drawingCanvas.setContainsCircle(true);
    }

    public Circle getCircle(){
        for(GeometryObject t : geometryObjects){
            if(t instanceof Circle)
                return (Circle)t;
        }
        return null;
    }

    public Polygon getPolygon(){
        for(GeometryObject t : geometryObjects){
            if(t instanceof Polygon)
                return (Polygon)t;
        }
        return null;
    }


}

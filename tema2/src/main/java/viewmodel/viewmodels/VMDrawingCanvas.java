package viewmodel.viewmodels;

import model.geometry.Circle;
import model.geometry.GeometryObject;
import model.geometry.Polygon;
import view.resources.DrawingCanvas;
import viewmodel.commands.ICommand;
import viewmodel.commands.drawingcanvas.*;

import java.util.ArrayList;

public class VMDrawingCanvas {

    public ICommand addCircle;
    public ICommand addLine;
    public ICommand addPoint;
    public ICommand clear;
    public ICommand createPolygon;

    private static ArrayList<GeometryObject> geometryObjects = new ArrayList<>();

    private DrawingCanvas drawingCanvas;

    public VMDrawingCanvas(DrawingCanvas drawingCanvas) {
        addCircle = new CommandAddCircle(this);
        addLine = new CommandAddLine(this);
        addPoint = new CommandAddPoint(this);
        clear = new CommandClear(this);
        createPolygon = new CommandCreatePolygon(this);
        this.drawingCanvas = drawingCanvas;
    }

    public static ArrayList<GeometryObject> getGeometryObjects() {
        return geometryObjects;
    }

    public static void setGeometryObjects(ArrayList<GeometryObject> geometryObjects) {
        VMDrawingCanvas.geometryObjects = geometryObjects;
    }

    public static void addGeometryObject(GeometryObject geometryObject){
        geometryObjects.add(geometryObject);
    }

    public DrawingCanvas getDrawingCanvas() {
        return drawingCanvas;
    }

    public static Circle getCircle(){
        for(GeometryObject t : geometryObjects){
            if(t instanceof Circle)
                return (Circle)t;
        }
        return null;
    }

    public static Polygon getPolygon(){
        for (GeometryObject t: geometryObjects){
            if(t instanceof Polygon)
                return (Polygon) t;
        }
        return null;
    }
}

package presenter;

import model.geometry.*;
import model.geometry.Point;
import model.geometry.Polygon;
import view.resources.DrawingCanvas;

import java.awt.*;
import java.util.ArrayList;

public class DrawingCanvasPresenter {

    private ArrayList<GeometryObject> objects;
    private DrawingCanvas drawingCanvas;

    public DrawingCanvasPresenter(DrawingCanvas dc){
        this.drawingCanvas = dc;
        objects = new ArrayList<>();
    }

    public void addPoint(int x,int y){
        Point p = new Point(x,y);
        objects.add(p);
    }

    public void addLine(int startX,int startY,int endX,int endY){
        Line line = new Line(new Point(startX,startY),new Point(endX,endY));
        Graphics g = drawingCanvas.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine((int) line.getStart().getX(), (int) line.getStart().getY(), (int) line.getEnd().getX(), (int) line.getEnd().getY());
        objects.add(line);
    }

    public boolean createPolygon(){
        ArrayList<Point> points = new ArrayList<>();
        for(GeometryObject t : objects){
            if(t instanceof Point){
                points.add((Point) t);
            }
            else{
                objects.clear();
                return false;
            }
        }

        if(points.size()==2){
            Point p1 = points.get(0);
            Point p2 = points.get(1);
            addLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
            objects.clear();
            return false;
        }

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
        objects.clear();
        objects.add(pol);
        return true;
    }

    public void addCircle(float centerX,float centerY,float radius){
        Circle circle = new Circle(new Point(centerX,centerY),radius);
        objects.clear();
        objects.add(circle);
        drawingCanvas.setContainsCircle(true);
    }

    public void clear(){
        objects.clear();
        Graphics g = drawingCanvas.getGraphics();
        g.setColor(drawingCanvas.getBackground());
        g.fillRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
    }

    public Circle getCircle(){
        for(GeometryObject t : objects){
            if(t instanceof Circle)
                return (Circle)t;
        }

        return null;
    }

    public Polygon getPolygon(){
        for (GeometryObject t: objects){
            if(t instanceof Polygon)
                return (Polygon) t;
        }
        return null;
    }
}

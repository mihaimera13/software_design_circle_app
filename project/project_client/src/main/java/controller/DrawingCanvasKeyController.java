package controller;

import model.geometry.GeometryObject;
import model.geometry.Line;
import model.geometry.Point;
import model.geometry.Polygon;
import model.language.Language;
import view.resources.DrawingCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class DrawingCanvasKeyController extends KeyAdapter {

    private DrawingCanvas drawingCanvas;
    private DrawingCanvasController drawingCanvasController;
    private Language language;

    public DrawingCanvasKeyController(DrawingCanvas drawingCanvas, DrawingCanvasController drawingCanvasController){
        language = new Language();
        this.drawingCanvas = drawingCanvas;
        this.drawingCanvasController = drawingCanvasController;
        drawingCanvas.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.isShiftDown()){
            drawingCanvas.setPoint(false);
            drawingCanvas.setCircle(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!e.isShiftDown()){
            drawingCanvas.setPoint(true);
            drawingCanvas.setCircle(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e){
        char key = e.getKeyChar();
        if(key == 'p'){
            createPolygon();
            if(!drawingCanvas.getContainsPolygon()){
                displayError(language.getPolygonError());
            }
        }
    }

    public void displayError(String message){
        JOptionPane.showMessageDialog(drawingCanvas,message,language.getTitleError(),JOptionPane.ERROR_MESSAGE);
    }

    public void createPolygon(){
        ArrayList<Point> points = new ArrayList<>();
        ArrayList<GeometryObject> objects = drawingCanvasController.getGeometryObjects();
        for(GeometryObject t : objects){
            if(t instanceof Point){
                points.add((Point) t);
            }
            else{
                drawingCanvasController.setGeometryObjects(new ArrayList<>());
            }
        }

        if(points.size()==2){
            Point p1 = points.get(0);
            Point p2 = points.get(1);
            addLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
            drawingCanvasController.setGeometryObjects(new ArrayList<>());

        }
        else if(points.size()>2){
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
            drawingCanvasController.setGeometryObjects(new ArrayList<>());
            drawingCanvasController.addGeometryObject(pol);
        }
    }

    private void addLine(int startX,int startY,int endX,int endY){
        Line line = new Line(new Point(startX,startY),new Point(endX,endY));
        Graphics g = drawingCanvas.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine((int) line.getStart().getX(), (int) line.getStart().getY(), (int) line.getEnd().getX(), (int) line.getEnd().getY());
        drawingCanvasController.addGeometryObject(line);
    }
}

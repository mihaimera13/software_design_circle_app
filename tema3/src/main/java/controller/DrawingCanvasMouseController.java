package controller;

import view.resources.DrawingCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingCanvasMouseController extends MouseAdapter {

    private DrawingCanvas drawingCanvas;
    private DrawingCanvasController drawingCanvasController;

    public DrawingCanvasMouseController(DrawingCanvas drawingCanvas, DrawingCanvasController drawingCanvasController){
        this.drawingCanvas = drawingCanvas;
        this.drawingCanvasController = drawingCanvasController;
        drawingCanvas.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
        if(!drawingCanvas.getCircle())
        {
            drawingCanvas.setPoint(true);
            drawingCanvas.setX(e.getX());
            drawingCanvas.setY(e.getY());

            if(drawingCanvas.getContainsPolygon()){
                drawingCanvasController.clear();
                drawingCanvas.setContainsPolygon(false);
            }
            if(drawingCanvas.getContainsCircle()){
                drawingCanvasController.clear();
                drawingCanvas.setContainsCircle(false);
            }
            if(drawingCanvas.getX()!=-1 && drawingCanvas.getY()!=-1)
                drawingCanvas.repaint(drawingCanvas.getGraphics());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(drawingCanvas.getCircle()){
            drawingCanvas.setStartX(e.getX());
            drawingCanvas.setStartY(e.getY());
            drawingCanvas.setContainsCircle(false);
            drawingCanvas.setContainsPolygon(false);
            drawingCanvasController.clear();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(drawingCanvas.getCircle()){
            drawingCanvas.setEndX(e.getX());
            drawingCanvas.setEndY(e.getY());
            drawingCanvas.repaint(drawingCanvas.getGraphics());
        }
    }
}

package view.resources;
import presenter.DrawingCanvasPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingCanvas extends Canvas {
    private boolean point;
    private boolean circle;
    private boolean containsPolygon;
    private boolean containsCircle;
    private int x,y;
    private int startX,startY,endX,endY;

    private DrawingCanvasPresenter drawingCanvasPresenter;

    private Color color;
    private Stroke stroke;

    public DrawingCanvas(Color color,Stroke stroke){
        this.color = color;
        this.stroke = stroke;

        point = true;
        circle = false;
        containsPolygon = false;
        containsCircle = false;
        x=-1;
        y=-1;

        drawingCanvasPresenter = new DrawingCanvasPresenter(this);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!circle)
                {
                    point = true;
                    x = e.getX();
                    y = e.getY();
                    if(containsPolygon){
                        drawingCanvasPresenter.clear();
                        containsPolygon = false;
                    }
                    if(containsCircle){
                        drawingCanvasPresenter.clear();
                        containsCircle = false;
                    }
                    if(x!=-1 && y!=-1)
                        repaint(getGraphics());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(circle){
                    startX = e.getX();
                    startY = e.getY();
                    drawingCanvasPresenter.clear();
                    containsPolygon = containsCircle = false;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(circle){
                    endX = e.getX();
                    endY = e.getY();
                    repaint(getGraphics());
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.isShiftDown()){
                    point = false;
                    circle = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(!e.isShiftDown()){
                    circle = false;
                    point = true;
                }
            }

            @Override
            public void keyTyped(KeyEvent e){
                char key = e.getKeyChar();
                if(key == 'p'){
                    if(!drawingCanvasPresenter.createPolygon()){
                        displayError("The polygon cannot be created!");
                    };
                }
            }
        });
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(stroke);

        if(point && x!=-1 && y!=-1){
            g2d.drawOval(x,y,5,5);
            drawingCanvasPresenter.addPoint(x,y);
        }
        if(circle){
            float radius = (float)Math.sqrt((endX-startX)*(endX-startX)+(endY-startY)*(endY-startY));
            g2d.drawOval((int) (startX-radius), (int) (startY-radius), (int) (2*radius), (int) (2*radius));
            drawingCanvasPresenter.addCircle(startX-radius, startY-radius, radius);
        }
    }

    @Override
    public void update(Graphics g){

    }

    public void displayError(String message){
        JOptionPane.showMessageDialog(this,message,"ERROR",JOptionPane.ERROR_MESSAGE);
    }

    public void setContainsPolygon(boolean containsPolygon) {
        this.containsPolygon = containsPolygon;
    }

    public void setContainsCircle(boolean containsCircle) {
        this.containsCircle = containsCircle;
    }

    public void repaint(Graphics g){
        paint(g);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    public DrawingCanvasPresenter getDrawingCanvasPresenter() {
        return drawingCanvasPresenter;
    }
}
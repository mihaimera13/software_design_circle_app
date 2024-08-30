package view.resources;

import net.sds.mvvm.bindings.*;
import viewmodel.viewmodels.VMDrawingCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingCanvas extends Canvas {
    private boolean point;
    private boolean circle;
    private boolean containsPolygon;
    private boolean containsCircle;

    private VMDrawingCanvas vmDrawingCanvas;

    private int x;
    private int y;

    private int startX;
    private int startY;
    private int endX;
    private int endY;

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
        startX = startY = endX = endY = -1;

        vmDrawingCanvas = new VMDrawingCanvas(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!circle)
                {
                    point = true;
                    x = e.getX();
                    y = e.getY();
                    if(containsPolygon){
                        vmDrawingCanvas.clear.execute();
                        containsPolygon = false;
                    }
                    if(containsCircle){
                        vmDrawingCanvas.clear.execute();
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
                    vmDrawingCanvas.clear.execute();
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
                    vmDrawingCanvas.createPolygon.execute();
                    if(!containsPolygon){
                        displayError("The polygon cannot be created!");
                    }
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
            vmDrawingCanvas.addPoint.execute();
        }

        if(circle){
            float radius = (float)Math.sqrt((endX-startX)*(endX-startX)+(endY-startY)*(endY-startY));
            g2d.drawOval((int) (startX-radius), (int) (startY-radius), (int) (2*radius), (int) (2*radius));
            vmDrawingCanvas.addCircle.execute();
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

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getStartX(){
        return startX;
    }

    public int getStartY(){
        return startY;
    }

    public int getEndX(){
        return endX;
    }

    public int getEndY(){
        return endY;
    }

    public VMDrawingCanvas getVmDrawingCanvas() {
        return vmDrawingCanvas;
    }
}
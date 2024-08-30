package view.resources;

import controller.DrawingCanvasController;

import java.awt.*;

public class DrawingCanvas extends Canvas {
    private boolean point;
    private boolean circle;
    private boolean containsPolygon;
    private boolean containsCircle;

    private int x;
    private int y;

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    private Color color;
    private Stroke stroke;

    private DrawingCanvasController drawingCanvasController;

    public DrawingCanvas(Color color,Stroke stroke){

        drawingCanvasController = new DrawingCanvasController(this);
        this.color = color;
        this.stroke = stroke;

        point = true;
        circle = false;
        containsPolygon = false;
        containsCircle = false;
        x=-1;
        y=-1;
        startX = startY = endX = endY = -1;
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(stroke);

        if(point && x!=-1 && y!=-1){
            g2d.drawOval(x,y,5,5);
            drawingCanvasController.addPoint();
        }
        if(circle){
            float radius = (float)Math.sqrt((endX-startX)*(endX-startX)+(endY-startY)*(endY-startY));
            g2d.drawOval((int) (startX-radius), (int) (startY-radius), (int) (2*radius), (int) (2*radius));
            drawingCanvasController.addCircle();
        }
    }

    @Override
    public void update(Graphics g){

    }

    public boolean getCircle() {
        return circle;
    }

    public boolean getContainsPolygon() {
        return containsPolygon;
    }

    public boolean getContainsCircle() {
        return containsCircle;
    }

    public void setPoint(boolean point) {
        this.point = point;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setStartX(int x) {
        this.startX = x;
    }

    public void setStartY(int y) {
        this.startY = y;
    }

    public void setEndX(int x) {
        this.endX = x;
    }

    public void setEndY(int y) {
        this.endY = y;
    }

    public void setCircle(boolean b) {
        this.circle = b;
    }

    public DrawingCanvasController getDrawingCanvasController() {
        return drawingCanvasController;
    }
}
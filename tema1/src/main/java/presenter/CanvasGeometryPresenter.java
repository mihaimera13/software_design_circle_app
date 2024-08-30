package presenter;

import model.geometry.Circle;
import model.geometry.Point;
import model.geometry.Polygon;
import view.views.CanvasView;

import javax.swing.*;
import java.awt.*;

public class CanvasGeometryPresenter {

    private CanvasView canvasView;

    public CanvasGeometryPresenter(CanvasView cv){
        canvasView = cv;
    }

    public void changeColor(JButton button){
        canvasView.getCanvasPanel().setColor(button.getBackground());
    }

    public void changeStroke(){
        Stroke stroke = new BasicStroke(canvasView.getSlider().getValue(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
        canvasView.getCanvasPanel().setStroke(stroke);
    }

    public void changePattern(float[] pattern){
        Stroke stroke = new BasicStroke(canvasView.getSlider().getValue(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, pattern, 0.0f);
        canvasView.getCanvasPanel().setStroke(stroke);
    }

    public void displayCircleInfo(){
        Circle c = canvasView.getCanvasPanel().getDrawingCanvasPresenter().getCircle();
        if(c!=null){
            float area = c.computeArea();
            float perimeter = c.computePerimeter();
            String message = "Area = " + area +"\nPerimeter = " + perimeter;
            JOptionPane.showMessageDialog(canvasView.getCanvasPanel(),message,"Circle Info",JOptionPane.PLAIN_MESSAGE);
        }
        else JOptionPane.showMessageDialog(canvasView.getCanvasPanel(),"No circle on canvas","ERROR",JOptionPane.ERROR_MESSAGE);
    }

    public void drawCircumscribed(){
        Polygon p = canvasView.getCanvasPanel().getDrawingCanvasPresenter().getPolygon();
        if(p!=null){
            if(p.isCircumscribed()){
                Point cc = p.getCircumcenter();
                Graphics g = canvasView.getCanvasPanel().getGraphics();
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(canvasView.getCanvasPanel().getColor());
                g2d.setStroke(canvasView.getCanvasPanel().getStroke());
                Point rand = p.getVertices().get(0);
                float radius = (float)Math.sqrt((rand.getX()-cc.getX())*(rand.getX()-cc.getX())+(rand.getY()-cc.getY())*(rand.getY()-cc.getY()));
                g2d.drawOval((int) (cc.getX()-radius), (int) (cc.getY()-radius), (int) (2*radius), (int)(2* radius));
            }
            else JOptionPane.showMessageDialog(canvasView.getCanvasPanel(),"Polygon cannot be circumscribed","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(canvasView.getCanvasPanel(),"No polygon on canvas","ERROR",JOptionPane.ERROR_MESSAGE);
    }

    public void drawInscribed(){
        Polygon p = canvasView.getCanvasPanel().getDrawingCanvasPresenter().getPolygon();
        if(p!=null){
            if(p.isInscribed()){
                Point ic = p.getIncenter();
                Graphics g = canvasView.getCanvasPanel().getGraphics();
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(canvasView.getCanvasPanel().getColor());
                g2d.setStroke(canvasView.getCanvasPanel().getStroke());
                Point start = p.getVertices().get(0);
                Point end = p.getVertices().get(1);
                float a = start.getY() - end.getY();
                float b = end.getX() - start.getX();
                float c = start.getX()* end.getY() - start.getY()* end.getX();
                float radius = (float) (Math.abs(a*ic.getX()+b*ic.getY()+c)/Math.sqrt(a*a+b*b));
                g2d.drawOval((int) (ic.getX()-radius), (int) (ic.getY()-radius), (int) (2*radius), (int)(2*radius));
            }
            else JOptionPane.showMessageDialog(canvasView.getCanvasPanel(),"Polygon cannot be inscribed","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(canvasView.getCanvasPanel(),"No polygon on canvas","ERROR",JOptionPane.ERROR_MESSAGE);
    }

}

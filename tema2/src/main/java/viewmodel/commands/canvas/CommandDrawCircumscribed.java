package viewmodel.commands.canvas;

import model.geometry.Point;
import model.geometry.Polygon;
import view.views.CanvasView;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMCanvas;
import viewmodel.viewmodels.VMDrawingCanvas;

import javax.swing.*;
import java.awt.*;

public class CommandDrawCircumscribed implements ICommand {

    private VMCanvas vmCanvas;

    public CommandDrawCircumscribed(VMCanvas vmCanvas) {
        this.vmCanvas = vmCanvas;
    }
    @Override
    public void execute() {
        CanvasView canvasView = vmCanvas.getCanvas();
        Polygon p = VMDrawingCanvas.getPolygon();
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
}

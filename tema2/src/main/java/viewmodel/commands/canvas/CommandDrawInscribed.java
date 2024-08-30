package viewmodel.commands.canvas;

import model.geometry.Point;
import model.geometry.Polygon;
import view.views.CanvasView;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMCanvas;
import viewmodel.viewmodels.VMDrawingCanvas;

import javax.swing.*;
import java.awt.*;

public class CommandDrawInscribed implements ICommand {

    private VMCanvas vmCanvas;

    public CommandDrawInscribed(VMCanvas vmCanvas) {
        this.vmCanvas = vmCanvas;
    }

    @Override
    public void execute() {
        CanvasView canvasView = vmCanvas.getCanvas();
        Polygon p = VMDrawingCanvas.getPolygon();
        if(p!=null){
            if(p.isInscribed()){
                Point ic = p.getIncenter();
                Graphics g = canvasView.getCanvasPanel().getGraphics();
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(canvasView.getCanvasPanel().getColor());
                g2d.setStroke(canvasView.getCanvasPanel().getStroke());
                float perimeter = p.computePerimeter();
                float radius = (float) (2*p.computeAreaOfTriangle()/perimeter);
                g2d.drawOval((int) (ic.getX()-radius), (int) (ic.getY()-radius), (int) (2*radius), (int)(2*radius));

            }
            else JOptionPane.showMessageDialog(canvasView.getCanvasPanel(),"Polygon cannot be inscribed","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(canvasView.getCanvasPanel(),"No polygon on canvas","ERROR",JOptionPane.ERROR_MESSAGE);
    }
}

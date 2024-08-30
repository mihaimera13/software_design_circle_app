package viewmodel.commands.canvas;

import model.geometry.Circle;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMCanvas;
import viewmodel.viewmodels.VMDrawingCanvas;

import javax.swing.*;

public class CommandDisplayInformation implements ICommand {
    private VMCanvas vmCanvas;

    public CommandDisplayInformation(VMCanvas vmCanvas){
        this.vmCanvas = vmCanvas;
    }
    @Override
    public void execute() {
        Circle c = VMDrawingCanvas.getCircle();
        if(c!=null){
            float area = c.computeArea();
            float perimeter = c.computePerimeter();
            String message = "Area = " + area +"\nPerimeter = " + perimeter;
            JOptionPane.showMessageDialog(vmCanvas.getCanvas().getCanvasPanel(),message,"Circle Info",JOptionPane.PLAIN_MESSAGE);
        }
        else JOptionPane.showMessageDialog(vmCanvas.getCanvas().getCanvasPanel(),"No circle on canvas","ERROR",JOptionPane.ERROR_MESSAGE);
    }
}

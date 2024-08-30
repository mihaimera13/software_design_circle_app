package viewmodel.commands.canvas;

import model.geometry.Circle;
import model.geometry.Point;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMCanvas;
import viewmodel.viewmodels.VMDrawingCanvas;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CommandUploadXML implements ICommand {
    private VMCanvas vmCanvas;

    public CommandUploadXML(VMCanvas vmCanvas){
        this.vmCanvas = vmCanvas;
    }

    @Override
    public void execute() {
        File file = getFileFromFolder();
        if (file == null) {
            JOptionPane.showMessageDialog(null, "No file selected!");
            return;
        }
        else{
            VMDrawingCanvas vmdc = vmCanvas.getCanvas().getCanvasPanel().getVmDrawingCanvas();
            vmdc.clear.execute();
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(file);

                Element originElement = (Element) doc.getElementsByTagName("origin").item(0);
                float x = Float.parseFloat(originElement.getAttribute("x"));
                float y = Float.parseFloat(originElement.getAttribute("y"));
                Point origin = new Point(x, y);

                Element radiusElement = (Element) doc.getElementsByTagName("radius").item(0);
                float radius = Float.parseFloat(radiusElement.getTextContent());

                Circle circle = new Circle(origin, radius);
                VMDrawingCanvas.setGeometryObjects(new ArrayList<>());
                VMDrawingCanvas.addGeometryObject(circle);

                Graphics g = vmCanvas.getCanvas().getCanvasPanel().getGraphics();
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(vmCanvas.getCanvas().getCanvasPanel().getColor());
                g2d.setStroke(vmCanvas.getCanvas().getCanvasPanel().getStroke());
                g2d.drawOval((int) (origin.getX() - radius), (int) (origin.getY() - radius), (int) (2 * radius), (int) (2 * radius));
                vmCanvas.getCanvas().getCanvasPanel().repaint();

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
        }

    }

    private File getFileFromFolder() {
        JFileChooser fileChooser = new JFileChooser();
        File initialDirectory = new File("C:/Users/Mera/Documents/stick/facultate/an3_2/SD/tema2");
        fileChooser.setCurrentDirectory(initialDirectory);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }
}

package viewmodel.commands.canvas;

import model.geometry.Circle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import viewmodel.commands.ICommand;
import viewmodel.viewmodels.VMCanvas;
import viewmodel.viewmodels.VMDrawingCanvas;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalTime;

public class CommandDownloadXML implements ICommand {
    private VMCanvas vmCanvas;

    public CommandDownloadXML(VMCanvas vmCanvas){
        this.vmCanvas = vmCanvas;
    }

    @Override
    public void execute() {
        Circle circle = VMDrawingCanvas.getCircle();
        if(circle == null){
            JOptionPane.showMessageDialog(null, "No circle to save!");
        }
        else{
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("circle");
                doc.appendChild(rootElement);

                Element originElement = doc.createElement("origin");
                originElement.setAttribute("x", String.valueOf(circle.getOrigin().getX()));
                originElement.setAttribute("y", String.valueOf(circle.getOrigin().getY()));
                rootElement.appendChild(originElement);

                Element radiusElement = doc.createElement("radius");
                radiusElement.setTextContent(String.valueOf(circle.getRadius()));
                rootElement.appendChild(radiusElement);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                String naspa = LocalTime.now().toString();
                naspa = naspa.replace(":", "");
                naspa = naspa.replace(".", "");
                StreamResult result = new StreamResult(new File("circle" + naspa +".xml"));
                transformer.transform(source, result);

                JOptionPane.showMessageDialog(null, "Circle saved as XML file!");

            } catch (ParserConfigurationException | TransformerException e) {
                e.printStackTrace();
            }
        }

    }
}

package controller;

import model.geometry.Circle;
import model.geometry.Point;
import model.geometry.Polygon;
import model.language.Language;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import view.views.CanvasView;
import view.views.LoginView;
import view.views.NewAccountView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class CanvasController implements ActionListener, ChangeListener {

    private CanvasView canvasView;

    private Language language;

    public CanvasController(CanvasView canvasView){
        this.canvasView = canvasView;
        language = new Language();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        if(command.equals("CHANGE COLOR")){
           JButton colorSource = (JButton) e.getSource();
           changeColor(colorSource);
        }
        else if(command.contains("CHANGE DASHING")){
           JButton patternSource = (JButton) e.getSource();
           changePattern(patternSource);
        }
        else if(command.equals("LOGIN")){
            login();
        }
        else if(command.equals("ACCOUNT")){
            newAccount();
        }
        else if(command.equals("CIRCLE")){
            getCircleInfo();
        }
        else if(command.equals("CIRCUMSCRIBED")){
            circumscribed();
        }else if(command.equals("INSCRIBED")){
            inscribed();
        }
        else if(command.equals("UPLOAD")){
            upload();
        }
        else if(command.equals("DOWNLOAD")){
            download();
        }
    }

    public void circumscribed(){
        Polygon p = canvasView.getCanvasPanel().getDrawingCanvasController().getPolygon();
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
            else JOptionPane.showMessageDialog(canvasView.getCanvasPanel(),language.getPolygonNotCircumscribed(),language.getTitleError(),JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(canvasView.getCanvasPanel(),language.getPolygonNotExistent(),language.getTitleError(),JOptionPane.ERROR_MESSAGE);
    }

    public void inscribed(){
        Polygon p = canvasView.getCanvasPanel().getDrawingCanvasController().getPolygon();
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
            else JOptionPane.showMessageDialog(canvasView.getCanvasPanel(),language.getPolygonNotInscribed(),language.getTitleError(),JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(canvasView.getCanvasPanel(),language.getPolygonNotExistent(),language.getTitleError(),JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        changeStroke();
    }

    public void changeColor(JButton colorSource){
        canvasView.getCanvasPanel().setColor(colorSource.getBackground());
    }

    public void changePattern(JButton patternSource){
        float[] pattern = getBrushDashing(patternSource);
        Stroke stroke;
        if(pattern[0]==0)
        {
            stroke = new BasicStroke(canvasView.getSlider().getValue(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
            canvasView.getCanvasPanel().setStroke(stroke);
        }
        else{
            stroke = new BasicStroke(canvasView.getSlider().getValue(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, pattern, 0.0f);
            canvasView.getCanvasPanel().setStroke(stroke);
        }
    }

    public void changeStroke(){
        Stroke stroke = new BasicStroke(canvasView.getSlider().getValue(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
        canvasView.getCanvasPanel().setStroke(stroke);
    }

    private float[] getBrushDashing(JButton patternSource){
        String s = patternSource.getActionCommand();
        if(s.contains("1"))
            return new float[]{0};
        else if(s.contains("2"))
            return new float[]{6.0F,3.0F};
        else if(s.contains("3"))
            return new float[]{1.0F, 1.0F};
        else return new float[]{0};
    }

    public void getCircleInfo(){
        Circle c = canvasView.getCanvasPanel().getDrawingCanvasController().getCircle();
        if(c!=null){
            float area = c.computeArea();
            float perimeter = c.computePerimeter();
            String message = language.getArea() + "= " + area +"\n"+ language.getPerimeter() + "= " + perimeter;
            JOptionPane.showMessageDialog(canvasView.getCanvasPanel(),message,language.getDataTitle(),JOptionPane.PLAIN_MESSAGE);
        }
        else JOptionPane.showMessageDialog(canvasView.getCanvasPanel(),language.getPolygonNotExistent(),language.getTitleError(),JOptionPane.ERROR_MESSAGE);
    }

    public void download(){
        Circle circle = canvasView.getCanvasPanel().getDrawingCanvasController().getCircle();
        if(circle == null){
            JOptionPane.showMessageDialog(null, language.getErrorNoSave());
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

                JOptionPane.showMessageDialog(null,language.getCircleSaved());

            } catch (ParserConfigurationException | TransformerException e) {
                e.printStackTrace();
            }
        }
    }

    private File getFileFromFolder() {
        JFileChooser fileChooser = new JFileChooser();
        File initialDirectory = new File("C:/Users/Mera/Documents/stick/facultate/an3_2/SD/tema3");
        fileChooser.setCurrentDirectory(initialDirectory);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    public void upload(){
        File file = getFileFromFolder();
        if (file == null) {
            JOptionPane.showMessageDialog(null, language.getNoFile());
            return;
        }
        else{
            DrawingCanvasController drawingCanvasController = canvasView.getCanvasPanel().getDrawingCanvasController();
            drawingCanvasController.clear();
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(file);

                Element originElement = (Element) doc.getElementsByTagName("origin").item(0);
                float x = Float.parseFloat(originElement.getAttribute("x"));
                float y = Float.parseFloat(originElement.getAttribute("y"));
                model.geometry.Point origin = new Point(x, y);

                Element radiusElement = (Element) doc.getElementsByTagName("radius").item(0);
                float radius = Float.parseFloat(radiusElement.getTextContent());

                Circle circle = new Circle(origin, radius);
                drawingCanvasController.setGeometryObjects(new ArrayList<>());
                drawingCanvasController.addGeometryObject(circle);

                Graphics g = canvasView.getCanvasPanel().getGraphics();
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(canvasView.getCanvasPanel().getColor());
                g2d.setStroke(canvasView.getCanvasPanel().getStroke());
                g2d.drawOval((int) (origin.getX() - radius), (int) (origin.getY() - radius), (int) (2 * radius), (int) (2 * radius));
                canvasView.getCanvasPanel().repaint();

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void login(){
        canvasView.setVisible(false);
        LoginView loginView = new LoginView(canvasView);
    }

    public void newAccount(){
        canvasView.setVisible(false);
        NewAccountView newAccountView = new NewAccountView(canvasView);
    }
}

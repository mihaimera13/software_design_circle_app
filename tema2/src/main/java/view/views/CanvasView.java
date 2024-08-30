package view.views;

import view.resources.DrawingCanvas;
import view.resources.Palette;
import viewmodel.viewmodels.VMCanvas;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class CanvasView extends JFrame implements ActionListener, ChangeListener {

    private JPanel mainPanel;
    private JPanel barPanel;
    private DrawingCanvas canvasPanel;
    private JPanel brushPanel;
    private JPanel downPanel;
    private JLabel barLabel;

    private JButton circleButton;
    private JButton polygonCircButton;
    private JButton polygonInscButton;

    private JButton redButton;
    private JButton greenButton;
    private JButton orangeButton;
    private JButton blueButton;
    private JButton blackButton;
    private JSlider slider;
    private JButton basicLineButton;
    private JButton dashedLineButton;
    private JButton dottedLineButton;
    private JButton colorSource;
    private JButton patternSource;
    private JButton uploadXML;
    private JButton downloadXML;

    private VMCanvas vmCanvas;

    public CanvasView(){
        this.setMainPanel();
    }

    private void setMainPanel(){
        vmCanvas = new VMCanvas(this);
        SpringLayout springLayout = new SpringLayout();
        DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

        mainPanel = new JPanel(springLayout);
        mainPanel.setBackground(Color.WHITE);
        this.setSize(mode.getWidth(),mode.getHeight());

        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(0,0);
        this.setTitle("Around App");

        setBarPanel();
        springLayout.putConstraint(SpringLayout.NORTH,barPanel,0,SpringLayout.NORTH,mainPanel);
        mainPanel.add(barPanel);

        setBrushPanel();
        springLayout.putConstraint(SpringLayout.NORTH,brushPanel,0,SpringLayout.SOUTH,barPanel);
        springLayout.putConstraint(SpringLayout.WEST,brushPanel,0,SpringLayout.WEST,mainPanel);
        mainPanel.add(brushPanel);

        setCanvasPanel();
        springLayout.putConstraint(SpringLayout.EAST,canvasPanel,0,SpringLayout.EAST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,canvasPanel,0,SpringLayout.SOUTH,barPanel);
        canvasPanel.setFocusable(true);
        mainPanel.add(canvasPanel);

        setDownPanel();
        springLayout.putConstraint(SpringLayout.SOUTH,downPanel,0,SpringLayout.SOUTH,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,downPanel,0,SpringLayout.SOUTH,canvasPanel);
        mainPanel.add(downPanel);

        this.setContentPane(this.mainPanel);
        this.setVisible(true);
    }

    private void setBarPanel(){
        SpringLayout springLayout = new SpringLayout();
        barPanel = new JPanel(springLayout);
        int width = this.getWidth();
        int height = this.getHeight()/15;
        barPanel.setPreferredSize(new Dimension(width,height));

        barPanel.setBorder(new LineBorder(Palette.BLUE10.color(),3));
        barPanel.setBackground(Palette.BLUE4.color());

        barLabel = new JLabel("You are not logged in yet.");
        barLabel.setForeground(Palette.BLUE10.color());
        barLabel.setFont(new Font("Font", Font.BOLD,15));
        springLayout.putConstraint(SpringLayout.EAST,barLabel,-20,SpringLayout.EAST,barPanel);
        springLayout.putConstraint(SpringLayout.NORTH,barLabel,10,SpringLayout.NORTH,barPanel);
        barPanel.add(barLabel);
    }

    private void setCanvasPanel(){
        Stroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
        canvasPanel = new DrawingCanvas(Color.BLACK,stroke);
        int width = this.getWidth() - brushPanel.getWidth();
        int height = (int) (this.getHeight()/1.4);
        canvasPanel.setPreferredSize(new Dimension(width, height));
        canvasPanel.setBackground(Palette.BLUE1.color());
    }

    private void setBrushPanel(){
        SpringLayout springLayout = new SpringLayout();
        brushPanel = new JPanel(springLayout);
        int width = this.getWidth()/6;
        int height = (int) (this.getHeight()/1.4);
        brushPanel.setPreferredSize(new Dimension(width, height));

        JLabel brushLabel = new JLabel("BRUSH SETTINGS");
        brushLabel.setForeground(Palette.BLUE1.color());
        brushLabel.setFont(new Font("Font", Font.BOLD,15));
        springLayout.putConstraint(SpringLayout.WEST,brushLabel,10,SpringLayout.WEST,brushPanel);
        springLayout.putConstraint(SpringLayout.NORTH,brushLabel,10,SpringLayout.NORTH,brushPanel);
        brushPanel.add(brushLabel);

        redButton = brushColorButton(Color.RED);
        springLayout.putConstraint(SpringLayout.WEST,redButton,20,SpringLayout.WEST,brushPanel);
        springLayout.putConstraint(SpringLayout.NORTH,redButton,10,SpringLayout.SOUTH,brushLabel);
        brushPanel.add(redButton);

        blackButton = brushColorButton(Color.BLACK);
        springLayout.putConstraint(SpringLayout.WEST,blackButton,20,SpringLayout.WEST,brushPanel);
        springLayout.putConstraint(SpringLayout.NORTH,blackButton,0,SpringLayout.SOUTH,redButton);
        brushPanel.add(blackButton);

        greenButton = brushColorButton(Color.GREEN);
        springLayout.putConstraint(SpringLayout.WEST,greenButton,20,SpringLayout.WEST,brushPanel);
        springLayout.putConstraint(SpringLayout.NORTH,greenButton,0,SpringLayout.SOUTH,blackButton);
        brushPanel.add(greenButton);

        orangeButton = brushColorButton(Color.ORANGE);
        springLayout.putConstraint(SpringLayout.WEST,orangeButton,20,SpringLayout.WEST,brushPanel);
        springLayout.putConstraint(SpringLayout.NORTH,orangeButton,0,SpringLayout.SOUTH,greenButton);
        brushPanel.add(orangeButton);

        blueButton = brushColorButton(Color.BLUE);
        springLayout.putConstraint(SpringLayout.WEST,blueButton,20,SpringLayout.WEST,brushPanel);
        springLayout.putConstraint(SpringLayout.NORTH,blueButton,0,SpringLayout.SOUTH,orangeButton);
        brushPanel.add(blueButton);

        slider = new JSlider(JSlider.VERTICAL, 1, 10, 1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(1);
        slider.setBackground(null);
        slider.addChangeListener(this);
        slider.setPreferredSize(new Dimension(70,150));
        springLayout.putConstraint(SpringLayout.WEST,slider,30,SpringLayout.EAST,redButton);
        springLayout.putConstraint(SpringLayout.NORTH,slider,10,SpringLayout.SOUTH,brushLabel);
        brushPanel.add(slider);

        basicLineButton = new JButton("<html><u>______________</u></html>");
        basicLineButton.setPreferredSize(new Dimension(150,30));
        basicLineButton.setActionCommand("CHANGE DASHING 1");
        basicLineButton.addActionListener(this);
        basicLineButton.setBackground(Color.LIGHT_GRAY);
        springLayout.putConstraint(SpringLayout.NORTH,basicLineButton,30,SpringLayout.SOUTH,blueButton);
        springLayout.putConstraint(SpringLayout.WEST,basicLineButton,25,SpringLayout.WEST,brushPanel);
        brushPanel.add(basicLineButton);

        dashedLineButton = new JButton("_ _ _ _ _ _ _ _ _ _ _");
        dashedLineButton.setPreferredSize(new Dimension(150,30));
        dashedLineButton.setActionCommand("CHANGE DASHING 2");
        dashedLineButton.addActionListener(this);
        dashedLineButton.setBackground(Color.LIGHT_GRAY);
        springLayout.putConstraint(SpringLayout.NORTH,dashedLineButton,30,SpringLayout.SOUTH,basicLineButton);
        springLayout.putConstraint(SpringLayout.WEST,dashedLineButton,25,SpringLayout.WEST,brushPanel);
        brushPanel.add(dashedLineButton);

        dottedLineButton = new JButton("................................");
        dottedLineButton.setPreferredSize(new Dimension(150,30));
        dottedLineButton.setActionCommand("CHANGE DASHING 3");
        dottedLineButton.addActionListener(this);
        dottedLineButton.setBackground(Color.LIGHT_GRAY);
        springLayout.putConstraint(SpringLayout.NORTH,dottedLineButton,30,SpringLayout.SOUTH,dashedLineButton);
        springLayout.putConstraint(SpringLayout.WEST,dottedLineButton,25,SpringLayout.WEST,brushPanel);
        brushPanel.add(dottedLineButton);

        brushPanel.setBackground(Palette.BLUE6.color());
        brushPanel.setBorder(new LineBorder(Palette.BLUE3.color(),8));
    }

    private void setDownPanel(){
        SpringLayout springLayout = new SpringLayout();
        downPanel = new JPanel(springLayout);
        int width = this.getWidth();
        int height = this.getHeight() - barPanel.getHeight() - canvasPanel.getHeight();
        downPanel.setPreferredSize(new Dimension(width, height));

        JLabel menuLabel = new JLabel("MENU");
        menuLabel.setForeground(Palette.BLUE2.color());
        menuLabel.setFont(new Font("Font", Font.BOLD,15));
        springLayout.putConstraint(SpringLayout.WEST,menuLabel,10,SpringLayout.WEST,downPanel);
        springLayout.putConstraint(SpringLayout.NORTH,menuLabel,10,SpringLayout.NORTH,downPanel);
        downPanel.add(menuLabel);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setPreferredSize(new Dimension(140,35));
        loginButton.setBackground(Palette.BLUE4.color());
        loginButton.setForeground(Palette.BLUE10.color());
        loginButton.setFont(new Font("Font",Font.BOLD,25));
        loginButton.setActionCommand("LOGIN");
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);
        springLayout.putConstraint(SpringLayout.EAST,loginButton,-20,SpringLayout.EAST,downPanel);
        springLayout.putConstraint(SpringLayout.NORTH,loginButton,10,SpringLayout.NORTH,downPanel);
        downPanel.add(loginButton);

        JButton accountButton = new JButton("REQUEST ACCOUNT");
        accountButton.setPreferredSize(new Dimension(300,35));
        accountButton.setBackground(Palette.BLUE4.color());
        accountButton.setForeground(Palette.BLUE10.color());
        accountButton.setFont(new Font("Font",Font.BOLD,25));
        accountButton.setActionCommand("ACCOUNT");
        accountButton.addActionListener(this);
        accountButton.setFocusable(false);
        springLayout.putConstraint(SpringLayout.EAST,accountButton,-20,SpringLayout.EAST,downPanel);
        springLayout.putConstraint(SpringLayout.NORTH,accountButton,10,SpringLayout.SOUTH,loginButton);
        downPanel.add(accountButton);

        circleButton = new JButton("SHOW CIRCLE DATA");
        circleButton.setPreferredSize(new Dimension(300,35));
        circleButton.setBackground(Palette.BLUE4.color());
        circleButton.setForeground(Palette.BLUE10.color());
        circleButton.setFont(new Font("Font",Font.BOLD,25));
        circleButton.setActionCommand("CIRCLE");
        circleButton.addActionListener(this);
        circleButton.setFocusable(false);
        springLayout.putConstraint(SpringLayout.WEST,circleButton,100,SpringLayout.WEST,downPanel);
        springLayout.putConstraint(SpringLayout.NORTH,circleButton,10,SpringLayout.NORTH,downPanel);
        downPanel.add(circleButton);

        polygonCircButton = new JButton("CIRCUMSCRIBED?");
        polygonCircButton.setPreferredSize(new Dimension(300,35));
        polygonCircButton.setBackground(Palette.BLUE4.color());
        polygonCircButton.setForeground(Palette.BLUE10.color());
        polygonCircButton.setFont(new Font("Font",Font.BOLD,25));
        polygonCircButton.setActionCommand("CIRCUMSCRIBED");
        polygonCircButton.addActionListener(this);
        polygonCircButton.setFocusable(false);
        springLayout.putConstraint(SpringLayout.WEST,polygonCircButton,30,SpringLayout.EAST,circleButton);
        springLayout.putConstraint(SpringLayout.NORTH,polygonCircButton,10,SpringLayout.NORTH,downPanel);
        downPanel.add(polygonCircButton);

        polygonInscButton = new JButton("INSCRIBED?");
        polygonInscButton.setPreferredSize(new Dimension(200,35));
        polygonInscButton.setBackground(Palette.BLUE4.color());
        polygonInscButton.setForeground(Palette.BLUE10.color());
        polygonInscButton.setFont(new Font("Font",Font.BOLD,25));
        polygonInscButton.setActionCommand("INSCRIBED");
        polygonInscButton.addActionListener(this);
        polygonInscButton.setFocusable(false);
        springLayout.putConstraint(SpringLayout.WEST,polygonInscButton,30,SpringLayout.EAST,polygonCircButton);
        springLayout.putConstraint(SpringLayout.NORTH,polygonInscButton,10,SpringLayout.NORTH,downPanel);
        downPanel.add(polygonInscButton);

        uploadXML = new JButton("UPLOAD XML");
        uploadXML.setPreferredSize(new Dimension(200,35));
        uploadXML.setBackground(Palette.BLUE4.color());
        uploadXML.setForeground(Palette.BLUE10.color());
        uploadXML.setFont(new Font("Font",Font.BOLD,25));
        uploadXML.setActionCommand("UPLOAD");
        uploadXML.addActionListener(this);
        uploadXML.setFocusable(false);
        springLayout.putConstraint(SpringLayout.WEST,uploadXML,100,SpringLayout.WEST,downPanel);
        springLayout.putConstraint(SpringLayout.NORTH,uploadXML,10,SpringLayout.SOUTH,circleButton);
        downPanel.add(uploadXML);

        downloadXML = new JButton("DOWNLOAD XML");
        downloadXML.setPreferredSize(new Dimension(300,35));
        downloadXML.setBackground(Palette.BLUE4.color());
        downloadXML.setForeground(Palette.BLUE10.color());
        downloadXML.setFont(new Font("Font",Font.BOLD,25));
        downloadXML.setActionCommand("DOWNLOAD");
        downloadXML.addActionListener(this);
        downloadXML.setFocusable(false);
        springLayout.putConstraint(SpringLayout.WEST,downloadXML,30,SpringLayout.EAST,uploadXML);
        springLayout.putConstraint(SpringLayout.NORTH,downloadXML,10,SpringLayout.SOUTH,circleButton);
        downPanel.add(downloadXML);

        downPanel.setBackground(Palette.BLUE9.color());
    }

    private JButton brushColorButton(Color color){
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(30,30));
        button.setActionCommand("CHANGE COLOR");
        button.addActionListener(this);
        button.setBorder(new LineBorder(Color.LIGHT_GRAY,4));
        button.setBackground(color);
        return button;
    }

    public void setBarLabel(String text) {
        this.barLabel.setText(text);
    }

    public DrawingCanvas getCanvasPanel() {
        return canvasPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if(command.equals("CHANGE COLOR")){
            colorSource = (JButton) e.getSource();
            vmCanvas.changeColor.execute();
        }
        else if(command.contains("CHANGE DASHING")){
            patternSource = (JButton) e.getSource();
            vmCanvas.changePattern.execute();
        }
        else if(command.equals("LOGIN")){
            vmCanvas.openLogin.execute();
        }
        else if(command.equals("ACCOUNT")){
            vmCanvas.openAccount.execute();
        }
        else if(command.equals("CIRCLE")){
            vmCanvas.displayInformation.execute();
        }
        else if(command.equals("CIRCUMSCRIBED")){
            vmCanvas.drawCircumscribed.execute();
        }else if(command.equals("INSCRIBED")){
            vmCanvas.drawInscribed.execute();
        }
        else if(command.equals("UPLOAD")){
            vmCanvas.uploadXML.execute();
        }
        else if(command.equals("DOWNLOAD")){
            vmCanvas.downloadXML.execute();
        }

    }

    public Color getBrushColor(){
        return colorSource.getBackground();
    }

    public float[] getBrushDashing(){
        String s = patternSource.getActionCommand();
        if(s.contains("1"))
            return new float[]{0};
        else if(s.contains("2"))
            return new float[]{6.0F,3.0F};
        else if(s.contains("3"))
            return new float[]{1.0F, 1.0F};
        else return new float[]{0};
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        vmCanvas.changeStroke.execute();
    }

    public JSlider getSlider() {
        return slider;
    }
}

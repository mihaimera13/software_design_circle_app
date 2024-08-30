package view.views;

import net.sds.mvvm.bindings.*;
import view.resources.Palette;

import viewmodel.viewmodels.VMTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TestView extends JFrame implements ActionListener{
    private JPanel mainPanel;
    private JButton startTest;
    private JButton endTest;
    private JButton nextButton;

    private ButtonGroup options;
    @Bind(value = "selected", target = "option1.value", type = BindingType.SOURCE_TO_TARGET)
    private JRadioButton option1;
    @Bind(value = "selected", target = "option2.value", type = BindingType.SOURCE_TO_TARGET)
    private JRadioButton option2;
    @Bind(value = "selected", target = "option3.value", type = BindingType.SOURCE_TO_TARGET)
    private JRadioButton option3;
    @Bind(value = "selected", target = "option4.value", type = BindingType.SOURCE_TO_TARGET)
    private JRadioButton option4;

    private JTextArea questionLabel;
    private SpringLayout springLayout;

    private int sid;
    private VMTest vmTest;

    public TestView(int sid){
        this.sid = sid;
        vmTest = new VMTest(this);
        springLayout = new SpringLayout();
        setMainPanel();
    }

    private void setMainPanel(){
        DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

        mainPanel = new JPanel(springLayout);
        mainPanel.setBackground(Palette.BLUE2.color());
        this.setSize(mode.getWidth(),mode.getHeight());

        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(0,0);
        this.setTitle("Test Window");

        startTest = new JButton("START THE TEST");
        startTest.setFocusable(false);
        startTest.setBackground(Palette.BLUE8.color());
        startTest.setForeground(Palette.BLUE3.color());
        startTest.setFont(new Font("FONT",Font.BOLD,40));
        startTest.setActionCommand("START");
        startTest.addActionListener(this);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER,startTest,0,SpringLayout.HORIZONTAL_CENTER,mainPanel);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER,startTest,0,SpringLayout.VERTICAL_CENTER,mainPanel);
        mainPanel.add(startTest);

        this.setContentPane(this.mainPanel);
        this.setVisible(true);

        options = new ButtonGroup();
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);

        try{
            Binder.bind(this, vmTest);
        } catch (BindingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "START" -> vmTest.start.execute();
            case "NEXT" -> vmTest.next.execute();
            case "END" -> vmTest.end.execute();
        }
    }

    public void fillPanel(String question, ArrayList<String> options){
        this.options.clearSelection();
        mainPanel.removeAll();
        questionLabel = new JTextArea(question);
        questionLabel.setPreferredSize(new Dimension(1000,70));
        questionLabel.setLineWrap(true);
        questionLabel.setWrapStyleWord(true);
        questionLabel.setFocusable(false);
        questionLabel.setBackground(null);
        questionLabel.setFont(new Font("FONT",Font.BOLD,25));
        questionLabel.setForeground(Palette.BLUE9.color());
        springLayout.putConstraint(SpringLayout.WEST,questionLabel,40,SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,questionLabel,40,SpringLayout.NORTH,mainPanel);
        mainPanel.add(questionLabel);

        option1.setText(options.get(0));
        option1.setSelected(false);
        option1.setForeground(Palette.BLUE9.color());
        option1.setFont(new Font("FONT",Font.PLAIN,20));
        option1.setFocusable(false);
        option1.setBackground(null);
        springLayout.putConstraint(SpringLayout.NORTH,option1,30,SpringLayout.SOUTH,questionLabel);
        springLayout.putConstraint(SpringLayout.WEST,option1,80,SpringLayout.WEST,mainPanel);
        mainPanel.add(option1);

        option2.setText(options.get(1));
        option2.setSelected(false);
        option2.setForeground(Palette.BLUE9.color());
        option2.setFont(new Font("FONT",Font.PLAIN,20));
        option2.setFocusable(false);
        option2.setBackground(null);
        springLayout.putConstraint(SpringLayout.NORTH,option2,10,SpringLayout.SOUTH,option1);
        springLayout.putConstraint(SpringLayout.WEST,option2,80,SpringLayout.WEST,mainPanel);
        mainPanel.add(option2);

        option3.setText(options.get(2));
        option3.setSelected(false);
        option3.setForeground(Palette.BLUE9.color());
        option3.setFont(new Font("FONT",Font.PLAIN,20));
        option3.setFocusable(false);
        option3.setBackground(null);
        springLayout.putConstraint(SpringLayout.NORTH,option3,10,SpringLayout.SOUTH,option2);
        springLayout.putConstraint(SpringLayout.WEST,option3,80,SpringLayout.WEST,mainPanel);
        mainPanel.add(option3);

        option4.setText(options.get(3));
        option4.setSelected(false);
        option4.setForeground(Palette.BLUE9.color());
        option4.setFont(new Font("FONT",Font.PLAIN,20));
        option4.setFocusable(false);
        option4.setBackground(null);
        springLayout.putConstraint(SpringLayout.NORTH,option4,10,SpringLayout.SOUTH,option3);
        springLayout.putConstraint(SpringLayout.WEST,option4,80,SpringLayout.WEST,mainPanel);
        mainPanel.add(option4);

        endTest = new JButton("END THE TEST");
        endTest.setFocusable(false);
        endTest.setBackground(Palette.BLUE8.color());
        endTest.setForeground(Palette.BLUE3.color());
        endTest.setFont(new Font("FONT",Font.BOLD,25));
        endTest.setActionCommand("END");
        endTest.addActionListener(this);
        springLayout.putConstraint(SpringLayout.EAST,endTest,-40,SpringLayout.EAST,mainPanel);
        springLayout.putConstraint(SpringLayout.SOUTH,endTest,-40,SpringLayout.SOUTH,mainPanel);
        endTest.setVisible(false);
        mainPanel.add(endTest);

        nextButton = new JButton("NEXT");
        nextButton.setFocusable(false);
        nextButton.setBackground(Palette.BLUE8.color());
        nextButton.setForeground(Palette.BLUE3.color());
        nextButton.setFont(new Font("FONT",Font.BOLD,40));
        nextButton.setActionCommand("NEXT");
        nextButton.addActionListener(this);
        springLayout.putConstraint(SpringLayout.EAST,nextButton,-40,SpringLayout.EAST,mainPanel);
        springLayout.putConstraint(SpringLayout.SOUTH,nextButton,-40,SpringLayout.SOUTH,mainPanel);
        nextButton.setVisible(false);
        mainPanel.add(nextButton);

        this.options.clearSelection();

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void fillPanel(String question, ArrayList<String> options,ImageIcon img){
        this.options.clearSelection();
        mainPanel.removeAll();
        questionLabel = new JTextArea(question);
        questionLabel.setPreferredSize(new Dimension(1000,70));
        questionLabel.setLineWrap(true);
        questionLabel.setWrapStyleWord(true);
        questionLabel.setFocusable(false);
        questionLabel.setBackground(null);
        questionLabel.setFont(new Font("FONT",Font.BOLD,25));
        questionLabel.setForeground(Palette.BLUE9.color());
        springLayout.putConstraint(SpringLayout.WEST,questionLabel,40,SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,questionLabel,40,SpringLayout.NORTH,mainPanel);
        mainPanel.add(questionLabel);

        option1.setText(options.get(0));
        option1.setSelected(false);
        option1.setForeground(Palette.BLUE9.color());
        option1.setFont(new Font("FONT",Font.PLAIN,20));
        option1.setFocusable(false);
        option1.setBackground(null);
        springLayout.putConstraint(SpringLayout.NORTH,option1,30,SpringLayout.SOUTH,questionLabel);
        springLayout.putConstraint(SpringLayout.WEST,option1,80,SpringLayout.WEST,mainPanel);
        mainPanel.add(option1);

        option2.setText(options.get(1));
        option2.setSelected(false);
        option2.setForeground(Palette.BLUE9.color());
        option2.setFont(new Font("FONT",Font.PLAIN,20));
        option2.setFocusable(false);
        option2.setBackground(null);
        springLayout.putConstraint(SpringLayout.NORTH,option2,10,SpringLayout.SOUTH,option1);
        springLayout.putConstraint(SpringLayout.WEST,option2,80,SpringLayout.WEST,mainPanel);
        mainPanel.add(option2);

        option3.setText(options.get(2));
        option3.setSelected(false);
        option3.setForeground(Palette.BLUE9.color());
        option3.setFont(new Font("FONT",Font.PLAIN,20));
        option3.setFocusable(false);
        option3.setBackground(null);
        springLayout.putConstraint(SpringLayout.NORTH,option3,10,SpringLayout.SOUTH,option2);
        springLayout.putConstraint(SpringLayout.WEST,option3,80,SpringLayout.WEST,mainPanel);
        mainPanel.add(option3);

        option4.setText(options.get(3));
        option4.setSelected(false);
        option4.setForeground(Palette.BLUE9.color());
        option4.setFont(new Font("FONT",Font.PLAIN,20));
        option4.setFocusable(false);
        option4.setBackground(null);
        springLayout.putConstraint(SpringLayout.NORTH,option4,10,SpringLayout.SOUTH,option3);
        springLayout.putConstraint(SpringLayout.WEST,option4,80,SpringLayout.WEST,mainPanel);
        mainPanel.add(option4);

        JLabel imageLabel = new JLabel(img);
        springLayout.putConstraint(SpringLayout.WEST,imageLabel,40,SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,imageLabel,50,SpringLayout.SOUTH,option4);
        mainPanel.add(imageLabel);

        endTest = new JButton("END THE TEST");
        endTest.setFocusable(false);
        endTest.setBackground(Palette.BLUE8.color());
        endTest.setForeground(Palette.BLUE3.color());
        endTest.setFont(new Font("FONT",Font.BOLD,25));
        endTest.setActionCommand("END");
        endTest.addActionListener(this);
        springLayout.putConstraint(SpringLayout.EAST,endTest,-40,SpringLayout.EAST,mainPanel);
        springLayout.putConstraint(SpringLayout.SOUTH,endTest,-40,SpringLayout.SOUTH,mainPanel);
        mainPanel.add(endTest);

        nextButton = new JButton("NEXT");
        nextButton.setFocusable(false);
        nextButton.setBackground(Palette.BLUE8.color());
        nextButton.setForeground(Palette.BLUE3.color());
        nextButton.setFont(new Font("FONT",Font.BOLD,40));
        nextButton.setActionCommand("NEXT");
        nextButton.addActionListener(this);
        springLayout.putConstraint(SpringLayout.EAST,nextButton,-40,SpringLayout.EAST,mainPanel);
        springLayout.putConstraint(SpringLayout.SOUTH,nextButton,-40,SpringLayout.SOUTH,mainPanel);
        mainPanel.add(nextButton);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public JButton getEndTest() {
        return endTest;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public int getSid() {
        return sid;
    }
}
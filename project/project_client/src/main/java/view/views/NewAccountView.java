package view.views;

import controller.NewAccountController;
import model.language.Language;
import view.resources.Palette;

import javax.swing.*;
import java.awt.*;

public class NewAccountView extends JFrame {

    private JPanel mainPanel;

    private JTextField nameField;
    private JTextField surnameField;
    private JTextField nicknameField;
    private JPasswordField passwordField;
    private JButton requestButton;

    private CanvasView canvasView;

    private NewAccountController newAccountController;

    private Language language;

    public NewAccountView(CanvasView cv){
        canvasView = cv;
        language = new Language(cv.getClient());
        newAccountController = new NewAccountController(this);
        setMainPanel();
    }

    private void setMainPanel(){
        SpringLayout springLayout = new SpringLayout();
        DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

        mainPanel = new JPanel(springLayout);
        mainPanel.setBackground(Palette.BLUE1.color());
        this.setSize(mode.getWidth(),mode.getHeight());

        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(0,0);
        this.setTitle(language.getNewAccountTitle());

        setJMenuBar(canvasView.getLanguageBar().getMenuBar());

        JLabel titleLabel = new JLabel(language.getAccountLabel());
        titleLabel.setBackground(null);
        titleLabel.setForeground(Palette.BLUE7.color());
        titleLabel.setFont(new Font("FONT",Font.BOLD,40));
        springLayout.putConstraint(SpringLayout.WEST,titleLabel,(int)(mode.getWidth()*0.4),SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,titleLabel,80,SpringLayout.NORTH,mainPanel);
        mainPanel.add(titleLabel);

        JLabel nameLabel = new JLabel(language.getNameLabel().toUpperCase());
        nameLabel.setBackground(null);
        nameLabel.setForeground(Palette.BLUE7.color());
        nameLabel.setFont(new Font("FONT",Font.BOLD,25));
        springLayout.putConstraint(SpringLayout.WEST,nameLabel,500,SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,nameLabel,100,SpringLayout.NORTH,titleLabel);
        mainPanel.add(nameLabel);

        nameField =  new JTextField();
        nameField.setPreferredSize(new Dimension(140,25));
        nameField.setBackground(Palette.BLUE4.color());
        nameField.setForeground(Palette.BLUE10.color());
        nameField.setFont(new Font("Font",Font.PLAIN,20));
        springLayout.putConstraint(SpringLayout.WEST,nameField,10,SpringLayout.EAST,nameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,nameField,100,SpringLayout.NORTH,titleLabel);
        mainPanel.add(nameField);

        JLabel surnameLabel = new JLabel(language.getSurnameLabel().toUpperCase());
        surnameLabel.setBackground(null);
        surnameLabel.setForeground(Palette.BLUE7.color());
        surnameLabel.setFont(new Font("FONT",Font.BOLD,25));
        springLayout.putConstraint(SpringLayout.WEST,surnameLabel,500,SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,surnameLabel,10,SpringLayout.SOUTH,nameLabel);
        mainPanel.add(surnameLabel);

        surnameField =  new JTextField();
        surnameField.setPreferredSize(new Dimension(140,25));
        surnameField.setBackground(Palette.BLUE4.color());
        surnameField.setForeground(Palette.BLUE10.color());
        surnameField.setFont(new Font("Font",Font.PLAIN,20));
        springLayout.putConstraint(SpringLayout.WEST,surnameField,10,SpringLayout.EAST,surnameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,surnameField,10,SpringLayout.SOUTH,nameLabel);
        mainPanel.add(surnameField);

        JLabel nicknameLabel = new JLabel(language.getNicknameLabel().toUpperCase());
        nicknameLabel.setBackground(null);
        nicknameLabel.setForeground(Palette.BLUE7.color());
        nicknameLabel.setFont(new Font("FONT",Font.BOLD,25));
        springLayout.putConstraint(SpringLayout.WEST,nicknameLabel,500,SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,nicknameLabel,10,SpringLayout.SOUTH,surnameLabel);
        mainPanel.add(nicknameLabel);

        nicknameField =  new JTextField();
        nicknameField.setPreferredSize(new Dimension(140,25));
        nicknameField.setBackground(Palette.BLUE4.color());
        nicknameField.setForeground(Palette.BLUE10.color());
        nicknameField.setFont(new Font("Font",Font.PLAIN,20));
        springLayout.putConstraint(SpringLayout.WEST,nicknameField,10,SpringLayout.EAST,nicknameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,nicknameField,10,SpringLayout.SOUTH,surnameLabel);
        mainPanel.add(nicknameField);

        JLabel passwordLabel = new JLabel(language.getPasswordLabel().toUpperCase());
        passwordLabel.setBackground(null);
        passwordLabel.setForeground(Palette.BLUE7.color());
        passwordLabel.setFont(new Font("FONT",Font.BOLD,25));
        springLayout.putConstraint(SpringLayout.WEST,passwordLabel,500,SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,passwordLabel,10,SpringLayout.SOUTH,nicknameLabel);
        mainPanel.add(passwordLabel);

        passwordField =  new JPasswordField();
        passwordField.setPreferredSize(new Dimension(140,25));
        passwordField.setBackground(Palette.BLUE4.color());
        passwordField.setForeground(Palette.BLUE10.color());
        passwordField.setFont(new Font("Font",Font.PLAIN,20));
        springLayout.putConstraint(SpringLayout.WEST,passwordField,10,SpringLayout.EAST,passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH,passwordField,10,SpringLayout.SOUTH,nicknameLabel);
        mainPanel.add(passwordField);

        requestButton = new JButton("<html>"+ language.getRequestAccount()  +"</html>");
        requestButton.setPreferredSize(new Dimension(180,60));
        requestButton.setBackground(Palette.BLUE4.color());
        requestButton.setForeground(Palette.BLUE10.color());
        requestButton.setFont(new Font("Font",Font.BOLD,25));
        requestButton.setActionCommand("REQUEST");
        requestButton.addActionListener(newAccountController);
        requestButton.setFocusable(false);
        springLayout.putConstraint(SpringLayout.WEST,requestButton,(int)(mode.getWidth()*0.45),SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,requestButton,40,SpringLayout.SOUTH,passwordLabel);
        mainPanel.add(requestButton);

        this.setContentPane(this.mainPanel);
        this.setVisible(true);
    }

    public String getNameField() {
        return nameField.getText();
    }

    public void setNameField(String name) {
        nameField.setText(name);
    }

    public String getSurnameField() {
        return surnameField.getText();
    }

    public void setSurnameField(String surname) {
        surnameField.setText(surname);
    }

    public String getNicknameField() {
        return nicknameField.getText();
    }

    public void setNicknameField(String nickname) {
        nicknameField.setText(nickname);
    }

    public String getPasswordField() {
        return String.valueOf(passwordField.getPassword());
    }

    public void setPasswordField(String password) {
        passwordField.setText(password);
    }

    public CanvasView getCanvasView() {
        return canvasView;
    }
}

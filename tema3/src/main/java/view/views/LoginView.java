package view.views;

import controller.LoginController;
import model.language.Language;
import view.resources.Palette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Locale;

public class LoginView extends JFrame{
    private JPanel mainPanel;

    private JTextField nicknameTextField;

    private JPasswordField passwordTextField;
    private JButton loginButton;
    private JButton backButton;
    private JLabel errorLabel;

    private CanvasView canvasView;

    private LoginController loginController;

    private Language language;

    public LoginView(CanvasView cv){
        language = new Language();
        loginController = new LoginController(this);
        canvasView = cv;
        setMainPanel();
    }

    private void setMainPanel(){
        SpringLayout springLayout = new SpringLayout();
        DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

        mainPanel = new JPanel(springLayout);
        mainPanel.setBackground(Palette.BLUE8.color());
        this.setSize(mode.getWidth(),mode.getHeight());

        setJMenuBar(canvasView.getLanguageBar().getMenuBar());

        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(0,0);
        this.setTitle(language.getLoginWindow());

        JLabel titleLabel = new JLabel(language.getLoginTitle());
        titleLabel.setBackground(null);
        titleLabel.setForeground(Palette.BLUE2.color());
        titleLabel.setFont(new Font("FONT",Font.BOLD,40));
        springLayout.putConstraint(SpringLayout.WEST,titleLabel,(int)(mode.getWidth()*0.45),SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,titleLabel,80,SpringLayout.NORTH,mainPanel);
        mainPanel.add(titleLabel);

        JLabel nicknameLabel = new JLabel(language.getNicknameLabel().toUpperCase());
        nicknameLabel.setBackground(null);
        nicknameLabel.setForeground(Palette.BLUE2.color());
        nicknameLabel.setFont(new Font("FONT",Font.BOLD,25));
        springLayout.putConstraint(SpringLayout.EAST,nicknameLabel,-(int)(mode.getWidth()*0.5),SpringLayout.EAST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,nicknameLabel,(int)(mode.getHeight()*0.35),SpringLayout.NORTH,mainPanel);
        mainPanel.add(nicknameLabel);

        JLabel passwordLabel = new JLabel(language.getPasswordLabel());
        passwordLabel.setBackground(null);
        passwordLabel.setForeground(Palette.BLUE2.color());
        passwordLabel.setFont(new Font("FONT",Font.BOLD,25));
        springLayout.putConstraint(SpringLayout.EAST,passwordLabel,-(int)(mode.getWidth()*0.5),SpringLayout.EAST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,passwordLabel,15,SpringLayout.SOUTH,nicknameLabel);
        mainPanel.add(passwordLabel);

        nicknameTextField =  new JTextField();
        nicknameTextField.setPreferredSize(new Dimension(140,24));
        nicknameTextField.setBackground(Palette.BLUE4.color());
        nicknameTextField.setForeground(Palette.BLUE10.color());
        nicknameTextField.setFont(new Font("Font",Font.PLAIN,20));
        springLayout.putConstraint(SpringLayout.WEST,nicknameTextField,10,SpringLayout.EAST,nicknameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,nicknameTextField,(int)(mode.getHeight()*0.36),SpringLayout.NORTH,mainPanel);
        mainPanel.add(nicknameTextField);

        passwordTextField =  new JPasswordField();
        passwordTextField.setPreferredSize(new Dimension(140,24));
        passwordTextField.setBackground(Palette.BLUE4.color());
        passwordTextField.setForeground(Palette.BLUE10.color());
        passwordTextField.setFont(new Font("Font",Font.PLAIN,20));
        springLayout.putConstraint(SpringLayout.WEST,passwordTextField,10,SpringLayout.EAST,passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH,passwordTextField,18,SpringLayout.SOUTH,nicknameTextField);
        mainPanel.add(passwordTextField);

        loginButton = new JButton(language.getLoginTitle().toUpperCase());
        loginButton.setPreferredSize(new Dimension(140,35));
        loginButton.setBackground(Palette.BLUE4.color());
        loginButton.setForeground(Palette.BLUE10.color());
        loginButton.setFont(new Font("Font",Font.BOLD,25));
        loginButton.setActionCommand("LOGIN");
        loginButton.addActionListener(loginController);
        loginButton.setFocusable(false);
        springLayout.putConstraint(SpringLayout.WEST,loginButton,(int)(mode.getWidth()*0.45),SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,loginButton,40,SpringLayout.SOUTH,passwordLabel);
        mainPanel.add(loginButton);

        backButton = new JButton(language.getBackButton());
        backButton.setPreferredSize(new Dimension(140,35));
        backButton.setBackground(Palette.BLUE4.color());
        backButton.setForeground(Palette.BLUE10.color());
        backButton.setFont(new Font("Font",Font.BOLD,25));
        backButton.setActionCommand("BACK");
        backButton.addActionListener(loginController);
        backButton.setFocusable(false);
        springLayout.putConstraint(SpringLayout.WEST,backButton,(int)(mode.getWidth()*0.45),SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,backButton,20,SpringLayout.SOUTH,loginButton);
        mainPanel.add(backButton);

        errorLabel = new JLabel(language.getLoginError());
        errorLabel.setBackground(Color.RED);
        errorLabel.setForeground(Palette.BLUE10.color());
        errorLabel.setFont(new Font("FONT",Font.BOLD,25));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER,errorLabel,0,SpringLayout.HORIZONTAL_CENTER,loginButton);
        springLayout.putConstraint(SpringLayout.NORTH,errorLabel,0,SpringLayout.SOUTH,backButton);
        errorLabel.setVisible(false);
        mainPanel.add(errorLabel);

        this.setContentPane(this.mainPanel);
        this.setVisible(true);

    }

    public void setNicknameTextField(String text){
        this.nicknameTextField.setText(text);
    }

    public void setPasswordTextField(String text){
        this.passwordTextField.setText(text);
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public CanvasView getCanvasView() {
        return canvasView;
    }

    public String getNickname() {
        return nicknameTextField.getText();
    }

    public String getPassword() {
        return new String(passwordTextField.getPassword());
    }

}

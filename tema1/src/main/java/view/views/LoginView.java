package view.views;

import presenter.LoginPresenter;
import view.resources.ILoginView;
import view.resources.Palette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginView extends JFrame implements ActionListener, ILoginView {
    private JPanel mainPanel;
    private JTextField nicknameTextField;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private LoginPresenter loginPresenter;
    private JLabel errorLabel;

    private CanvasView canvasView;

    public LoginView(CanvasView cv){
        canvasView = cv;
        setMainPanel();
        loginPresenter = new LoginPresenter(this);
    }

    private void setMainPanel(){
        SpringLayout springLayout = new SpringLayout();
        DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

        mainPanel = new JPanel(springLayout);
        mainPanel.setBackground(Palette.BLUE8.color());
        this.setSize(mode.getWidth(),mode.getHeight());

        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(0,0);
        this.setTitle("Login Window");

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setBackground(null);
        titleLabel.setForeground(Palette.BLUE2.color());
        titleLabel.setFont(new Font("FONT",Font.BOLD,40));
        springLayout.putConstraint(SpringLayout.WEST,titleLabel,(int)(mode.getWidth()*0.45),SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,titleLabel,80,SpringLayout.NORTH,mainPanel);
        mainPanel.add(titleLabel);

        JLabel nicknameLabel = new JLabel("NICKNAME:");
        nicknameLabel.setBackground(null);
        nicknameLabel.setForeground(Palette.BLUE2.color());
        nicknameLabel.setFont(new Font("FONT",Font.BOLD,25));
        springLayout.putConstraint(SpringLayout.EAST,nicknameLabel,-(int)(mode.getWidth()*0.5),SpringLayout.EAST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,nicknameLabel,(int)(mode.getHeight()*0.35),SpringLayout.NORTH,mainPanel);
        mainPanel.add(nicknameLabel);

        JLabel passwordLabel = new JLabel("PASSWORD:");
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

        loginButton = new JButton("LOGIN");
        loginButton.setPreferredSize(new Dimension(140,35));
        loginButton.setBackground(Palette.BLUE4.color());
        loginButton.setForeground(Palette.BLUE10.color());
        loginButton.setFont(new Font("Font",Font.BOLD,25));
        loginButton.setActionCommand("LOGIN");
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);
        springLayout.putConstraint(SpringLayout.WEST,loginButton,(int)(mode.getWidth()*0.45),SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,loginButton,40,SpringLayout.SOUTH,passwordLabel);
        mainPanel.add(loginButton);
        
        errorLabel = new JLabel("Something went wrong with your login.");
        errorLabel.setBackground(Color.RED);
        errorLabel.setForeground(Palette.BLUE10.color());
        errorLabel.setFont(new Font("FONT",Font.BOLD,25));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER,errorLabel,0,SpringLayout.HORIZONTAL_CENTER,loginButton);
        springLayout.putConstraint(SpringLayout.NORTH,errorLabel,0,SpringLayout.SOUTH,loginButton);
        errorLabel.setVisible(false);
        mainPanel.add(errorLabel);

        this.setContentPane(this.mainPanel);
        this.setVisible(true);
    }

    public String getNicknameField(){
        if(nicknameTextField.getText().isEmpty())
            return null;
        else return nicknameTextField.getText().trim();
    }

    public String getPasswordField(){
        if(passwordTextField.getPassword().length == 0)
            return null;
        else return Arrays.toString(passwordTextField.getPassword());
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("LOGIN")){
            loginPresenter.login();
        }
    }

    public CanvasView getCanvasView() {
        return canvasView;
    }
}

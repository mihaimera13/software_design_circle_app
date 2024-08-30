package view.views;

import net.sds.mvvm.bindings.*;
import view.resources.Palette;

import viewmodel.viewmodels.VMAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AccountView extends JFrame implements ActionListener{

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel nameLabel;
    private JLabel nameSLabel;
    private JLabel surnameLabel;
    private JLabel surnameSLabel;
    private JLabel nicknameLabel;
    @Bind(value = "text", target = "nickname.value", type = BindingType.BI_DIRECTIONAL)
    private JLabel nicknameSLabel;

    private JButton testButton;
    private JButton backButton;

    private JScrollPane scrollPanel;
    @Bind(value = "model", target = "model.value", type = BindingType.TARGET_TO_SOURCE)
    private JTable testTable;

    private VMAccount vmAccount;

    private CanvasView canvasView;

    public AccountView(String name, String surname,String nickname,CanvasView cv){
        testTable = new JTable();
        vmAccount = new VMAccount(this);
        canvasView = cv;
        setMainPanel(name,surname,nickname);
    }

    private void setMainPanel(String name, String surname,String nickname){
        SpringLayout springLayout = new SpringLayout();
        DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

        mainPanel = new JPanel(springLayout);
        mainPanel.setBackground(Palette.BLUE1.color());
        this.setSize(mode.getWidth(),mode.getHeight());

        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(0,0);
        this.setTitle("Account Window");

        titleLabel = new JLabel("Account");
        titleLabel.setBackground(null);
        titleLabel.setForeground(Palette.BLUE9.color());
        titleLabel.setFont(new Font("FONT",Font.BOLD,40));
        springLayout.putConstraint(SpringLayout.WEST,titleLabel,(int)(mode.getWidth()*0.45),SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,titleLabel,20,SpringLayout.NORTH,mainPanel);
        mainPanel.add(titleLabel);

        nameLabel = setLabel("Name:");
        nameLabel.setFont(new Font("Font",Font.BOLD,20));
        springLayout.putConstraint(SpringLayout.WEST,nameLabel,60,SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,nameLabel,40,SpringLayout.SOUTH,titleLabel);
        mainPanel.add(nameLabel);

        nameSLabel = setLabel(name);
        nameSLabel.setFont(new Font("Font",Font.PLAIN,20));
        springLayout.putConstraint(SpringLayout.WEST,nameSLabel,20,SpringLayout.EAST,nameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,nameSLabel,40,SpringLayout.SOUTH,titleLabel);
        mainPanel.add(nameSLabel);

        surnameLabel = setLabel("Surname:");
        surnameLabel.setFont(new Font("Font",Font.BOLD,20));
        springLayout.putConstraint(SpringLayout.WEST,surnameLabel,60,SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,surnameLabel,20,SpringLayout.SOUTH,nameLabel);
        mainPanel.add(surnameLabel);

        surnameSLabel = setLabel(surname);
        surnameSLabel.setFont(new Font("Font",Font.PLAIN,20));
        springLayout.putConstraint(SpringLayout.WEST,surnameSLabel,20,SpringLayout.EAST,surnameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,surnameSLabel,20,SpringLayout.SOUTH,nameLabel);
        mainPanel.add(surnameSLabel);

        nicknameLabel = setLabel("Nickname:");
        nicknameLabel.setFont(new Font("Font",Font.BOLD,20));
        springLayout.putConstraint(SpringLayout.WEST,nicknameLabel,60,SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,nicknameLabel,20,SpringLayout.SOUTH,surnameLabel);
        mainPanel.add(nicknameLabel);

        nicknameSLabel = setLabel(nickname);
        nicknameSLabel.setFont(new Font("Font",Font.PLAIN,20));
        springLayout.putConstraint(SpringLayout.WEST,nicknameSLabel,20,SpringLayout.EAST,nicknameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,nicknameSLabel,20,SpringLayout.SOUTH,surnameLabel);
        mainPanel.add(nicknameSLabel);

        testButton = new JButton("Take a test");
        testButton.setPreferredSize(new Dimension(200,35));
        testButton.setBackground(Palette.BLUE4.color());
        testButton.setForeground(Palette.BLUE10.color());
        testButton.setFont(new Font("Font",Font.BOLD,25));
        testButton.setActionCommand("TEST");
        testButton.addActionListener(this);
        testButton.setFocusable(false);
        springLayout.putConstraint(SpringLayout.EAST,testButton,-30,SpringLayout.EAST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,testButton,40,SpringLayout.NORTH,mainPanel);
        mainPanel.add(testButton);

        backButton = new JButton("BACK");
        backButton.setPreferredSize(new Dimension(120,35));
        backButton.setBackground(Palette.BLUE4.color());
        backButton.setForeground(Palette.BLUE10.color());
        backButton.setFont(new Font("Font",Font.BOLD,25));
        backButton.setActionCommand("BACK");
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        springLayout.putConstraint(SpringLayout.EAST,backButton,-30,SpringLayout.EAST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,backButton,10,SpringLayout.SOUTH,testButton);
        mainPanel.add(backButton);

        scrollPanel = createScrollPane();
        scrollPanel.setPreferredSize(new Dimension(700,300));
        scrollPanel.setFont(new Font("FONT",Font.PLAIN,10));
        scrollPanel.setBackground(Palette.BLUE7.color());
        scrollPanel.setForeground(Palette.BLUE3.color());
        springLayout.putConstraint(SpringLayout.NORTH,scrollPanel,30,SpringLayout.SOUTH,nicknameLabel);
        springLayout.putConstraint(SpringLayout.WEST,scrollPanel,70,SpringLayout.WEST,mainPanel);
        mainPanel.add(scrollPanel);

        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentShown(ComponentEvent e) {
                updateScrollPane();
            }

        });

        this.setContentPane(this.mainPanel);
        this.setVisible(true);

        try{
            Binder.bind(this,vmAccount);
        } catch (BindingException e) {
            e.printStackTrace();
        }
    }

    private JLabel setLabel(String text){
        JLabel label = new JLabel(text);
        label.setBackground(null);
        label.setForeground(Palette.BLUE7.color());

        return label;
    }

    private JScrollPane createScrollPane(){
        vmAccount.retrieveTests.execute();

        return new JScrollPane(testTable);
    }

    private void updateScrollPane(){

        vmAccount.retrieveTests.execute();

        scrollPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("TEST")){
            vmAccount.takeTest.execute();
        }
        else if(command.equals("BACK")){
            vmAccount.goBack.execute();
        }
    }

    public CanvasView getCanvasView() {
        return canvasView;
    }
}

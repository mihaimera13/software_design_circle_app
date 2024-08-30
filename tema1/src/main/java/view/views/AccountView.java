package view.views;

import presenter.AccountPresenter;
import view.resources.IAccountView;
import view.resources.Palette;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class AccountView extends JFrame implements ActionListener, IAccountView {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel nameLabel;
    private JLabel nameSLabel;
    private JLabel surnameLabel;
    private JLabel surnameSLabel;
    private JLabel nicknameLabel;
    private JLabel nicknameSLabel;
    
    private JButton testButton;
    private JButton backButton;
    
    private JScrollPane scrollPanel;
    private JTable testTable;

    private AccountPresenter accountPresenter;

    private CanvasView canvasView;

    public AccountView(String name, String surname,String nickname,CanvasView cv){
        canvasView = cv;
        accountPresenter = new AccountPresenter(this);
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
    }

    private JLabel setLabel(String text){
        JLabel label = new JLabel(text);
        label.setBackground(null);
        label.setForeground(Palette.BLUE7.color());

        return label;
    }

    private JScrollPane createScrollPane(){

        DefaultTableModel model = accountPresenter.retrieveStudentTests();
        testTable = new JTable(model);

        return new JScrollPane(testTable);
    }

    private void updateScrollPane(){
        DefaultTableModel model = accountPresenter.retrieveStudentTests();
        testTable.setModel(model);

        scrollPanel.repaint();
    }

    public JLabel getNicknameSLabel() {
        return nicknameSLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("TEST")){
            accountPresenter.takeTest();
        }
        else if(command.equals("BACK")){
            accountPresenter.goBack();
        }
    }

    public CanvasView getCanvasView() {
        return canvasView;
    }
}

package view.views;

import presenter.AdminPresenter;
import view.resources.IAdminView;
import view.resources.Palette;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class AdminView extends JFrame implements ActionListener, IAdminView {

    private JPanel mainPanel;
    private JButton approveButton;
    private JButton deleteButton;
    private JButton seeAllButton;
    private JButton seeRequestButton;
    private AdminPresenter adminPresenter;
    private JTable displayTable;

    private JScrollPane scrollPane;

    private String nickname;

    private CanvasView canvasView;

    public AdminView(CanvasView cv){
        canvasView = cv;
        adminPresenter = new AdminPresenter(this);
        displayTable = new JTable();
        setMainPanel();
    }

    private void setMainPanel(){
        SpringLayout springLayout = new SpringLayout();
        DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

        mainPanel = new JPanel(springLayout);
        mainPanel.setBackground(Palette.BLUE2.color());
        this.setSize(mode.getWidth(),mode.getHeight());

        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(0,0);
        this.setTitle("Admin Window");

        approveButton = setButton("<html>ACCEPT<br />REQUESTS</html>");
        approveButton.setActionCommand("APPROVE");
        approveButton.addActionListener(this);
        springLayout.putConstraint(SpringLayout.WEST,approveButton,400,SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,approveButton,15,SpringLayout.NORTH,mainPanel);
        mainPanel.add(approveButton);

        deleteButton = setButton("DELETE");
        deleteButton.setActionCommand("DELETE");
        deleteButton.addActionListener(this);
        springLayout.putConstraint(SpringLayout.WEST,deleteButton,15,SpringLayout.EAST,approveButton);
        springLayout.putConstraint(SpringLayout.NORTH,deleteButton,15,SpringLayout.NORTH,mainPanel);
        mainPanel.add(deleteButton);

        seeAllButton = setButton("SEE ALL");
        seeAllButton.setActionCommand("SEE_ALL");
        seeAllButton.addActionListener(this);
        springLayout.putConstraint(SpringLayout.WEST,seeAllButton,15,SpringLayout.EAST,deleteButton);
        springLayout.putConstraint(SpringLayout.NORTH,seeAllButton,15,SpringLayout.NORTH,mainPanel);
        mainPanel.add(seeAllButton);

        seeRequestButton = setButton("<html>SEE<br />PENDING</html>");
        seeRequestButton.setActionCommand("SEE_REQUESTS");
        seeRequestButton.addActionListener(this);
        springLayout.putConstraint(SpringLayout.WEST,seeRequestButton,15,SpringLayout.EAST,seeAllButton);
        springLayout.putConstraint(SpringLayout.NORTH,seeRequestButton,15,SpringLayout.NORTH,mainPanel);
        mainPanel.add(seeRequestButton);

        JButton backButton = setButton("BACK");
        backButton.setActionCommand("BACK");
        backButton.addActionListener(this);
        springLayout.putConstraint(SpringLayout.NORTH,backButton,15,SpringLayout.NORTH,mainPanel);
        springLayout.putConstraint(SpringLayout.EAST,backButton,-15,SpringLayout.EAST,mainPanel);
        mainPanel.add(backButton);

        scrollPane = new JScrollPane(displayTable);
        scrollPane.setPreferredSize(new Dimension(1140,500));
        scrollPane.setFont(new Font("FONT",Font.PLAIN,10));
        scrollPane.setBackground(Palette.BLUE7.color());
        scrollPane.setForeground(Palette.BLUE3.color());
        springLayout.putConstraint(SpringLayout.NORTH,scrollPane,70,SpringLayout.SOUTH,deleteButton);
        springLayout.putConstraint(SpringLayout.WEST,scrollPane,70,SpringLayout.WEST,mainPanel);
        mainPanel.add(scrollPane);

        displayTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = displayTable.rowAtPoint(e.getPoint());
                nickname = displayTable.getValueAt(row,2).toString();
            }
        });

        this.setContentPane(this.mainPanel);
        this.setVisible(true);
    }

    private JButton setButton(String text){
        JButton button = new JButton(text);
        button.setBackground(Palette.BLUE8.color());
        button.setForeground(Palette.BLUE1.color());
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setPreferredSize(new Dimension(100,50));
        button.setFocusable(false);

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("APPROVE")){
            adminPresenter.approve();
            updateSeeRequests();
        }
        else if(command.equals("SEE_ALL")){
            updateSeeAll();
        }
        else if(command.equals("SEE_REQUESTS")){
            updateSeeRequests();
        }
        else if(command.equals("DELETE")){
            adminPresenter.delete();
            updateSeeAll();
        }
        else if(command.equals("BACK")){
            adminPresenter.back();
        }
    }

    private void updateSeeAll(){
        DefaultTableModel dtm = adminPresenter.seeAll();
        displayTable.setModel(dtm);
        scrollPane.repaint();
    }

    private void updateSeeRequests(){
        DefaultTableModel dtm = adminPresenter.seeRequests();
        displayTable.setModel(dtm);
        scrollPane.repaint();
    }

    public String getNickname() {
        return nickname;
    }

    public CanvasView getCanvasView() {
        return canvasView;
    }
}

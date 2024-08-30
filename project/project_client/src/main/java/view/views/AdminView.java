package view.views;

import controller.AdminController;
import model.language.Language;
import view.resources.Palette;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminView extends JFrame {

    private JPanel mainPanel;
    private JButton approveButton;
    private JButton deleteButton;
    private JButton seeAllButton;
    private JButton seeRequestButton;
    private JTable displayTable;

    private JScrollPane scrollPane;

    private CanvasView canvasView;

    private AdminController adminController;

    private Language language;

    public AdminView(CanvasView cv){
        language = new Language(cv.getClient());
        adminController = new AdminController(this);
        canvasView = cv;
        displayTable = new JTable();
        displayTable.getSelectionModel().addListSelectionListener(adminController);
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
        this.setTitle(language.getAdminWindow());

        setJMenuBar(canvasView.getLanguageBar().getMenuBar());

        approveButton = setButton("<html>"+ language.getAcceptRequests() +"</html>");
        approveButton.setActionCommand("APPROVE");
        approveButton.addActionListener(adminController);
        springLayout.putConstraint(SpringLayout.WEST,approveButton,400,SpringLayout.WEST,mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH,approveButton,15,SpringLayout.NORTH,mainPanel);
        mainPanel.add(approveButton);

        deleteButton = setButton(language.getDelete());
        deleteButton.setActionCommand("DELETE");
        deleteButton.addActionListener(adminController);
        springLayout.putConstraint(SpringLayout.WEST,deleteButton,15,SpringLayout.EAST,approveButton);
        springLayout.putConstraint(SpringLayout.NORTH,deleteButton,15,SpringLayout.NORTH,mainPanel);
        mainPanel.add(deleteButton);

        seeAllButton = setButton(language.getSeeAll());
        seeAllButton.setActionCommand("SEE_ALL");
        seeAllButton.addActionListener(adminController);
        springLayout.putConstraint(SpringLayout.WEST,seeAllButton,15,SpringLayout.EAST,deleteButton);
        springLayout.putConstraint(SpringLayout.NORTH,seeAllButton,15,SpringLayout.NORTH,mainPanel);
        mainPanel.add(seeAllButton);

        seeRequestButton = setButton("<html>"+ language.getSeePending() +"</html>");
        seeRequestButton.setActionCommand("SEE_REQUESTS");
        seeRequestButton.addActionListener(adminController);
        springLayout.putConstraint(SpringLayout.WEST,seeRequestButton,15,SpringLayout.EAST,seeAllButton);
        springLayout.putConstraint(SpringLayout.NORTH,seeRequestButton,15,SpringLayout.NORTH,mainPanel);
        mainPanel.add(seeRequestButton);

        JButton backButton = setButton(language.getBackButton());
        backButton.setActionCommand("BACK");
        backButton.addActionListener(adminController);
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

    public void updateRepaint(){
        scrollPane.repaint();
    }

    public CanvasView getCanvasView() {
        return canvasView;
    }

    public void setModel(DefaultTableModel dtm) {
        displayTable.setModel(dtm);
    }

    public JTable getDisplayTable() {
        return displayTable;
    }
}

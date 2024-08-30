package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.language.Language;
import model.quiz.TestTableEntry;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import view.views.AccountView;
import view.views.TestView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Timestamp;

public class AccountController extends ComponentAdapter implements ActionListener {

    private AccountView accountView;

    private Language language;

    public AccountController(AccountView accountView){
        this.accountView = accountView;
        this.language = new Language(accountView.getCanvasView().getClient());
    }

    private void goBack(){
        accountView.getCanvasView().setVisible(true);
        accountView.dispose();
    }

    private void retrieveTests(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn(language.getIndex1());
        model.addColumn(language.getName());
        model.addColumn(language.getSurname());
        model.addColumn(language.getPoints());
        model.addColumn(language.getTimestamp());

        accountView.getCanvasView().getClient().sendMessage("GET_TESTS " + accountView.getNickname());

        String response = accountView.getCanvasView().getClient().receiveMessage();

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(response);

        TestTableEntry[] entries;

        try {
            entries = objectMapper.readValue(response, TestTableEntry[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        for (TestTableEntry e : entries) {
            model.addRow(new Object[]{e.getIndex(), e.getName(), e.getSurname(), e.getPoints(), e.getTimestamp()});
        }

        accountView.setModel(model);
    }

    public void takeTest(){
        accountView.getCanvasView().getClient().sendMessage("GET_ID " + accountView.getNickname());
        String result = accountView.getCanvasView().getClient().receiveMessage();
        int id = Integer.parseInt(result);
        accountView.setVisible(false);
        TestView tv = new TestView(id, accountView.getCanvasView());

        tv.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                accountView.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if(command.equals("BACK")){
            goBack();
        }else if(command.equals("TEST")){
            takeTest();
        }
        else if (command.equals("CHART")){
            generateChart();
        }
    }

    @Override
    public void componentShown(ComponentEvent e) {
        retrieveTests();
        accountView.updateScrollPane();
    }

    public void generateChart(){
        DefaultTableModel model = accountView.getModel();
        int rows = model.getRowCount();

        int[] pointsArray = new int[rows];
        long[] timestampArray = new long[rows];

        for(int i = 0; i < rows; i++){
            pointsArray[i] = (int) model.getValueAt(i, 3);
            timestampArray[i] = ((Timestamp) model.getValueAt(i, 4)).getTime();
        }

        for(int i=0;i<rows-1;i++)
            for(int j=i+1;j<rows;j++)
                if(timestampArray[i] > timestampArray[j]){
                    long aux = timestampArray[i];
                    timestampArray[i] = timestampArray[j];
                    timestampArray[j] = aux;
                    int aux2 = pointsArray[i];
                    pointsArray[i] = pointsArray[j];
                    pointsArray[j] = aux2;
                }

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Test Points");
        for (int i = 0; i < rows; i++) {
            series.add(timestampArray[i], pointsArray[i]);
        }
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart("Test Points", "timestamp", "points", dataset, PlotOrientation.VERTICAL,false, false, false);
        ChartFrame frame = new ChartFrame("Test Results", chart);
        ChartPanel panel = new ChartPanel(chart);
        frame.setSize(500, 500);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}

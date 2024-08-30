package view.resources;

import javax.swing.*;
import java.util.ArrayList;

public interface ITestView {
    void fillPanel(String question, ArrayList<String> options);
    void fillPanel(String question, ArrayList<String> options, ImageIcon img);
    JButton getEndTest();
    JButton getNextButton();
    JRadioButton getOption1();
    JRadioButton getOption2();
    JRadioButton getOption3();
    JRadioButton getOption4();
    int getSid();
}

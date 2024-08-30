package view.resources;

import model.language.Subject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class LanguageBar extends Subject implements ActionListener  {

    private JMenuBar menuBar;

    private int index;

    public LanguageBar(){
        index = 0;
        setMenuBar();
    }

    private void setMenuBar(){
        menuBar = new JMenuBar();

        JMenu languageMenu = new JMenu("Language");

        JMenuItem english = new JMenuItem("English");
        english.setMnemonic(KeyEvent.VK_E);
        english.setActionCommand("English");
        english.addActionListener(this);

        JMenuItem spanish = new JMenuItem("Spanish");
        spanish.setMnemonic(KeyEvent.VK_S);
        spanish.setActionCommand("Spanish");
        spanish.addActionListener(this);

        JMenuItem italian = new JMenuItem("Italian");
        italian.setMnemonic(KeyEvent.VK_I);
        italian.setActionCommand("Italian");
        italian.addActionListener(this);

        languageMenu.add(english);
        languageMenu.add(spanish);
        languageMenu.add(italian);

        menuBar.add(languageMenu);
    }

    public JMenuBar getMenuBar(){
        return menuBar;
    }

    public int getIndex(){
        return index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "English" -> index = 0;
            case "Italian" -> index = 1;
            case "Spanish" -> index = 2;
        }

        notifyObservers(index);
    }
}

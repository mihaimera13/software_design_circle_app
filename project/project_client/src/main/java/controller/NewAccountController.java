package controller;

import model.language.Language;
import view.views.NewAccountView;

import javax.swing.*;
import java.awt.event.ActionListener;

public class NewAccountController implements ActionListener {

    private NewAccountView newAccountView;

    private Language language;

    public NewAccountController(NewAccountView newAccountView) {
        this.newAccountView = newAccountView;
        language = new Language(newAccountView.getCanvasView().getClient());
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String action = e.getActionCommand();
        if ("REQUEST".equals(action)) {
            request();
        }
    }

    public void request() {
        String name = newAccountView.getNameField();
        String surname = newAccountView.getSurnameField();
        String nickname = newAccountView.getNicknameField();
        String password = newAccountView.getPasswordField();

        newAccountView.getCanvasView().getClient().sendMessage("NEWACCOUNT " + nickname + " " + password + " " + name + " " + surname);

        String result = newAccountView.getCanvasView().getClient().receiveMessage();

        switch (result) {
            case "OK" -> {
                JOptionPane.showMessageDialog(newAccountView, language.getRequestSent(), language.getRequestTitle(), JOptionPane.PLAIN_MESSAGE);
                newAccountView.getCanvasView().setVisible(true);
                newAccountView.dispose();
            }
            case "ERROR_NAME_USED" ->
                    JOptionPane.showMessageDialog(newAccountView, language.getEmptyFields(), language.getTitleError(), JOptionPane.ERROR_MESSAGE);
            case "ERROR_EMPTY_FIELDS" ->
                    JOptionPane.showMessageDialog(newAccountView, language.getNameUsed(), language.getTitleError(), JOptionPane.ERROR_MESSAGE);
        }

        reset();
    }

    public void reset() {
        newAccountView.setNameField("");
        newAccountView.setSurnameField("");
        newAccountView.setNicknameField("");
        newAccountView.setPasswordField("");
    }
}

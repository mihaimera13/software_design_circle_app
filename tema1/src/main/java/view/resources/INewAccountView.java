package view.resources;

import view.views.CanvasView;

import javax.swing.*;

public interface INewAccountView {
    JTextField getNameField();
    JTextField getSurnameField();
    JTextField getNicknameField();
    JPasswordField getPasswordField();
    CanvasView getCanvasView();
}

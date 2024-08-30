package view.resources;

import view.views.CanvasView;

import javax.swing.*;

public interface ILoginView {
    String getPasswordField();
    void setNicknameTextField(String text);
    void setPasswordTextField(String text);
    JLabel getErrorLabel();
    CanvasView getCanvasView();
}

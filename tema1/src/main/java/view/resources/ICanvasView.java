package view.resources;

import view.views.CanvasView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

public interface ICanvasView {

    void setBarLabel(String text);

    DrawingCanvas getCanvasPanel();

    JSlider getSlider();

}

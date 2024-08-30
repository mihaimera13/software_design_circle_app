package model.language;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Subject {

    protected ArrayList<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(int index) {
        for (Observer observer : observers) {
            observer.update(index);
        }
    }
}

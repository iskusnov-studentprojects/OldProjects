package com.interfaces;

/**
 * Created by Сергей on 05.02.2016.
 */
public interface Publisher {
    public void addObserver(Observer obs);
    public void removeObserber(Observer obs);
    public void notifyObserbers();
}

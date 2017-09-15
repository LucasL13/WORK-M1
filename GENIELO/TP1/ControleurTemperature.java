package TP1;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by work on 15/09/17.
 */
public class ControleurTemperature extends Observable implements Observer {

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }



    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
    }
}

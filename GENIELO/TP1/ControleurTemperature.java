package TP1;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by work on 15/09/17.
 */
public class ControleurTemperature extends Observable implements Observer {

    private ModeleTemperature model;


    public ControleurTemperature(ModeleTemperature MT){
        this.model = MT;
    }

    @Override
    public void update(Observable o, Object arg) {

        if(arg.getClass() == String.class) {

            String BTN_NAME = (String) arg;

            if (BTN_NAME == Constantes.BTN_PLUSF) {
                model.setTempF(model.getTemperatureF() + 1d);
            } else if (BTN_NAME == Constantes.BTN_MOINSF) {
                model.setTempF(model.getTemperatureF() - 1d);
            }
            else if (BTN_NAME == Constantes.BTN_PLUSC) {
                model.setTempC(model.getTemperatureC() + 1d);
            } else if (BTN_NAME == Constantes.BTN_MOINSC) {
                model.setTempC(model.getTemperatureC() - 1d);
            }

        }
        else if(arg.getClass() == JScrollBar.class) {
            JScrollBar sb = (JScrollBar) arg;

            if (sb.getName() == Constantes.SCROLL_F) {
                model.setTempF(sb.getValue());
            } else if (sb.getName() == Constantes.SCROLL_C) {
                model.setTempC(sb.getValue());
            }
        }

        setChanged();
        notifyObservers();
    }



}

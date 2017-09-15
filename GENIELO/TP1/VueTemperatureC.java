package TP1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.util.Observable;

/**
 * Created by work on 15/09/17.
 */
public class VueTemperatureC extends VueTemperature {

    protected void createWindow() {
        super.createWindow("Celsius", Constantes.BTN_PLUSC, Constantes.BTN_MOINSC, Constantes.SCROLL_C);
        scroll.setMinimum(-110);
        scroll.setMaximum(100);
    }

    @Override
    public void update(Observable o, Object arg) {
        ModeleTemperature MT = (ModeleTemperature) arg;
        tempField.setText("" + MT.getTemperatureC() + "°");
        scroll.setValue((int) MT.getTemperatureC());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String BTN_NAME = ((JButton) e.getSource()).getName();
        setChanged();

        if(BTN_NAME == Constantes.BTN_PLUSC){
            notifyObservers(Constantes.BTN_PLUSC);
        }
        else if(BTN_NAME == Constantes.BTN_MOINSC){
            notifyObservers(Constantes.BTN_MOINSC);
        }
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        setChanged();
        notifyObservers(scroll);
    }
}

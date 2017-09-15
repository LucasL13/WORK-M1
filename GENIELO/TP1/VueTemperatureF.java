package TP1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.util.Observable;

public class VueTemperatureF extends VueTemperature {

    protected void createWindow() {
        super.createWindow("Farenheit", Constantes.BTN_PLUSF, Constantes.BTN_MOINSF, Constantes.SCROLL_F);
        scroll.setMinimum(-200);
        scroll.setMaximum(200);
    }

    @Override
    public void update(Observable o, Object arg) {
        ModeleTemperature MT = (ModeleTemperature) arg;
        tempField.setText("" + MT.getTemperatureF() + "°");
        scroll.setValue((int) MT.getTemperatureF());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String BTN_NAME = ((JButton) e.getSource()).getName();
        setChanged();

        if(BTN_NAME == Constantes.BTN_PLUSF){
            notifyObservers(Constantes.BTN_PLUSF);
        }
        else if(BTN_NAME == Constantes.BTN_MOINSF){
            notifyObservers(Constantes.BTN_MOINSF);
        }
    }


    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        setChanged();
        notifyObservers(scroll);
    }
}

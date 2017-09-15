package TP1;

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
        tempField.setText("" +  ((int) (MT.getTemperatureC() * 1000))/1000d  + "°");
        scroll.setValue((int) MT.getTemperatureC());
    }

}

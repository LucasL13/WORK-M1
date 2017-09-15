package TP1;

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
        tempField.setText("" + ((int) (MT.getTemperatureF() * 1000))/1000d + "°");
        scroll.setValue((int) MT.getTemperatureF());
    }
}

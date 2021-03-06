package TP1;

/**
 * Created by work on 15/09/17.
 */
public class Test {

    public static void main(String[] args) {
        VueTemperatureF VTF = new VueTemperatureF();
        VueTemperatureC VTC = new VueTemperatureC();
        ModeleTemperature MT = new ModeleTemperature();
        ControleurTemperature CT = new ControleurTemperature(MT);

        VTF.createWindow();
        VTC.createWindow();

        VTF.addObserver(CT);
        VTC.addObserver(CT);

        MT.addObserver(VTF);
        MT.addObserver(VTC);

        CT.addObserver(MT);
    }
}

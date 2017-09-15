package TP1;

/**
 * Created by work on 15/09/17.
 */
public class Test {

    public static void main(String[] args) {
        VueTemperature VT = new VueTemperature();
        ModeleTemperature MT = new ModeleTemperature();
        ControleurTemperature CT = new ControleurTemperature();

        VT.createWindow();

        MT.addObserver(VT);
        VT.addObserver(CT);
        CT.addObserver(MT);


    }
}

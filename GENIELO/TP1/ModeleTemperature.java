package TP1;

import sun.plugin.javascript.JSClassLoader;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class ModeleTemperature extends Observable implements Observer{

    @Override
    public void update(Observable o, Object arg) {

        if(arg.getClass() == String.class) {

            String BTN_NAME = (String) arg;

            setChanged();

            if (BTN_NAME == Constantes.BTN_PLUSF) {
                setTempF(this.getTemperatureF() + 1d);
            } else if (BTN_NAME == Constantes.BTN_MOINSF) {
                setTempF(this.getTemperatureF() - 1d);
            } else if (BTN_NAME == Constantes.BTN_PLUSC) {
                setTempC(this.getTemperatureC() + 1d);
            } else if (BTN_NAME == Constantes.BTN_MOINSC) {
                setTempC(this.getTemperatureC() - 1d);
            }

        }
        else if(arg.getClass() == JScrollBar.class){

            JScrollBar sb = (JScrollBar) arg;

            if(sb.getName() == Constantes.SCROLL_F){
                this.setTempF(sb.getValue());
            }
            else if(sb.getName() == Constantes.SCROLL_C){
                this.setTempC(sb.getValue());
            }
        }


        System.out.println("New temp = " + this.getTemperatureF());

    }

    private double tempF = 32;// Temperature en Farenheit
    ModeleTemperature(){
        super();
    }

    public double getTemperatureF(){
        return this.tempF;
    }

    public double getTemperatureC(){
        double tempC = ((this.tempF-32.0)*5.0)/9.0;
        tempC = ((int)(tempC * 1000))/1000d;
        return (tempC);
    }


    public void setTempF(double tempF){
        this.tempF = tempF;
        this.setChanged(); // valider les modifications

        this.notifyObservers(this);// signaler les modifications
    }


    public void setTempC(double tempC){
        this.tempF = (((tempC*9)/5.0) + 32.0);
        this.tempF = ((int)(this.tempF * 1000))/1000d;

        this.setChanged();

        this.notifyObservers(this);
    }
}
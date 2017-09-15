package TP1;

import java.util.*;

public class ModeleTemperature extends Observable implements Observer{

    @Override
    public void update(Observable o, Object arg) {

        System.out.println("Old temp = " + this.getTemperatureF());

        String BTN_NAME = (String) arg;

        setChanged();

        if(BTN_NAME == Constantes.BTN_PLUSF){
            setTempF(this.getTemperatureF()+1);
        }
        else if(BTN_NAME == Constantes.BTN_MOINSF) {
            setTempF(this.getTemperatureF()-1);
        }
        else if(BTN_NAME == Constantes.BTN_PLUSC){
            setTempC(this.getTemperatureC()+1);
        }
        else if(BTN_NAME == Constantes.BTN_MOINSC){
            setTempC(this.getTemperatureC()-1);
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
        return (tempC);
    }


    public void setTempF(double tempF){
        this.tempF = tempF;
        this.setChanged(); // valider les modifications

        ArrayList<String> temp = new ArrayList<String>();
        temp.add(Constantes.TEMP_F);
        temp.add(""+this.getTemperatureF());

        this.notifyObservers(temp);// signaler les modifications
    }


    public void setTempC(double tempC){
        this.tempF = (((tempC*9)/5.0) + 32.0);
        this.setChanged();

        ArrayList<String> temp = new ArrayList<String>();
        temp.add(Constantes.TEMP_C);
        temp.add(""+this.getTemperatureC());

        this.notifyObservers(temp);
    }
}
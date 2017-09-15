package TP1;

import java.util.*;

public class ModeleTemperature extends Observable implements Observer{

    private double tempF = 32;

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
        this.notifyObservers(this);// signaler les modifications
    }


    public void setTempC(double tempC){
        this.tempF = (((tempC*9)/5.0) + 32.0);
        this.setChanged();
        this.notifyObservers(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(this);
    }

}
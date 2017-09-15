package TP1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;




public class VueTemperature extends Observable implements Observer, ActionListener {

    private JFrame window;
    private JPanel boutons1, boutons2;

    private JButton plusF, moinsF, plusC, moinsC;

    private GridLayout BL;

    private JLabel Farenheit, Celsius;
    private JTextField FarenheitTemp, CelsiusTemp;

    private double tempF;

    protected void createWindow(){
        window = new JFrame("Temperature");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setPreferredSize(new Dimension(200,200));
        BL = new GridLayout(0,1);

        boutons1 = new JPanel(new GridLayout(0,2));
        boutons2 = new JPanel(new GridLayout(0,2));

        plusF = new JButton("+");
        moinsF = new JButton("-");

        plusF.addActionListener(this);
        moinsF.addActionListener(this);

        plusF.setName(Constantes.BTN_PLUSF);
        moinsF.setName(Constantes.BTN_MOINSF);

        boutons1.add(plusF);
        boutons1.add(moinsF);

        Farenheit = new JLabel("Farenheit");
        Celsius = new JLabel("Celsius");

        FarenheitTemp = new JTextField("");
//        CelsiusTemp = new JTextField("" + MT.getTemperatureC());

        window.setLayout(BL);

        window.add(Farenheit);
        window.add(FarenheitTemp);
        window.add(boutons1);

        window.pack();
        window.setVisible(true);

        notifyObservers();
    }


    @Override
    public void update(Observable o, Object arg) {

        ArrayList<String> temp = (ArrayList<String>) arg;
        System.out.println("J'ai recu les new temps : " + temp.get(0)+ " : " + temp.get(1));

        tempF = Double.parseDouble(temp.get(1));

        FarenheitTemp.setText("" + tempF + "°");
    }




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

}

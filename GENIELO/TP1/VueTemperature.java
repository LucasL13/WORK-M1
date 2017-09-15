package TP1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;


public class VueTemperature extends Observable implements Observer, ActionListener, AdjustmentListener {

    private JFrame window;
    private JPanel boutons;

    protected JButton plus, moins;
    protected JScrollBar scroll;

    private GridLayout BL;

    private JLabel tempLabel;
    protected JTextField tempField;

    private double tempF = 0;

    private String WINDOW_ID;
    private String BTN_PLUS_ID;
    private String BTN_MOINS_ID;

    protected void createWindow(String title, String BTN_PLUS_ID, String BTN_MOINS_ID, String SCROLL_ID){

        this.WINDOW_ID = title;
        this.BTN_PLUS_ID = BTN_PLUS_ID;
        this.BTN_MOINS_ID = BTN_MOINS_ID;

        window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setPreferredSize(new Dimension(200,200));
        BL = new GridLayout(0,1);

        boutons = new JPanel(new GridLayout(0,2));

        plus = new JButton("+");
        moins = new JButton("-");

        plus.addActionListener(this);
        moins.addActionListener(this);

        plus.setName(BTN_PLUS_ID);
        moins.setName(BTN_MOINS_ID);

        scroll = new JScrollBar(JScrollBar.HORIZONTAL);
        scroll.setValue(0);
        scroll.setName(SCROLL_ID);
        scroll.addAdjustmentListener(this);

        boutons.add(plus);
        boutons.add(moins);

        tempLabel = new JLabel(title);

        tempField = new JTextField(""+tempF);
        window.setLayout(BL);

        window.add(tempLabel);
        window.add(tempField);
        window.add(boutons);
        window.add(scroll);

        window.pack();
        window.setVisible(true);

        notifyObservers();
    }


    @Override
    public void update(Observable o, Object arg) {
        // A completer dans les classes héritées
    }


    public void actionPerformed(ActionEvent e) {
        // A completer dans les classes héritées
    }


    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        // A completer dans les classes héritées
    }
}

package Multijoueurs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

/**
 * Created by work on 23/11/17.
 */

public class Client extends AbstractGraphicClient implements ActionListener{

    public Client(){
        this.frame = new JFrame("Le jeu du mot caché");
        this.frame.setSize(600,400);
        this.frame.setPreferredSize(new Dimension(600,300));
        this.frame.setLocationRelativeTo(null);

        this.frame.setLayout(new BoxLayout(this.frame.getContentPane(), BoxLayout.PAGE_AXIS));

        this.letters_text = new JLabel(letters_text_text);
        this.letters = new JLabel("_______");

        this.panel_bottom = new JPanel();
        this.panel_bottom.setBackground(new Color(120,120,120));
        this.panel_bottom.setMaximumSize(new Dimension(600, 140));
        this.panel_bottom.setBorder(BorderFactory.createEmptyBorder(40,0,30,0));
        this.panel_bottom.setOpaque(true);

        this.letter_proposal = new JTextField();
        this.letter_proposal.setMaximumSize(new Dimension(80,30));
        this.letter_proposal.setMinimumSize(new Dimension(80,80));
        this.letter_proposal.setPreferredSize(new Dimension(80,25));
        this.letter_proposal.setBackground(Color.WHITE);
        this.letter_proposal.setFont(new Font(this.letters.getFont().getName(), Font.BOLD, 20));
        this.letter_proposal.setOpaque(true);

        this.letter_submit = new JButton("OK");
        this.letter_submit.setBackground(new Color(30,30,30));
        this.letter_submit.setForeground(Color.WHITE);
        this.letter_submit.setOpaque(true);
        this.letter_submit.addActionListener(this);

        this.panel_bottom.add(this.letter_proposal);
        this.panel_bottom.add(this.letter_submit);

        this.letters_text.setBackground(new Color(70,70,70));
        this.letters_text.setMaximumSize(new Dimension(600,80));
        this.letters_text.setHorizontalAlignment(JLabel.CENTER);
        this.letters_text.setVerticalAlignment(JLabel.BOTTOM);
        this.letters_text.setOpaque(true);
        this.letters_text.setForeground(Color.WHITE);

        this.letters.setBackground(new Color(70,70,70));
        this.letters.setMaximumSize(new Dimension(600,80));
        this.letters.setHorizontalAlignment(JLabel.CENTER);
        this.letters.setVerticalAlignment(JLabel.BOTTOM);
        this.letters.setOpaque(true);
        this.letters.setForeground(Color.WHITE);
        this.letters.setFont(new Font(this.letters.getFont().getName(), Font.PLAIN, 40));

        this.letters_text.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.letters.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.letters_text.setBorder(BorderFactory.createEmptyBorder( 0 /*top*/, 0, 30, 0 ));
        this.letters.setBorder(BorderFactory.createEmptyBorder( 0 /*top*/, 0, 30, 0 ));

        this.frame.add(this.letters_text);
        this.frame.add(this.letters);
        this.frame.add(this.panel_bottom);

        this.frame.pack();
        this.frame.setVisible(true);
    }

    @Override
    protected void update_word() {

    }

    @Override
    protected void send_word() {

    }

    @Override
    protected void display_failure() {

    }

    @Override
    protected void display_success() {

    }

    @Override
    void stopSocket(Socket soc) {

    }

    public static void main(String[] args) {
        Client client = new Client();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Lettre choisie : " + this.letter_proposal.getText());

        String letter = this.letter_proposal.getText();
        if(letter.length() > 1){
            letter = "" + letter.charAt(0);
            this.letter_proposal.setText(letter);
            JOptionPane.showMessageDialog(frame, "Un seul caractère à la fois", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        else{
            this.letter_proposal.setBackground(Color.GREEN);
        }
    }
}

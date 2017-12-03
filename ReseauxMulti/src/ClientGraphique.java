import com.sun.org.apache.xml.internal.utils.CharKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.Key;

import static java.lang.System.exit;

/**
 * Created by work on 02/12/17.
 */
public class ClientGraphique extends Client implements ActionListener, KeyListener{

    protected final String letters_text_text = "LE MOT CACHÉ : ";

    private Thread lecture;

    private void initUI(){
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
        this.letter_proposal.addKeyListener(this);

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

    public ClientGraphique(){
        this.serveurAddress = DEFAULT_ADDR;
        this.serveurPort = DEFAULT_PORT;
        initUI();
    }

    private ClientGraphique(int serveurPort){
        this.serveurAddress = DEFAULT_ADDR;
        this.serveurPort = serveurPort;
        initUI();
    }

    private ClientGraphique(String serveurAdr, int serveurPort){
        this.serveurAddress = serveurAdr;
        this.serveurPort = serveurPort;
        initUI();
    }

    protected JFrame        frame;
    protected Socket        sock;

    protected JLabel        letters;
    protected JLabel        letters_text;

    protected JTextField    letter_proposal;
    protected JButton       letter_submit;

    protected JPanel        panel_top;
    protected JPanel        panel_bottom;

    @Override
    protected void se_connecter() {
        try {
            sock = new Socket(this.serveurAddress, this.serveurPort);
            connecte = true;
        }catch(Exception e){e.printStackTrace();}
    }

    @Override
    protected void receive_message(String message) {
        EOC = (message.contains(END_OF_COMMUNICATION));
        SAL = (message.contains(SEND_A_LETTER));
        EGW = (message.contains(ENDGAME_WIN));
        EGL = (message.contains(ENDGAME_LOSE));
        EWC = (message.contains(ERROR_WRONGCHAR));
        LWS = (message.contains(LASTWORD_SUCCESS));
        LWF = (message.contains(LASTWORD_FAILED));
        GET = (message.contains(GET_WORD));

        if(EOC){
            String motcache = message.substring(message.indexOf(END_OF_COMMUNICATION)+END_OF_COMMUNICATION.length(), message.length());
            this.letters.setText(motcache);
        }

        if(SAL)
            message = message.replace(SEND_A_LETTER, "");

        if(EGW) {
            Object[] options = {"Rejouer","Quitter"};
            int choix = JOptionPane.showOptionDialog(frame, "Vous avez gagné félicitations !", "VICTOIRE", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, "Quitter");
            if(choix == 0)
                rejouer();
            else if(choix == 1)
                stopClient();
        }
        else if(EGL){
            Object[] options = {"Rejouer","Quitter"};
            int choix = JOptionPane.showOptionDialog(frame, "Vous avez perdu !\nVous aurez plus de chance la prochaine fois", "DEFAITE", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, "Quitter");
            if(choix == 0)
                rejouer();
            else if(choix == 1)
                stopClient();
        }

        if(EWC){
            message = message.replace(ERROR_WRONGCHAR, "");
            JOptionPane.showMessageDialog(frame, "Le caractere envoyé n'est pas correct\n Les seuls caracteres autorisés sont [a-zA-Z]", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        if(LWS){
            int parsing = message.indexOf(GET_WORD);
            String secondPart = message.substring(parsing+GET_WORD.length(), message.length());
            this.letter_proposal.setBackground(Color.GREEN);
            this.letters.setText(secondPart);
            this.letter_proposal.setText("");
        }
        else if(LWF){
            int parsing = message.indexOf(GET_WORD);
            String secondPart = message.substring(parsing+GET_WORD.length(), message.length());
            this.letter_proposal.setBackground(Color.RED);
            this.letters.setText(secondPart);
            this.letter_proposal.setText("");
        }

        System.out.println(message);

//        if(SAL && !EOC && !EGW && !EGL)
//            read_letterInput();
//        if(EWC)
//            read_letterInput();
    }

    @Override
    protected void jouer() {
        lecture = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                    while(connecte){
                        String message = "";
                        while(br.ready()){
                            message += (char) br.read();
                        }

                        if(!message.equals(""))
                            receive_message(message);
                    }
                }catch (Exception e){ e.printStackTrace(); }
            }
        });

        lecture.start();
    }

    @Override
    protected void stopClient() {
        try{
            connecte = false;
            sock.close();
            exit(0);

        }catch (IOException e){ e.printStackTrace(); }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_ENTER)
            if(checkInput())
                sendMessage(sock, this.letter_proposal.getText()+"");
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Lettre choisie : " + this.letter_proposal.getText());
        if(checkInput())
            sendMessage(sock, this.letter_proposal.getText()+"");
    }


    private boolean checkInput(){
        String letter = this.letter_proposal.getText();
        if(letter.length() > 1){
            letter = "" + letter.charAt(0);
            this.letter_proposal.setText(letter);
            JOptionPane.showMessageDialog(frame, "Un seul caractère à la fois", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        else{
            this.letter_proposal.setBackground(Color.GREEN);
            return true;
        }
        return false;
    }

    private void rejouer(){
        se_connecter();
        jouer();
        sendMessage(sock, "&");
    }

    public static void main(String[] args) {
        ClientGraphique c = new ClientGraphique();
        c.se_connecter();
        c.jouer();
    }
}

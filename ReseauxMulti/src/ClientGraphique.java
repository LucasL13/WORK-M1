/**
    Nom du binôme : LOIGNON Lucas et FAUCONNIER Axel

    Description de la classe : Programme qui permet de lancer un client en mode graphique

    Fonctionnalités :
        -> Se connecte au serveur
        -> Recoit et envoie des messages pour le jeu
        -> Traite les messages reçus et affiche sur la console
        -> Récupere les entrées du joueur sur l'entrée standard

    Dependances :   Client : la classe abstraite qui définit les variables, constantes et fonctions génériques d'un client
                    (-> ReseauxToolbox : une classe abstraite qui fournit des méthodes pour faciliter les communications réseaux)

 **/

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

import static java.lang.System.exit;

public class ClientGraphique extends Client implements ActionListener, KeyListener{

    protected final String letters_text_text = "LE MOT CACHÉ : ";

    private Thread lecture;                         // Un thread pour scruter en continu l'entrée réseau
    private boolean connex;                         // Un indicateur de la réussite de la connexion avec le serveur

    protected JFrame        frame;                  // La fenetre graphique principale

    protected JLabel        letters;                // Le champ qui affiche le mot caché en cours
    protected JLabel        letters_text;           // Le champ qui affiche "Le mot caché : " au dessus

    protected JTextField    letter_proposal;        // Le champ ou le joueur rentre sa lettre
    protected JButton       letter_submit;          // Le boutton pour valider l'envoi
    protected JTextField    tentatives_restantes;   // Le champ qui affiche le nombre de tentatives restantes

    protected JPanel        panel_bottom;           // Une sous-fenetre pour agencer la partie du bas


    // Une étape préliminaire pour afficher une fenêtre permettant de choisir le serveur et le port
    private boolean initConnex(){
        try{
            String connex = JOptionPane.showInputDialog(this.frame, "Adresse du serveur", "127.0.0.1:5500");
            this.serveurAddress = connex.split(":")[0];
            this.serveurPort = Integer.parseInt(connex.split(":")[1]);
        } catch (Exception e) {
            return false;
        }

        System.out.print("Tentative de connexion à " + this.serveurAddress);
        System.out.println(" sur le port " + this.serveurPort);

        se_connecter();
        if(!connex){
            JOptionPane.showMessageDialog(this.frame, "Erreur : connexion impossible");
            return false;
        }
        initUI();
        jouer();

        return true;
    }


    // Une fonction pour mettre en place graphiquement les éléments de la fenetre principale
    private void initUI(){

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

        this.tentatives_restantes = new JTextField("Tentatives restantes : ");
        this.tentatives_restantes.setBackground(new Color(70,70,70));
        this.tentatives_restantes.setForeground(Color.white);
        this.tentatives_restantes.setHorizontalAlignment(JTextField.CENTER);
        this.tentatives_restantes.setFont(new Font(this.letters.getFont().getName(), Font.BOLD, 15));
        this.tentatives_restantes.setBorder(BorderFactory.createLineBorder(Color.white, 0));

        this.panel_bottom.add(this.letter_proposal);
        this.panel_bottom.add(this.letter_submit);

        this.letters_text.setBackground(new Color(70,70,70));
        this.letters_text.setMaximumSize(new Dimension(600,50));
        this.letters_text.setHorizontalAlignment(JLabel.CENTER);
        this.letters_text.setVerticalAlignment(JLabel.BOTTOM);
        this.letters_text.setOpaque(true);
        this.letters_text.setForeground(Color.WHITE);

        this.letters.setBackground(new Color(70,70,70));
        this.letters.setMaximumSize(new Dimension(600,20));
        this.letters.setHorizontalAlignment(JLabel.CENTER);
        this.letters.setVerticalAlignment(JLabel.BOTTOM);
        this.letters.setOpaque(true);
        this.letters.setForeground(Color.WHITE);
        this.letters.setFont(new Font(this.letters.getFont().getName(), Font.PLAIN, 40));

        this.letters_text.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.letters.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.letters_text.setBorder(BorderFactory.createEmptyBorder( 30 , 0, 15, 0 ));
        this.letters.setBorder(BorderFactory.createEmptyBorder( 0 , 0, 30, 0 ));

        this.frame.add(this.letters_text);
        this.frame.add(this.letters);
        this.frame.add(this.panel_bottom);
        this.frame.add(this.tentatives_restantes);

        this.frame.pack();
        this.frame.setVisible(true);
    }


    public ClientGraphique(){
        this.serveurAddress = DEFAULT_ADDR;
        this.serveurPort = DEFAULT_PORT;

        this.frame = new JFrame("Le jeu du mot caché");
        this.frame.setSize(600,400);
        this.frame.setPreferredSize(new Dimension(600,300));
        this.frame.setLocationRelativeTo(null);
        this.frame.getContentPane().setBackground(new Color(70,70,70));
        this.frame.pack();
        this.frame.setVisible(true);

    }

    private ClientGraphique(int serveurPort){
        this.serveurAddress = DEFAULT_ADDR;
        this.serveurPort = serveurPort;

        this.frame = new JFrame("Le jeu du mot caché");
        this.frame.setSize(600,400);
        this.frame.setPreferredSize(new Dimension(600,300));
        this.frame.setLocationRelativeTo(null);
        this.frame.getContentPane().setBackground(new Color(70,70,70));
        this.frame.pack();
        this.frame.setVisible(true);

    }

    private ClientGraphique(String serveurAdr, int serveurPort){
        this.serveurAddress = serveurAdr;
        this.serveurPort = serveurPort;

        this.frame = new JFrame("Le jeu du mot caché");
        this.frame.setSize(600,400);
        this.frame.setPreferredSize(new Dimension(600,300));
        this.frame.setLocationRelativeTo(null);
        this.frame.getContentPane().setBackground(new Color(70,70,70));
        this.frame.pack();
        this.frame.setVisible(true);

    }


    // Une fonction pour établir la connexion avec le serveur de jeu
    @Override
    protected void se_connecter() {
        try {
            sock = new Socket(this.serveurAddress, this.serveurPort);
            connecte = true;
            connex = true;
        }catch(Exception e){
            connex = false;
        }
    }

    // Une fonction qui analyse et traite le message reçu du serveur
    // Affiche les informations utiles et répond au serveur si nécéssaire
    @Override
    protected void receive_message(String message) {

        System.out.println(message);

        // On vérifie la présence ou non de champs protocolaires dans le message
        EOC = (message.contains(END_OF_COMMUNICATION));
        SAL = (message.contains(SEND_A_LETTER));
        EGW = (message.contains(ENDGAME_WIN));
        EGL = (message.contains(ENDGAME_LOSE));
        EWC = (message.contains(ERROR_WRONGCHAR));
        LWS = (message.contains(LASTWORD_SUCCESS));
        LWF = (message.contains(LASTWORD_FAILED));
        GET = (message.contains(GET_WORD));

        if(message.indexOf("tentatives ! Bonne chance") != -1){
            int in = message.indexOf("_ en");
            int out = message.indexOf("tentatives ! Bonne chance");
            this.tentatives_restantes.setText("Tentatives restantes : " + message.substring(in+5, out-1));
        }

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
            int parsingLWS = message.indexOf(LASTWORD_SUCCESS);
            String firstPart = message.substring(parsingLWS+LASTWORD_SUCCESS.length(), parsing);
            String secondPart = message.substring(parsing+GET_WORD.length(), message.length());
            this.letter_proposal.setBackground(Color.GREEN);
            this.letters.setText(secondPart);
            this.letter_proposal.setText("");
            this.tentatives_restantes.setText("Tentatives restantes : " + firstPart);

            message = "Tentatives restantes : " + firstPart + "\nMot caché : " + secondPart;
        }
        else if(LWF){
            int parsing = message.indexOf(GET_WORD);
            int parsingLWF = message.indexOf(LASTWORD_FAILED);
            String firstPart = message.substring(parsingLWF+LASTWORD_FAILED.length(), parsing);
            String secondPart = message.substring(parsing+GET_WORD.length(), message.length());
            this.letter_proposal.setBackground(Color.RED);
            this.letters.setText(secondPart);
            this.letter_proposal.setText("");
            this.tentatives_restantes.setText("Tentatives restantes : " + firstPart);

            message = "Tentatives restantes : " + firstPart + "\nMot caché : " + secondPart;
        }

        System.out.println(message);

//        if(SAL && !EOC && !EGW && !EGL)
//            read_letterInput();
//        if(EWC)
//            read_letterInput();
    }


    // Une fonction à implementer selon le client pour le déroulement et l'affichage de la partie
    // Lance un thread chargé d'écouter en continu et de faire traiter le message par "receive_message"
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


    // Une fonction à implementer selon le client pour fermer la socket et quitter le programme
    @Override
    protected void stopClient() {
        try{
            connecte = false;
            sock.close();
            exit(0);
        }catch (IOException e){ e.printStackTrace(); }
    }

    // Une fonction pour récuperer l'evenement "touche entrée" pour valider l'envoi de la lettre choisie
    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_ENTER)
            if(checkInput())
                sendMessage(sock, this.letter_proposal.getText()+"");
    }

    // Inutilisée (Imposée par l'interface KeyListenner)
    @Override
    public void keyPressed(KeyEvent e) {}

    // Inutilisée (Imposée par l'interface KeyListenner)
    @Override
    public void keyReleased(KeyEvent e) {}

    // Une fonction pour traiter le clic sur le bouton  "OK" qui doit envoyer la lettre choisie
    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkInput())
            sendMessage(sock, this.letter_proposal.getText()+"");
    }


    // Une fonction pour valider le contenu du champ "lettre a envoyer"
    // Vérifie qu'il n'y ai qu'un seul caractere et qu'il soit [a-zA-Z]
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


    // Une fonction pour relancer une partie une fois celle en cours terminée
    private void rejouer(){
        se_connecter();
        jouer();
        sendMessage(sock, "&");
    }


    // La fonction main pour lancer le client graphique et le connecter une fois une adresse et un port de connexion valide obtenus (la connexion doit également réussir)
    public static void main(String[] args) {
        ClientGraphique c = new ClientGraphique();
        boolean connex = c.initConnex();
        while(!connex)
            connex = c.initConnex();
    }
}

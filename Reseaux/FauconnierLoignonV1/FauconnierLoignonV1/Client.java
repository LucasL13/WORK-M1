/**
    Nom du binôme : LOIGNON Lucas et FAUCONNIER Axel

    Description de la classe : Client de jeu

    Fonctionnalités :
        - Se connecte au serveur de jeu via les paramètres par défaut (localhost:5500)
        - Reçoit les informations du serveur pour le jeu et les affiche
        - Envoi les informations entrées par le joueur au serveur de jeu

    Dependances : ReseauxToolbox : une classe abstraite qui fournit des méthodes pour faciliter les communications réseaux

 **/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client extends ReseauxToolbox {

    private boolean connecte;       // Indicateur de connexion avec le serveur
    private Socket sock;            // Socket de communication avec le serveur de jeu
    private Thread lecture;         // Un thread pour lire à chaque fois que possible dans le buffer in

    private String address;			// Adresse du serveur
    private int port;				// Port pour se connecter

    // Une fonction pour établir la connexion avec le serveur de jeu
    private void se_connecter(){
        try {
            sock = new Socket(this.address, this.port);
            connecte = true;
        }catch(Exception e){e.printStackTrace();}
    }

    // Une fonction qui analyse et traite le message reçu du serveur
    // Lit sur l'entrée standard et envoi au serveur lorsque ce dernier attend une lettre
    private void receive_message(String message){

        boolean EOC = (message.indexOf(END_OF_COMMUNICATION) != -1);
        boolean SAL = (message.indexOf(SEND_A_LETTER) != -1);

        if(EOC)
            message = message.replace(END_OF_COMMUNICATION, "");
        if(SAL)
            message = message.replace(SEND_A_LETTER, "");

        System.out.println(message);

       if(SAL && !EOC){
            System.out.print("Entrez une lettre : ");
            Scanner sc = new Scanner(System.in);
            char c = sc.next().charAt(0);
            sendMessage(sock, c+"");
        }

        if(EOC)
            stopSocket(sock);
    }


    // La fonction pour lancer le jeu une fois la connexion établie
    // Crée un thread parallèle pour la lecture permanente du buffer d'entrée de la socket
    private void jouer(){
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


    // Fonction de fermeture de la socket du client et d'arrêt du programme
    // Imposée par la ReseauxToolbox
    @Override
    void stopSocket(Socket soc) {
        System.out.println("Le serveur a terminé la connexion. Fin du programme client.");
        connecte = false;
        if( !soc.equals(sock)) return;
        try{
            sock.close();
        }catch (Exception e){e.printStackTrace();}
        exit(0);
    }

    Client(){
        this.address = "localhost";
        this.port = 5500;
        se_connecter();
        jouer();
    }

    Client(String add, int port){
        this.address = add;
        this.port = port;
        se_connecter();
        jouer();
    }

    public static void main(String[] args) {
        Client c = new Client();
    }

}

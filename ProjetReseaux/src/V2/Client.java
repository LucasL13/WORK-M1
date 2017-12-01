/**
    Nom du binôme : LOIGNON Lucas et FAUCONNIER Axel

    Description de la classe : Client de jeu

    Fonctionnalités :
        - Se connecte au serveur de jeu via les paramètres par défaut (localhost:5500)
        - Reçoit les informations du serveur pour le jeu et les affiche
        - Envoi les informations entrées par le joueur au serveur de jeu

    Dependances : ReseauxToolbox : une classe abstraite qui fournit des méthodes pour faciliter les communications réseaux

 **/

package V2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client extends ReseauxToolbox {

    private String hostname;        // Adresse du serveur
    private int port;               // Port du serveur
    private boolean connecte;       // Indicateur de connexion avec le serveur
    private DatagramSocket sock;    // Socket de communication avec le serveur de jeu
    private DatagramPacket in;      // Datagram pour la communication entrante
    private DatagramPacket out;     // Datagram pour la communication sortante
    private byte[] buffer;          // Le buffer pour stocker le contenu de la communication entrante
    private Thread lecture;         // Un thread pour lire à chaque fois que possible dans le buffer in
    private Thread sigint_catch;    // Un thread chargé d'attraper la sortie du programme client pour prevenir le serveur


    // Une fonction pour établier la connexion avec le serveur de jeu
    private void se_connecter(){

        sigint_catch = new Thread(new Runnable() {
            @Override
            public void run() {
                sendMessage(sock, in.getSocketAddress(), END_OF_COMMUNICATION);
                System.out.println("Le serveur a terminé la connexion. Fin du programme client.");
                stopSocket(sock);
            }
        });


        Runtime.getRuntime().addShutdownHook(sigint_catch);

        try {
            sock = new DatagramSocket();
            buffer = new byte[1024];
            in = new DatagramPacket(buffer, buffer.length);
            out = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(hostname), port);

            sock.send(out);     // initialisation de lda connexion
            connecte = true;
            jouer();
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

        System.out.print(message);

       if(SAL && !EOC){
            System.out.print("Entrez une lettre : ");
            Scanner sc = new Scanner(System.in);
            char c = sc.next().charAt(0);
            sendMessage(sock, in.getSocketAddress(), c+"");
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
                    while(connecte) {
                        sock.receive(in);
                        receive_message(new String(in.getData(), 0, in.getLength()));
                    }
                }catch (Exception e){ e.printStackTrace(); }
            }
        });

        lecture.start();
    }


    // Fonction de fermeture de la socket du client et d'arrêt du programme
    // Imposée par la ReseauxToolbox
    @Override
    void stopSocket(DatagramSocket soc) {
        connecte = false;
        if( !soc.equals(sock)) return;
        try{
            sock.close();
        }catch (Exception e){e.printStackTrace();}
        Runtime.getRuntime().removeShutdownHook(sigint_catch);
        exit(0);
    }

    // Constructeur par défaut
    Client(){
        this.hostname = "localhost";
        this.port = 5500;
    }


    // Constructeur avec parametres
    Client(String add, int port){
        this.hostname = add;
        this.port = port;
    }


    public static void main(String[] args)
    {
        Client c = new Client();
        c.se_connecter();
    }

}

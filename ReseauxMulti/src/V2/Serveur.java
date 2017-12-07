/**
    Nom du binôme : LOIGNON Lucas et FAUCONNIER Axel

    Description de la classe : Serveur de jeu

    Fonctionnalités :
        -> Attend la connexion d'un client
            -> Lance un thread qui gère une partie avec ledit client

    Dependances : ReseauxToolbox : une classe abstraite qui fournit des méthodes pour faciliter les communications réseaux

 **/

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Serveur extends ReseauxToolbox {

    private static String SERVEUR_START_TEXT                    = "[Serveur] Demarrage réussi.";
    private static final String SERVEUR_STOP_TEXT               = "[Serveur] Arrêt réussi. ";
    private static final String SERVEUR_WAITINGCLIENT_TEXT      = "[Serveur] En attente d'une connexion..";
    private static final String SERVEUR_ACCEPTCLIENT_TEXT       = "------\n[Serveur] Connexion entrante acceptée.\n------";
    private static final String SERVEUR_REJECTCLIENT_TEXT       = "------\n[Serveur] Connexion entrante rejetée.\n------";

    private static int serveur_mode;
    private static final int SERVEUR_MODE_ON        = 1;
    private static final int SERVEUR_MODE_OFF       = 2;
    private static final int SERVEUR_MODE_PAUSE     = 3;

    private static ServerSocket ss;                         // La socket d'écoute du serveur
    private static int port;                                // Le port d'écoute du serveur ; par défaut sur DEFAULT_PORT = 5500

    private static Thread ecouteConnexions;                 // Le thread principal pour l'écoute des connexions
    private static int nbConnexions;                        // Un simple compteur pour enregistrer le nombre de clients traités


    private Serveur() {
        this.port = 5500;
    }

    private Serveur(int port){
        this.port = port;
    }


    // Une fonction pour démarrer le serveur, la socket d'écoute et la fonction en charge de traiter les connexions entrantes
    private void demarrer(){
        try {
            ss = new ServerSocket(port);
            serveur_mode = SERVEUR_MODE_ON;
        }
        catch (UnknownHostException e){ e.printStackTrace(); }
        catch (IOException e){ e.printStackTrace(); }

        System.out.println(SERVEUR_START_TEXT);
        ecouterConnexions();
    }

    // Une fonction pour mettre en pause le serveur
    private void pause(){
        ecouteConnexions.interrupt();
        serveur_mode = SERVEUR_MODE_PAUSE;
    }

    // Une fonction pour arrêter le serveur
    private void arreter(){
        ecouteConnexions.interrupt();
        serveur_mode = SERVEUR_MODE_OFF;
    }


    // La fonction qui démarre le thread d'écoute
    private void ecouterConnexions(){
        ecouteConnexions = new Thread(new Runnable() {
            // Le thread en charge d'ecouter la socket du serveur
            // Et de créer un thread de jeu (GameThread) pour chaque client
            @Override
            public void run() {
                while (serveur_mode == SERVEUR_MODE_ON) {
                    try {
                        System.out.println(SERVEUR_WAITINGCLIENT_TEXT);
                        Socket in = ss.accept();
                        nbConnexions++;
                        System.out.println(SERVEUR_ACCEPTCLIENT_TEXT);
                        Thread client = new Thread(new GameThread(in, nbConnexions));
                        client.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        ecouteConnexions.start();
    }


    // La fonction main qui crée une instance du serveur et le démarre
    public static void main(String[] args) {
        Serveur s = new Serveur();
        s.demarrer();
    }

}

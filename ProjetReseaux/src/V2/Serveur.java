/**
    Nom du binôme : LOIGNON Lucas et FAUCONNIER Axel

    Description de la classe : Serveur de jeu (UDP)

    Fonctionnalités :
        - Attend la connexion d'un client
        - Lance une partie du jeu du mot caché
        - Met en attente les nouvelles connexions si une partie est en cours

    Dependances : ReseauxToolbox : une classe abstraite qui fournit des méthodes pour faciliter les communications réseaux

 **/

package V2;

import java.io.IOException;
import java.net.*;

import static java.lang.Thread.sleep;

public class Serveur extends ReseauxToolbox {

    private int port;                   // Numéro de port d'écoute du serveur
    private boolean estActif;           // Indicateur d'activité du serveur
    private byte[] buffer;              // Buffer pour les communications entrantes
    private DatagramSocket ds;          // Socket du serveur
    private DatagramPacket in;          // Datagram pour la communication entrante
    private DatagramPacket out;         // Datagram pour la communication sortante
    private SocketAddress client;       // Socket pour le client en cours de partie
    private String motActif;            // Mot choisi pour la partie en cours
    private String dictPath;            // Chemin vers le fichier dictionnaire
    private boolean jeu_trouve;         // Indicateur de l'etat de la partie en cours
    private int nb_tentatives;

    // Constructeur du serveur
    // Par défaut : localhost:5500
    private Serveur(){
        try {
            this.ds = new DatagramSocket(5500);
            this.dictPath = "./Mots.txt";
        }catch(Exception e){ e.printStackTrace(); }
    }

    private Serveur(int port){
        try {
            this.port = port;
            this.ds = new DatagramSocket(this.port);
            this.dictPath = "./Mots.txt";
        }catch(Exception e){ e.printStackTrace(); }
    }


    // Fonction de lancement du serveur
    // Attend une connexion pour lancer une partie
    private void demarrer(){
        this.estActif = true;

        while(estActif){
            System.out.println("[Serveur] Demarrage du serveur..");
            System.out.println("[Serveur] En attention d'une connexion.");

            try {
                buffer = new byte[1024];
                in = new DatagramPacket(buffer, buffer.length);
                ds.receive(in);     // Bloquant jusqu'a la connexion d'un client
                client = in.getSocketAddress();
                System.out.println("[Serveur] Connexion acceptée (client id = " + client.toString() + ")");
                jouer();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    // Fonction d'arret du serveur
    private void arreter(){
        this.estActif = false;
        ds.close();
    }


    // Fonction principale pour le jeu
    private void jouer(){

        sendMessage(ds, client, "Bienvenue sur le jeu du mot caché\n\n");
        sendMessage(ds, client, "Rappel des regles : \n");
        sendMessage(ds, client, "\t* Seule une lettre est prise en compte (la première)\n");
        sendMessage(ds, client, "\t* Aucune différence entre les lettres miniscules et majuscules\n");
        sendMessage(ds, client, "\t* Une lettre invalide déjà utilisée compte pas comme une tentative\n");
        sendMessage(ds, client, "\t* Une lettre valide déjà utilisée ne compte pas comme une tentative\n\n");


        // Initialisation des variables de jeu
        motActif = pickRandomWord(dictPath);
        nb_tentatives = (motActif.length()/2)+2;
        int nb_coups = 0;
        jeu_trouve = false;
        String mot_en_cours = "";

        for(int i = 0; i < motActif.length(); i++)
            mot_en_cours += "_";

        sendMessage(ds, client, "Le mot a trouver : " + mot_en_cours + " en " + nb_tentatives + " tentatives ! Bonne chance\n\n");
        sendMessage(ds, client, SEND_A_LETTER);

        // Tant que le mot n'as pas été trouvé et qu'il reste des tentatives
        while(nb_tentatives > 0 && !jeu_trouve){
            char a = Character.toUpperCase(getLetter(ds, client));
            System.out.println("[Serveur - Jeu] Lettre proposée : " + a);
            if(motActif.indexOf(a) == -1){
                System.out.println("[Serveur - Jeu] Lettre absente du mot caché");
                nb_tentatives--;
            }
            else{
                System.out.println("[Serveur - Jeu] Lettre presente dans le mot caché");
                String motMisAJour = "";
                // On construit le nouveau mot (composé des lettres trouvées et de tirets)
                for(int i =0; i < motActif.length(); i++){
                    if(motActif.charAt(i) == a)
                        motMisAJour += a;
                    else if(mot_en_cours.charAt(i) != '_')
                        motMisAJour += mot_en_cours.charAt(i);
                    else
                        motMisAJour += '_';
                }
                mot_en_cours = motMisAJour;
            }

            nb_coups++;

            if(mot_en_cours.equals(motActif)) {
                jeu_trouve = true;
                System.out.println("END GAME");
            }
            else if(nb_tentatives > 0 && !jeu_trouve) {
                sendLetters(ds, client, mot_en_cours, motActif.indexOf(a) != -1, nb_tentatives);
            }
        }

        if(jeu_trouve)
            sendMessage(ds, client, "\n\nVous avez gagné en " + nb_coups + " coups ! Bravo\nAurevoir et merci d'avoir joué\n");
        else
            sendMessage(ds, client, "\n\nVous avez perdu ! Le mot était \""+ motActif + "\" .. Une prochaine fois peut-être !\nAurevoir et merci d'avoir joué\n");

        sendMessage(ds, client, END_OF_COMMUNICATION);

    }


    // Fonction de fermeture de la socket du client
    // Imposée par la ReseauxToolbox pour gérer les deconnexions coté client
    @Override
    void stopSocket(DatagramSocket soc) {
            this.nb_tentatives = 0;
    }

    public static void main(String[] args) {
        Serveur serveur = new Serveur();
        serveur.demarrer();

    }


}

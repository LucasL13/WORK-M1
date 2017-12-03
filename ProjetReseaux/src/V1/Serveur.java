/**
    Nom du binôme : LOIGNON Lucas et FAUCONNIER Axel

    Description de la classe : Serveur de jeu.

    Fonctionnalités :
        - Attend la connexion d'un client
        - Lance une partie du jeu du mot caché
        - Met en attente les nouvelles connexions si une partie est en cours

    Dependances : ReseauxToolbox : une classe abstraite qui fournit des méthodes pour faciliter les communications réseaux

 **/

package V1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur extends ReseauxToolbox {

    private boolean estActif;           // Indicateur d'activité du serveur
    private ServerSocket ss;            // Socket du serveur
    private Socket client;              // Socket pour le client en cours de partie
    private String motActif;            // Mot choisi pour la partie en cours
    private String dictPath;            // Chemin vers le fichier dictionnaire
    private boolean jeu_trouve;         // Indicateur de l'etat de la partie en cours

    // Constructeur du serveur
    // Par défaut : localhost:5500
    private Serveur(){
        try {
            this.ss = new ServerSocket(5500);
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

            try{
                client = ss.accept();
                System.out.println("[Serveur] Connexion acceptée (client id = " + client.getInetAddress()+")");
                jouer();
                client.close();
            }catch (Exception e){ e.printStackTrace(); }

        }

    }


    // Fonction d'arret du serveur
    private void arreter(){
        this.estActif = false;
    }


    // Fonction principale pour le jeu
    private void jouer(){

        sendMessage(client, "Bienvenue sur le jeu du mot caché\n\n");
        sendMessage(client, "Rappel des regles : \n");
        sendMessage(client, "\t* Seule une lettre est prise en compte (la première)\n");
        sendMessage(client, "\t* Aucune différence entre les lettres miniscules et majuscules\n");
        sendMessage(client, "\t* Une lettre invalide déjà utilisée compte pas comme une tentative\n");
        sendMessage(client, "\t* Une lettre valide déjà utilisée ne compte pas comme une tentative\n\n");

        // Initialisation des variables de jeu
        motActif = pickRandomWord(dictPath);
            int nb_tentatives = (motActif.length()/2)+2;
        int nb_coups = 0;
        jeu_trouve = false;
        String mot_en_cours = "";

        for(int i = 0; i < motActif.length(); i++)
            mot_en_cours += "_";

        sendMessage(client, "Le mot a trouver : " + mot_en_cours + " en " + nb_tentatives + " tentatives ! Bonne chance\n\n");
        sendMessage(client, SEND_A_LETTER);

        // Tant que le mot n'as pas été trouvé et qu'il reste des tentatives
        while(nb_tentatives > 0 && !jeu_trouve){
            char a = Character.toUpperCase(getLetter(client));
            System.out.println("[Serveur - Jeu] Lettre proposée : " + a);
            if(motActif.indexOf(a) == -1){
                System.out.println("[Serveur - Jeu] Lettre absente du le mot caché");
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
            else if(nb_tentatives > 0 || !jeu_trouve) {
                sendLetters(client, mot_en_cours, motActif.indexOf(a) != -1, nb_tentatives);
            }
        }

        if(jeu_trouve)
            sendMessage(client, "\n\nVous avez gagné en " + nb_coups + " coups ! Bravo\nAurevoir et merci d'avoir joué\n");
        else
            sendMessage(client, "\n\nVous avez perdu ! Le mot était \""+ motActif + "\" .. Une prochaine fois peut-être !\nAurevoir et merci d'avoir joué");

        sendMessage(client, END_OF_COMMUNICATION);

        try {
            client.close();
        } catch (IOException e) {e.printStackTrace();}
    }


    // Fonction de fermeture de la socket du client
    // Imposée par la ReseauxToolbox pour gérer les deconnexions coté client
    @Override
    void stopSocket(Socket soc) {
        if (!soc.equals(client)) return;
        try{
            client.close();
        }catch (Exception e){ e.printStackTrace(); }
    }

    public static void main(String[] args) {
        Serveur serveur = new Serveur();
        serveur.demarrer();
    }


}

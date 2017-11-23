package TCPv1;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

import static java.lang.Thread.sleep;

/**
 * Created by work on 23/11/17.
 */
public class Serveur extends ReseauxToolbox{

    private boolean estActif;
    private ServerSocket ss;
    private Socket client;              // Socket pour le client en cours de partie
    private String motActif;            // Mot choisi pour la partie en cours
    private String dictPath;            // Chemin vers le fichier dictionnaire

    private boolean jeu_trouve;

    private Serveur(){
        try {
            this.ss = new ServerSocket(5500);
            this.dictPath = "./Mots.txt";
        }catch(Exception e){ e.printStackTrace(); }
    }

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

    private void arreter(){
        this.estActif = false;
    }

    // Fonction principale pour le jeu
    private void jouer(){

        motActif = pickRandomWord(dictPath);

        sendMessage(client, "Bienvenue sur le jeu du mot caché\n");

        int nb_tentatives = (motActif.length()/2)+1;
        int nb_coups = 0;
        jeu_trouve = false;
        String mot_en_cours = "";

        for(int i = 0; i < motActif.length(); i++)
            mot_en_cours += "_";

        sendMessage(client, "Le mot a trouver : " + mot_en_cours + " en " + nb_tentatives + " tentatives ! Bonne chance\n\n");
        sendMessage(client, "Entrez une lettre : ");

        while(nb_tentatives > 0 && !jeu_trouve){
            char a = getLetter(client);
            System.out.println("[Serveur - Jeu] Lettre proposée : " + a);
            if(motActif.indexOf(a) == -1){
                System.out.println("[Serveur - Jeu] Lettre absente du le mot caché");
                nb_tentatives--;
            }
            else{
                System.out.println("[Serveur - Jeu] Lettre presente dans le mot caché");
                String motMisAJour = "";
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

            if(nb_tentatives > 0)
                sendLetters(client, mot_en_cours, motActif.indexOf(a) != -1, nb_tentatives);

            if(mot_en_cours.equals(motActif))
                jeu_trouve = true;
        }

        if(jeu_trouve)
            sendMessage(client, "\n\nVous avez gagné en " + nb_coups + " coups ! Bravo\nAurevoir et merci d'avoir joué\n");
        else
            sendMessage(client, "\n\nVous avez perdu ! Le mot était \""+ motActif + "\" .. Une prochaine fois peut-être !\nAurevoir et merci d'avoir joué\n");

        try {
            client.close();
        } catch (IOException e) {e.printStackTrace();}
    }


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

package TCPv1;

import java.net.Socket;
import java.net.ServerSocket;

import static java.lang.Thread.sleep;

/**
 * Created by work on 23/11/17.
 */
public class Serveur {

    private boolean estActif;
    private ServerSocket ss;
    private Socket client;              // Socket pour le client en cours de partie
    private String motActif;            // Mot choisi pour la partie en cours
    private String dictPath;            // Chemin vers le fichier dictionnaire

    private Serveur(){
        try {
            this.ss = new ServerSocket(5500);
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
                sleep(10000);
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

        int nb_tentatives = (motActif.length()/2);
        boolean trouvé = false;
        String mot_en_cours = "";

        for(int i = 0; i < motActif.length(); i++)
            mot_en_cours += "_";

        while(nb_tentatives > 0 || !trouvé){
            char a = getLetter();
            if(mot_en_cours.indexOf(a) == -1){
                nb_tentatives--;
            }
            else{
                for(int i =0; i < motActif.length(); i++)
                    if(motActif.charAt(i) == a)
                        mot_en_cours += a;
                    else
                        mot_en_cours += '_';
            }

            sendLetters(mot_en_cours, false, nb_tentatives);

            if(mot_en_cours.equals(motActif))
                trouvé = true;
        }

        if(trouvé)
            sendMessage("Vous avez gagné en " + nb_tentatives + " tentatives ! Bravo");
        else
            sendMessage("Vous avez perdu ! Une prochaine fois peut-être !");
    }


    private char getLetter(){ return 'a';}
    private void sendLetters(String letters, boolean present, int nb_tentatives_restantes){}
    private void sendMessage(String message){}

    // Fonction chargée de choisir le mot dans le dictionnaire
    // Et le charge dans l'attribut "motActif"
    private void pickWord(){

    }


    public static void main(String[] args) {

        Serveur serveur = new Serveur();

        serveur.demarrer();

    }


}

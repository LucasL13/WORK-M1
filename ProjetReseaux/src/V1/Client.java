/**
    Nom du binôme : LOIGNON Lucas et FAUCONNIER Axel

    Description de la classe : Client de jeu

    Fonctionnalités :
        - Se connecte au serveur de jeu via les paramètres par défaut (localhost:5500)
        - Reçoit les informations du serveur pour le jeu et les affiche
        - Envoi les informations entrées par le joueur au serveur de jeu

    Dependances : ReseauxToolbox : une classe abstraite qui fournit des méthodes pour faciliter les communications réseaux

 **/

package V1;

import java.net.Socket;

public class Client extends ReseauxToolbox {

    private boolean connecte;
    private Socket sock;

    private void se_connecter(){
        try {
            sock = new Socket("localhost", 5500);
            connecte = true;
        }catch(Exception e){e.printStackTrace();}
    }

    private void jouer(){
        while(connecte){
            //System.out.println("Je vais jouer");
            String in, out;
            System.out.println(getMessage(sock));
            connecte = false;
        }
    }
    @Override
    void stopSocket(Socket soc) {
        connecte = false;
        if( !soc.equals(sock)) return;
        try{
            sock.close();
        }catch (Exception e){e.printStackTrace();}
    }

    Client(){
        se_connecter();
        jouer();
    }

    public static void main(String[] args) {
        Client c = new Client();
    }

}

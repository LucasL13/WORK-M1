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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.FileSystem;
import java.rmi.server.ExportException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client extends ReseauxToolbox {

    private boolean connecte;
    private Socket sock;
    private Thread lecture;

    private void se_connecter(){
        try {
            sock = new Socket("localhost", 5500);
            connecte = true;
        }catch(Exception e){e.printStackTrace();}
    }

    private void receive_message(String message){

        boolean EOC = (message.indexOf(END_OF_COMMUNICATION) != -1);
        boolean SAL = (message.indexOf(SEND_A_LETTER) != -1);

        if(EOC)
            message = message.replace(END_OF_COMMUNICATION, "");
//        if(SAL)
//            message = message.replace(SEND_A_LETTER, "");

        System.out.println(message);

       if(SAL){
            System.out.print("Entrez une lettre : ");
            Scanner sc = new Scanner(System.in);
            char c = sc.next().charAt(0);
            sendMessage(sock, c+"");
        }

        if(EOC)
            stopSocket(sock);
    }

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

    @Override
    void stopSocket(Socket soc) {
        System.out.println("\nLe serveur a terminé la connexion. Fin du programme client.");
        connecte = false;
        if( !soc.equals(sock)) return;
        try{
            sock.close();
        }catch (Exception e){e.printStackTrace();}
        exit(0);
    }

    Client(){
        se_connecter();
        jouer();
    }

    public static void main(String[] args) {
        Client c = new Client();
    }

}

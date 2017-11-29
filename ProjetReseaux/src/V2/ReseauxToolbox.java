/**
    Nom du binôme : LOIGNON Lucas et FAUCONNIER Axel

    Description de la classe : Boîte a outils pour les communications réseaux

    Fonctionnalités :
        - Fournit des méthodes pour envoyer et recevoir des messages via une socket
        - Fournit une méthode pour lire un dictionnaire et choisir un mot aléatoirement

    Dependances :

 **/

package V2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

public abstract class ReseauxToolbox {

    protected final String END_OF_COMMUNICATION = "[PROTOCOL_ENDCOM]";
    protected final String SEND_A_LETTER        = "[PROTOCOL_SENDLET]";

    private BufferedWriter bw;          // Pour écrire dans une socket
    private BufferedReader br;          // Pour lire dans une socket
    private ArrayList<String> mots;     // Contient l'ensemble de mots chargés depuis le dictionnaire

    // Une méthode pour faciliter la reception d'une tentative du client
    // S'assure que le caractere envoyé est une lettre autorisée ([a-zA-Z])
    // Ne renvoie que la premiere lettre si la taille de l'entrée > 1
    public char getLetter(DatagramSocket ds, SocketAddress sa){
        char letter = '\0';
        boolean valide = false;
        try {
            while (!valide) {
                byte[] buffer = new byte[1024];
                DatagramPacket in = new DatagramPacket(buffer, buffer.length);
                ds.receive(in);

                if( new String(in.getData()).indexOf(END_OF_COMMUNICATION) != -1) {
                    stopSocket(ds);
                    valide = true;
                }
                letter = new String(in.getData()).charAt(0);
                if (!(letter >= 'A' && letter <= 'Z' || letter >= 'a' && letter <= 'z')) {
                    sendMessage(ds, sa, "\nErreur entrée : votre caractere doit être une lettre de l'alphabet\n");
			        sendMessage(ds, sa, SEND_A_LETTER);
                }
                else
                    valide = true;
            }
        }catch(Exception e){
            try{
                br.close();
                //stopSocket(soc);
            }catch (Exception z){z.printStackTrace();}
        }

        return letter;
    }


    // Une méthode pour faciliter l'envoi formaté d'une réponse du serveur
    // De la forme : "Votre précédente tentative à réussie. Tentatives restantes : 4
    //                          Le mot caché => OBE__
    //                Entrez votre lettre : r"
    public void sendLetters(DatagramSocket ds, SocketAddress sa, String letters, boolean present, int nb_tentatives_restantes){

        String precedentEssai = "échoué";
        if(present)
            precedentEssai = "réussie";

        sendMessage(ds, sa, "\nVotre précédente tentative à " + precedentEssai + ". Tentatives restantes : " + nb_tentatives_restantes + "\n");
        sendMessage(ds, sa, "\t\tLe mot caché => " + letters + "\n");
        sendMessage(ds, sa, SEND_A_LETTER);
    }


    // Une méthode pour faciliter l'envoi d'un message par les classes héritées
    public void sendMessage(DatagramSocket soc, SocketAddress client, String message){
        try{
            DatagramPacket mess = new DatagramPacket(message.getBytes(), message.getBytes().length, client);
            soc.send(mess);
        }
        catch (IOException e){ e.printStackTrace(); }
    }

    // Charge le dictionnaire en mémoire à partir du fichier Mots.txt
    private void readMots(String path){
        mots = new ArrayList<>();
        try{
            br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null ){
                //System.out.println("Line = " + line);
                mots.add(line);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    // Choisis un mot aléatoirement parmis les mots chargés en mémoire
    public String pickRandomWord(String path){
        if(mots == null)
            readMots(path);
        if(mots.isEmpty())
            readMots(path);

            int rand = (int) Math.floor(Math.random() * (mots.size() +1));
        System.out.println("Mot choisi aléatoirement à partir du dictionnaire : "  + mots.get(rand));
        return mots.get(rand);
    }


    // Une méthode qui ferme le socket du client deconnecté
    // A implémenter par les classes héritées
    abstract void stopSocket(DatagramSocket soc);

}

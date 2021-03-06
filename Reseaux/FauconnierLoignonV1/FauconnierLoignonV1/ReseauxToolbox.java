/**
    Nom du binôme : LOIGNON Lucas et FAUCONNIER Axel

    Description de la classe : Boîte a outils pour les communications réseaux

    Fonctionnalités :
        - Fournit des méthodes pour envoyer et recevoir des messages via une socket
        - Fournit une méthode pour lire un dictionnaire et choisir un mot aléatoirement

    Dependances :

 **/

import java.io.*;
import java.util.Vector;
import java.util.Random;
import java.util.Scanner;
import java.net.Socket;
import java.util.ArrayList;

public abstract class ReseauxToolbox {

	// On utilise des "champs" de protocole pour informer le serveur et/ou le client des cas spécifiques
    protected final String END_OF_COMMUNICATION = "[PROTOCOL_ENDCOM]"; // L'envoyeur annonce la fin de la communication
    protected final String SEND_A_LETTER        = "[PROTOCOL_SENDLET]"; // L'envoyeur indique qu'il attends une lettre pour le mot caché

    private BufferedWriter bw;          // Pour écrire dans une socket
    private BufferedReader br;          // Pour lire dans une socket
	private Vector<String> mots;		// Contient l'ensemble de mots chargés depuis le dictionnaire

    // Une méthode pour faciliter la reception d'une tentative du client
    // S'assure que le caractere envoyé est une lettre autorisée ([a-zA-Z])
    // Ne renvoie que la premiere lettre si la taille de l'entrée > 1
    public char getLetter(Socket soc){
        char letter = '\0';
        boolean valide = false;
        try {
            while (!valide) {
                br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                letter = (char) br.read();
                if (!(letter >= 'A' && letter <= 'Z' || letter >= 'a' && letter <= 'z')) {
                    sendMessage(soc, "\nErreur entrée : votre caractere doit être une lettre de l'alphabet\n");
			sendMessage(soc, SEND_A_LETTER);
                }
                else
                    valide = true;
            }
        }catch(Exception e){
            try{
                br.close();
                stopSocket(soc);
            }catch (Exception z){z.printStackTrace();}
        }

        return letter;
    }


    // Une méthode pour faciliter l'envoi formaté d'une réponse du serveur
    // De la forme : "Votre précédente tentative à réussie. Tentatives restantes : 4
    //                          Le mot caché => OBE__
    //                Entrez votre lettre : r"
    public void sendLetters(Socket soc, String letters, boolean present, int nb_tentatives_restantes){

        String precedentEssai = "échoué";
        if(present)
            precedentEssai = "réussie";

        sendMessage(soc, "\nVotre précédente tentative à " + precedentEssai + ". Tentatives restantes : " + nb_tentatives_restantes + "\n");
        sendMessage(soc, "\t\tLe mot caché => " + letters + "\n");
        sendMessage(soc, SEND_A_LETTER);
    }


    // Une méthode pour faciliter l'envoi d'un message par les classes héritées
    public void sendMessage(Socket soc, String message){
        try{
            bw = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
            bw.write(message);
            bw.flush();
        }catch(Exception e){
            System.out.println("\n[Serveur - Erreur] Client déconnecté -> fermeture de la connexion\n");
            try{
                bw.close();
                stopSocket(soc);
            }catch (Exception z){z.printStackTrace();}
        }

    }

    // Charge le dictionnaire en mémoire à partir du fichier Mots.txt
    private void readMots(String path){
        mots = new Vector<String>();
        try{
            Scanner sc = new Scanner(new File(path));
            String line;
            while(sc.hasNextLine())
            {
				line = sc.nextLine();
				mots.add(line);
			}
			sc.close();
			
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


    // Choisis un mot aléatoirement parmis les mots chargés en mémoire
    public String pickRandomWord(String path){
        if(mots == null)
            readMots(path);
        if(mots.isEmpty())
            readMots(path);

        Random random = new Random();
        int rand = random.nextInt(mots.size());	//Génère un entier entre 0 et mots.size() - 1 inclus
        System.out.println("Mot choisi aléatoirement à partir du dictionnaire : "  + mots.get(rand));
        return mots.get(rand);
    }


    // Une méthode qui ferme le socket du client deconnecté
    // A implémenter par les classes héritées
    abstract void stopSocket(Socket soc);

}

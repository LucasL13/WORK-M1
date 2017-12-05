import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;


public abstract class ReseauxToolbox {


    protected final String DEFAULT_ADDR = "localhost";      // L'adresse par défaut
    protected final int DEFAULT_PORT = 5500;                // Le port par défaut


    // On utilise des "champs" de protocole pour informer le serveur et/ou le client de cas spécifiques
    // Voir le rapport/documentation pour la descripton complète des champs

    protected final String END_OF_COMMUNICATION = "[PROTOCOL_ENDCOM]";
    protected final String SEND_A_LETTER        = "[PROTOCOL_SENDLET]";
    protected final String ENDGAME_WIN          = "[PROTOCOL_ENDWIN]";
    protected final String ENDGAME_LOSE         = "[PROTOCOL_ENDLOSE]";
    protected final String ERROR_WRONGCHAR      = "[PROTOCOL_WRONGCHAR]";
    protected final String GET_WORD             = "[PROTOCOL_GETWORD]";
    protected final String LASTWORD_FAILED      = "[PROTOCOL_LASTWFAILED]";
    protected final String LASTWORD_SUCCESS     = "[PROTOCOL_LASTWSUCCESS]";
    protected final String CLIENT_GETWORD       = "&";


    private BufferedWriter bw;          // Pour écrire dans une socket
    private BufferedReader br;          // Pour lire dans une socket
    private Vector<String> mots;		// Contient l'ensemble de mots chargés depuis le dictionnaire

    // Une fonction qui permet de récuperer une lettre parmis l'entrée envoyée par le client
    protected char getLetter(Socket cible){
        char letter = '\0';
        boolean valide = false;
        try {
            while (!valide) {
                br = new BufferedReader(new InputStreamReader(cible.getInputStream()));
                letter = (char) br.read();
                if(letter == '&')
                    valide = true;
                else if (!(letter >= 'A' && letter <= 'Z' || letter >= 'a' && letter <= 'z')) {
                    sendMessage(cible, ERROR_WRONGCHAR);
                }
                else
                    valide = true;
            }
        }catch(Exception e){
            try{
                br.close();
            }catch (Exception z){z.printStackTrace();}
        }

        return letter;
    }


    // Une fonction pour envoyer au client les lettres du mot caché en cours
    // Indique également la réussite ou l'echec de la précédente tentative et le nombre de tentatives restantes
    protected void sendLetters(Socket cible, String mot, boolean present, int nbTentatives) {

        if (present)
            sendMessage(cible, LASTWORD_SUCCESS + nbTentatives + GET_WORD + mot);
        else
            sendMessage(cible, LASTWORD_FAILED + nbTentatives + GET_WORD + mot);

        sendMessage(cible, SEND_A_LETTER);
    }


    // Une fonction utilitaire pour faciliter l'envoi d'un message sur le réseau (via une Socket)
    protected void sendMessage(Socket cible, String message){
        try{
            bw = new BufferedWriter(new OutputStreamWriter(cible.getOutputStream()));
            bw.write(message);
            bw.flush();
        }catch(Exception e){
            System.out.println("\n[Serveur - Erreur] Client déconnecté -> fermeture de la connexion\n");
            try{
                bw.close();
                //stopSocket(soc);
            }catch (Exception z){z.printStackTrace();}
        }
    }


    // Charge le dictionnaire en mémoire à partir du fichier Mots.txt
    protected void readMots(String path){
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
    protected String pickRandomWord(String path){

        readMots(path);

        Random random = new Random();
        int rand = random.nextInt(mots.size());	//Génère un entier entre 0 et mots.size() - 1 inclus
        System.out.println("Mot choisi aléatoirement à partir du dictionnaire : "  + mots.get(rand));
        return mots.get(rand);
    }

}

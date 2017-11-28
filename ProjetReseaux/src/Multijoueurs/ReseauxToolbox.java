package Multijoueurs;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by work on 23/11/17.
 */
public abstract class ReseauxToolbox {


    public final String ERROR_NOTALETTER    = "Serv_NAL";
    public final String ENDOFGAME_SUCCESS   = "Serv_EOGS";
    public final String ENDOFGAME_FAILURE   = "Serv_EOGF";
    public final String LETTER_SUCCESS      = "Serv_LS";
    public final String LETTER_FAILURE      = "Serv_LF";


    private BufferedWriter bw;
    private BufferedReader br;
    private ArrayList<String> mots;


    public char getLetter(Socket soc){
        char letter = '\0';
        boolean valide = false;
        try {
            while (!valide) {
                br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                letter = (char) br.read();
                if (!(letter >= 'A' && letter <= 'Z' || letter >= 'a' && letter <= 'z')) {
                    sendMessage(soc, ERROR_NOTALETTER);
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

    public void sendLetters(Socket soc, String letters, boolean present, int nb_tentatives_restantes){
        if(present)
            sendMessage(soc, LETTER_SUCCESS);
        else
            sendMessage(soc, LETTER_FAILURE);

        sendMessage(soc, "Tentatives restantes : " + nb_tentatives_restantes + "\n");
        sendMessage(soc, "\t\tLe mot caché => " + letters + "\n");
    }

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

    public String pickRandomWord(String path){
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

        int rand = (int) Math.floor(Math.random() * (mots.size() +1));
        System.out.println("Mot choisi aléatoirement à partir du dictionnaire : "  + mots.get(rand));
        return mots.get(rand);
    }

     abstract void stopSocket(Socket soc);

}

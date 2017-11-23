package TCPv1;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by work on 23/11/17.
 */
public abstract class ReseauxToolbox {

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
                    sendMessage(soc, "\nErreur entrée : votre caractere doit être une lettre de l'alphabet\n");
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

        String precedentEssai = "échoué";
        if(present)
            precedentEssai = "réussie";

        sendMessage(soc, "\nVotre précédente tentative à " + precedentEssai + ". Tentatives restantes : " + nb_tentatives_restantes + "\n");
        sendMessage(soc, "\t\tLe mot caché => " + letters + "\n");
        sendMessage(soc, "Entrez votre lettre : ");

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

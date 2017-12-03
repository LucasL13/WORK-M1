import java.io.IOException;
import java.net.Socket;

import static java.lang.System.exit;

/**
 * Created by work on 02/12/17.
 */
public class GameThread extends ReseauxToolbox implements Runnable {

    private final String THREAD_CLIENT       = "------\n[Serveur -> Thread] Début d'une partie avec le nouveau client ";

    private final String THREAD_GAMERULES    = "Bienvenue sur le jeu du mot caché\n\n"
            + "Rappel des regles : \n"
            + "\t* Seule une lettre est prise en compte (la première)\n"
            + "\t* Aucune différence entre les lettres miniscules et majuscules\n"
            + "\t* Une lettre invalide déjà utilisée compte pas comme une tentative\n"
            + "\t* Une lettre valide déjà utilisée ne compte pas comme une tentative\n\n";



    private String motCache;
    private String motCacheTemp;
    private int nbTentatives;
    private int coupJoues;
    private boolean motTrouve;


    Socket client;
    int threadNumber;

    public GameThread(Socket s, int threadNumber){
        client = s;
        this.threadNumber = threadNumber;
    }

    private void init_game(){
        motCache = pickRandomWord("Mots.txt");
        nbTentatives = (motCache.length()/2)+2;
        coupJoues = 0;
        motTrouve = false;
        motCacheTemp = "";

        for(int i = 0; i < motCache.length(); i++)
            motCacheTemp += "_";
    }

    @Override
    public void run() {

        System.out.println(THREAD_CLIENT + "(" + client.getInetAddress() + ")");

        init_game();
        sendMessage(client, THREAD_GAMERULES);
        System.out.println("Mot choisi : " + motCache);
        sendMessage(client, "Le mot a trouver : " + motCacheTemp + " en " + nbTentatives + " tentatives ! Bonne chance\n\n");
        sendMessage(client, SEND_A_LETTER);

        // Tant que le mot n'as pas été trouvé et qu'il reste des tentatives
        while(nbTentatives > 0 && !motTrouve){
            char a = Character.toUpperCase(getLetter(client));
            if(a == '&')
                System.out.println("[Serveur -> Client"+threadNumber+"] Le client a demandé a recevoir le mot caché");
            else {
                System.out.println("[Serveur -> Client" + threadNumber + "] Lettre proposée : " + a);
                if(!motCache.contains(a+"")){
                    System.out.println("[Serveur -> Client"+threadNumber+"] Lettre absente du mot caché");
                    nbTentatives--;
                }
                else{
                    System.out.println("[Serveur -> Client"+threadNumber+"] Lettre presente dans le mot caché");
                    String motMisAJour = "";
                    // On construit le nouveau mot (composé des lettres trouvées et de tirets)
                    for(int i =0; i < motCache.length(); i++){
                        if(motCache.charAt(i) == a)
                            motMisAJour += a;
                        else if(motCacheTemp.charAt(i) != '_')
                            motMisAJour += motCacheTemp.charAt(i);
                        else
                            motMisAJour += '_';
                    }
                    motCacheTemp = motMisAJour;
                }
            }


            coupJoues++;

            if(motCacheTemp.equals(motCache)) {
                motTrouve = true;
                System.out.println("END GAME");
            }
            else if(nbTentatives > 0 && !motTrouve) {
                sendLetters(client, motCacheTemp, motCache.contains(a+""), nbTentatives);
            }
        }


        if(motTrouve)
            sendMessage(client, ENDGAME_WIN + coupJoues);
        else
            sendMessage(client, ENDGAME_LOSE );

        sendMessage(client, END_OF_COMMUNICATION+motCache);

        try {
            client.close();
        } catch (IOException e) {e.printStackTrace();}
    }

}

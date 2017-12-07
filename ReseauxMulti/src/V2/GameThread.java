/**
    Nom du binôme : LOIGNON Lucas et FAUCONNIER Axel

    Description de la classe : Thread de gestion d'une partie avec un client unique

    Fonctionnalités :
        -> Est crée par le serveur lorsqu'un nouveau client se connecte
        -> S'occupe de gérer une partie avec ledit client

    Dependances :   ReseauxToolbox : une classe abstraite qui fournit des méthodes pour faciliter les communications réseaux
                    Runnable       : une interface qui permet de rendre la classe "Threadable"

 **/

import java.io.IOException;
import java.net.Socket;


public class GameThread extends ReseauxToolbox implements Runnable {

    private final String THREAD_CLIENT       = "------\n[Serveur -> Thread] Début d'une partie avec le nouveau client ";

    private final String THREAD_GAMERULES    = "Bienvenue sur le jeu du mot caché\n\n"
            + "Rappel des regles : \n"
            + "\t* Seule une lettre est prise en compte (la première)\n"
            + "\t* Aucune différence entre les lettres miniscules et majuscules\n"
            + "\t* Une lettre invalide déjà utilisée compte pas comme une tentative\n"
            + "\t* Une lettre valide déjà utilisée ne compte pas comme une tentative\n\n";



    private String motCache;        // Le mot caché de la partie en cours
    private String motCacheTemp;    // Le mot en cours qui contient les lettres trouvées et les lettres cachées sous la forme d'un tiret "_"
    private int nbTentatives;       // Le nombre de tentatives restantes pour la partie en cours
    private int coupJoues;          // Le nombre de coups joués
    private boolean motTrouve;      // Un indicateur pour savoir si le mot caché a été trouvé


    Socket client;                  // La socket pour communiquer avec le client lié a l'instance du thread
    int threadNumber;               // Le numéro du thread (pour de futures statistiques)


    public GameThread(Socket s, int threadNumber){
        client = s;
        this.threadNumber = threadNumber;
    }


    // Initialisation d'une partie : Choix du mot caché, calcul du nombre de tentatives, création du mot en cours (initialement composé par des '_'), etc..
    private void init_game(){
        motCache = pickRandomWord("Mots.txt");
        nbTentatives = motCache.length();
        coupJoues = 0;
        motTrouve = false;
        motCacheTemp = "";

        for(int i = 0; i < motCache.length(); i++)
            motCacheTemp += "_";
    }


    // Methode imposée par l'interface Runnable (sera lancée lorsque le thread sera démarré)
    // Gère une partie avec le client
    @Override
    public void run() {

        System.out.println(THREAD_CLIENT + "(" + client.getInetAddress() + ")");


        // Phase d'initialisation du jeu, on informe le client des règles, du mot caché a trouver et du nombre de tentatives
        // On signale au client que le serveur attend une lettre
        init_game();
        sendMessage(client, THREAD_GAMERULES);
        System.out.println("Mot choisi : " + motCache);
        sendMessage(client, "Le mot a trouver : " + motCacheTemp + " en " + nbTentatives + " tentatives ! Bonne chance\n\n");
        sendMessage(client, SEND_A_LETTER);

        // Boucle de jeu principale :
        // Tant que le mot n'as pas été trouvé et qu'il reste des tentatives :
        //         On recupere la lettre proposée par le client
        //         On regarde si elle est présente dans le mot
        //         On met a jour le mot caché en cours et on informe le client de la réussite ou non de sa tentative
        //         On lui envoi le mot en cours et le nombre de tentatives restantes mis à jour
        //         On lui demande une nouvelle lettre si le mot n'as pas été trouvé et qu'il reste des tentatives
        while(nbTentatives > 0 && !motTrouve){
            char a = Character.toUpperCase(getLetter(client));
            if(a == '&')
                System.out.println("[Serveur -> Client" + threadNumber + "] Le client a demandé a recevoir le mot caché");
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

        // Si le mot a été trouvé on informe le client en lui précisant le nombre de coups joués
        // Sinon on informe le client de sa défaite
        if(motTrouve)
            sendMessage(client, ENDGAME_WIN + coupJoues);
        else
            sendMessage(client, ENDGAME_LOSE);

        // On annonce la fin de la communication en indiquant quel était le mot caché de la partie
        sendMessage(client, END_OF_COMMUNICATION+motCache);

        try {
            client.close();
        } catch (IOException e) {e.printStackTrace();}
    }

}

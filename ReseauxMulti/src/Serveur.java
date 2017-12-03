import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by work on 02/12/17.
 */
public class Serveur extends ReseauxToolbox {

    private static String SERVEUR_START_TEXT                    = "[Serveur] Demarrage réussi.";
    private static final String SERVEUR_STOP_TEXT               = "[Serveur] Arrêt réussi. ";
    private static final String SERVEUR_WAITINGCLIENT_TEXT      = "[Serveur] En attente d'une connexion..";
    private static final String SERVEUR_ACCEPTCLIENT_TEXT       = "------\n[Serveur] Connexion entrante acceptée.\n------";
    private static final String SERVEUR_REJECTCLIENT_TEXT       = "------\n[Serveur] Connexion entrante rejetée.\n------";

    private static int serveur_mode;
    private static final int SERVEUR_MODE_ON        = 1;
    private static final int SERVEUR_MODE_OFF       = 2;
    private static final int SERVEUR_MODE_PAUSE     = 3;

    private static ServerSocket ss;
    private static int port;

    private static Thread ecouteConnexions;
    private static int nbConnexions;


    private Serveur() {
        this.port = 5500;
    }

    private Serveur(int port){
            this.port = port;
    }

    private void demarrer(){
        try {
            ss = new ServerSocket(port);
            serveur_mode = SERVEUR_MODE_ON;
        }
        catch (UnknownHostException e){ e.printStackTrace(); }
        catch (IOException e){ e.printStackTrace(); }

        System.out.println(SERVEUR_START_TEXT);
        ecouterConnexions();
    }

    private void pause(){
        ecouteConnexions.interrupt();
        serveur_mode = SERVEUR_MODE_PAUSE;
    }

    private void arreter(){
        ecouteConnexions.interrupt();
        serveur_mode = SERVEUR_MODE_OFF;
    }

    private void ecouterConnexions(){

        ecouteConnexions = new Thread(new Runnable() {
            @Override
            public void run() {
                while (serveur_mode == SERVEUR_MODE_ON) {
                    try {
                        System.out.println(SERVEUR_WAITINGCLIENT_TEXT);
                        Socket in = ss.accept();
                        nbConnexions++;
                        System.out.println(SERVEUR_ACCEPTCLIENT_TEXT);
                        Thread client = new Thread(new GameThread(in, nbConnexions));
                        client.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        ecouteConnexions.start();
    }


    public static void main(String[] args) {

        Serveur s = new Serveur();
        s.demarrer();
        while(serveur_mode == SERVEUR_MODE_ON){

        }

    }

}

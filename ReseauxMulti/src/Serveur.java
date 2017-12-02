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

    private static String SERVEUR_START_TEXT                    = "[Serveur] Demarrage réussi ..";
    private static final String SERVEUR_STOP_TEXT               = "";
    private static final String SERVEUR_WAITINGCLIENT_TEXT      = "";
    private static final String SERVEUR_ACCEPTCLIENT_TEXT       = "";
    private static final String SERVEUR_REJECTCLIENT_TEXT       = "";

    private static int serveur_mode;
    private static final int SERVEUR_MODE_ON        = 1;
    private static final int SERVEUR_MODE_OFF       = 2;
    private static final int SERVEUR_MODE_PAUSE     = 3;

    private static ServerSocket ss;
    private static String servAdr;
    private static int port;

    private static Thread ecouteConnexions;


    private Serveur() {
        this.servAdr = "localhost";
        this.port = 5500;
    }

    private Serveur(String adr, int port){
            this.servAdr = adr;
            this.port = port;
    }

    private void demarrer(){
        try {
            ss = new ServerSocket(port, 10, InetAddress.getByName("localhost"));
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
                try{
                    System.out.println("JE COMMENCE");
                    Socket t = ss.accept();
                    System.out.println("JAI RECU");
                    Thread client = new Thread(new GameThread());
                    client.start();
                }
                catch (IOException e){ e.printStackTrace(); }
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

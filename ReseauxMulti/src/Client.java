import java.net.Socket;
import java.util.Scanner;

/**
 * Created by work on 02/12/17.
 */
public abstract class Client extends ReseauxToolbox{

    protected boolean connecte;
    protected Socket sock;

    protected String serveurAddress;
    protected int serveurPort;

    protected boolean EOC;  // Fin de communication
    protected boolean SAL;  // Le serveur attend une lettre
    protected boolean EGW;  // Fin de partie : victoire
    protected boolean EGL;  // Fin de partie : défaite
    protected boolean EWC;  // Mauvais caractere envoyé
    protected boolean LWS;  // Precedente tentative : réussie
    protected boolean LWF;  // Precedente tentative : echouée
    protected boolean GET;  // Recuperer le mot caché

    // Une fonction pour établir la connexion avec le serveur de jeu
    protected abstract void se_connecter();

    // Une fonction qui analyse et traite le message reçu du serveur
    // Lit sur l'entrée standard et envoi au serveur lorsque ce dernier attend une lettre
    protected abstract void receive_message(String message);

    protected abstract void jouer();

    protected abstract void stopClient();

}

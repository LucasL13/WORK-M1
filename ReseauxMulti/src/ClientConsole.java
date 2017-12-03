import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Created by work on 02/12/17.
 */
public class ClientConsole extends Client {

    private ClientConsole(){
        this.serveurAddress = DEFAULT_ADDR;
        this.serveurPort = DEFAULT_PORT;
    }

    private ClientConsole(int serveurPort){
        this.serveurAddress = DEFAULT_ADDR;
        this.serveurPort = serveurPort;
    }

    private ClientConsole(String serveurAdr, int serveurPort){
        this.serveurAddress = serveurAdr;
        this.serveurPort = serveurPort;
    }

    @Override
    protected void se_connecter() {
        try {
            sock = new Socket(this.serveurAddress, this.serveurPort);
            connecte = true;
        }catch(Exception e){e.printStackTrace();}
    }

    @Override
    protected void receive_message(String message) {

        EOC = (message.contains(END_OF_COMMUNICATION));
        SAL = (message.contains(SEND_A_LETTER));
        EGW = (message.contains(ENDGAME_WIN));
        EGL = (message.contains(ENDGAME_LOSE));
        EWC = (message.contains(ERROR_WRONGCHAR));
        LWS = (message.contains(LASTWORD_SUCCESS));
        LWF = (message.contains(LASTWORD_FAILED));
        GET = (message.contains(GET_WORD));

        if(EOC) {
            String motcache = message.substring(message.indexOf(END_OF_COMMUNICATION)+END_OF_COMMUNICATION.length(), message.length());
            System.out.println("\nLe mot caché était : " + motcache);
            message = message.replace(END_OF_COMMUNICATION, "");
            message = message.replace(motcache, "");
        }

        if(SAL)
            message = message.replace(SEND_A_LETTER, "");

        if(EGW) {
            message = message.replace(ENDGAME_WIN, "");
            message = "\nVous avez gagné en " + message + " coups ! Bravo\nAurevoir et merci d'avoir joué\n";
        }

        if(EGL){
            message = message.replace(ENDGAME_LOSE, "");
            message = "\nVous avez perdu ! Une prochaine fois peut-être !\nAurevoir et merci d'avoir joué\n";
        }

        if(EWC){
            message = message.replace(ERROR_WRONGCHAR, "");
            message = "Le caractere envoyé n'est pas correct ! Seules les lettres [a-zA-Z] sont autorisées\n";
        }

        if(LWS){
            int parsing = message.indexOf(GET_WORD);
            String firstPart = message.substring(LASTWORD_SUCCESS.length(), parsing);
            String secondPart = message.substring(parsing+GET_WORD.length(), message.length());
            message = "La tentative à réussi ! Il vous reste " + firstPart + " tentatives\n";
            message += "\t\tLe mot a trouver : " + secondPart + "\n";
        }
        else if(LWF){
            int parsing = message.indexOf(GET_WORD);
            String firstPart = message.substring(LASTWORD_FAILED.length(), parsing);
            String secondPart = message.substring(parsing+GET_WORD.length(), message.length());
            message = "La tentative à échoué ! Il vous reste " + firstPart + " tentatives\n";
            message += "\t\tLe mot a trouver : " + secondPart + "\n";
        }


        System.out.println(message);

        if(SAL && !EOC && !EGW && !EGL)
            read_letterInput();
        if(EWC)
            read_letterInput();
        if(EOC)
            stopClient();
    }

    protected void read_letterInput() {
        System.out.print("Entrez une lettre : ");
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        sendMessage(sock, c+"");
        SAL = false;
        EWC = false;
    }

    @Override
    protected void jouer() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            while(connecte){
                String message = "";
                while(br.ready()){
                    message += (char) br.read();
                }

                if(!message.equals(""))
                    receive_message(message);
            }
        }catch (Exception e){ e.printStackTrace(); }
    }

    @Override
    protected void stopClient() {
        try{
            sock.close();
            System.out.print("Le programme va s'arreter..");
            exit(0);
        } catch (IOException e){ e.printStackTrace(); }
    }

    public static void main(String[] args) {
        ClientConsole cc = new ClientConsole();
        cc.se_connecter();
        cc.jouer();
    }
}

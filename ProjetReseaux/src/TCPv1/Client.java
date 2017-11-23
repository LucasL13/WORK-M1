package TCPv1;

import java.net.Socket;

/**
 * Created by work on 23/11/17.
 */

public class Client extends ReseauxToolbox{

    private boolean connecte;
    private Socket sock;

    private void se_connecter(){

    }

    private void jouer(){

    }
    @Override
    void stopSocket(Socket soc) {
        connecte = false;
        if( !soc.equals(sock)) return;
        try{
            sock.close();
        }catch (Exception e){e.printStackTrace();}
    }

    Client(){
        se_connecter();
        while(connecte)
            jouer();
    }

}

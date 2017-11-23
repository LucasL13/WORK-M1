package TP6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by work on 23/11/17.
 */
public class ClientTcpDaytime{

    private String hostname;
    private int port;

    public ClientTcpDaytime() {
        this.hostname = "localhost";
        this.port = 50013;
    }

    public ClientTcpDaytime(String host, int port) {
        this.hostname = host;
        this.port = port;
    }

    public void lancerBR() {
        try {
            Socket sock = new Socket(InetAddress.getLocalHost(), this.port);
            BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            System.out.println("[Client] Recu : " + br.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ClientTcpDaytime ctd = new ClientTcpDaytime();
        ctd.lancerBR();
    }

}

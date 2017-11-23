package TP6;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Thread.sleep;

/**
 * Created by work on 23/11/17.
 */
public class ServeurTcpDaytime {

    private ServerSocket sr;
    private boolean actif;

    public ServeurTcpDaytime(int nb_clients_max) throws IOException {
        this.sr = new ServerSocket(50013, nb_clients_max, InetAddress.getLocalHost());
        this.actif = true;
    }

    private void running_serveur() {

        System.out.print("[Serveur] En ligne\n[Serveur] En attente de connexion..");

        Thread affichage = new Thread(new Runnable() {
            @Override
            public void run() {
                while (actif){
                    try {
                        System.out.print(".");
                        sleep(1000);
                    } catch (InterruptedException e) {e.printStackTrace();}
                }
            }
        });

        affichage.start();

        Thread wait_connexion = new Thread(new Runnable() {
            public void run() {
                while (actif) {
                    try {
                        Socket client = sr.accept();
                        System.out.println("\n[Serveur] Connexion acceptée.");
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                        Date d = new Date();
                        bw.write(d.toString());
                        bw.close();
                        client.close();
                        System.out.println("\n[Serveur] Connexion fermeée.");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        wait_connexion.start();
    }


    public void close() {
        actif = false;
    }

    public static void main(String[] args) throws IOException {

        ServeurTcpDaytime std = new ServeurTcpDaytime(10);
        std.running_serveur();

    }


}

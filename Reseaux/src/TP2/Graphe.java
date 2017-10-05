package TP2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graphe {

    // Nombres de sommets
    int ns;
    // Nombre d'arcs
    int na;
    // Graphe sous forme matricielle
    int[][] m;

    public Graphe(String pathname){

        try{

            File f = new File(pathname);
            Scanner sc = new Scanner(f);
            FileInputStream fis = new FileInputStream(pathname);

            this.ns = sc.nextInt();
            System.out.println("Nombre de sommets : " + this.ns);
            this.na = sc.nextInt();
            System.out.println("Nombre d'arcs : " + this.na);

            this.m = new int[this.ns][this.ns];

            for(int i=0; i < this.ns; i++)
                for(int j=0; j < this.ns; j++)
                    m[i][j] = 0;

            int c, sFrom, sTo, sWeight;
            for(int nba = 0; nba < this.na; nba++){
                sFrom = sc.nextInt();
                sTo = sc.nextInt();
                sWeight = sc.nextInt();
                System.out.println("Arc : " + sFrom + "->" + sTo + " = " + sWeight);

                this.m[sFrom][sTo] = sWeight;
                this.m[sTo][sFrom] = sWeight;
            }

           System.out.println("\nFin création du graphe");
        }catch(IOException e){e.printStackTrace();}

    }


    void initialisation(){

    }


    void add_arc(int sD, int sA, int valeur){
        this.m[sD][sA] = valeur;
        this.m[sA][sD] = valeur;
        this.na++;
    }

    void affiche_graphe(){

        System.out.println("\nAffichage du graphe : \n");

        for(int i=0; i < this.ns; i++) {
            for (int j = 0; j < this.ns; j++) {
                System.out.print(this.m[i][j]);
            }
            System.out.println();
        }

    }



}

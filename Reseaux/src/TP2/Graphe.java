package TP2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Graphe {

    // Nombres de sommets
    int ns;
    // Nombre d'arcs
    int na;
    // Graphe sous forme matricielle
    int[][] m;

    public Graphe(String pathname){

        try{
            FileInputStream fis = new FileInputStream(pathname);
            this.ns = Character.getNumericValue(fis.read());
            System.out.println("Nombre de sommets : " + this.ns);
            fis.read();
            this.na = Character.getNumericValue(fis.read());
            System.out.println("Nombre d'arcs : " + this.na);

            this.m = new int[this.ns][this.ns];

            for(int i=0; i < this.ns; i++)
                for(int j=0; j < this.ns; j++)
                    m[i][j] = 0;

            int c, sFrom, sTo, sWeight;
            while( (c = fis.read()) != -1){
                sFrom = Character.getNumericValue(fis.read());
                fis.read();
                sTo = Character.getNumericValue(fis.read());
                fis.read();
                sWeight = Character.getNumericValue(fis.read());
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

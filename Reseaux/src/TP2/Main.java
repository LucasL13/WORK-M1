package TP2;


import java.util.ArrayList;

public class Main {

    public static ArrayList<TableRoutage> listeTablesRoutage;


    public static void main(String[] args) {

        Graphe g = new Graphe("Graphe2.txt");
        Dijkstra2 djk = new Dijkstra2();

        g.affiche_graphe();

        TableRoutage trS0 = djk.calculer_table(g, 0);
        trS0.afficher_table_routage();

    }


}

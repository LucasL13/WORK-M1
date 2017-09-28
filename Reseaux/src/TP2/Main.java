package TP2;


import java.util.ArrayList;

public class Main {

    public static ArrayList<TableRoutage> listeTablesRoutage;


    public static void main(String[] args) {

        Graphe g = new Graphe("Graphe.txt");
        Dijkstra djk = new Dijkstra();

        g.affiche_graphe();

        TableRoutage trS0 = djk.calculer_table(g, 0);
        trS0.afficher_table_routage();

    }


}

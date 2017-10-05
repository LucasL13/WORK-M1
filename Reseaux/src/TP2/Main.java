package TP2;


import java.util.ArrayList;

public class Main {

    public static ArrayList<TableRoutage> listeTablesRoutage;


    public static void main(String[] args) {

        // Création et lecture du graphe
        Graphe g = new Graphe("Graphe.txt");

        // Création d'une instance de la classe qui possede les fonctions de calcul
        Dijkstra djk;

        // Vérification de la bonne lecture et du bon chargement du graphe
        // g.affiche_graphe();

        // Création des tables de routages pour tous les sommets
        listeTablesRoutage = new ArrayList<TableRoutage>();
        for(int i=0; i < g.ns; i++) {
            djk = new Dijkstra();
            listeTablesRoutage.add(djk.calculer_table(g, i));
            listeTablesRoutage.get(i).afficher_table_routage();
        }

        // Affichage de la table de routage du sommet 0 pour vérification
//        listeTablesRoutage.get(1).afficher_table_routage();


        GUI_Vue GUI_V = new GUI_Vue(g, listeTablesRoutage);
    }


}

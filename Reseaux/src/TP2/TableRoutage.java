package TP2;


import java.util.IntSummaryStatistics;

public class TableRoutage {


    // Tableau de routage [i][j]
    // i = 0 -> Destination
    // i = 1 -> Prochain saut
    // i = 2 -> Cout
    // j = Sommet à atteindre

    int sommet;
    int taille_table;
    int [][] tableRoutage;


    public TableRoutage(int Sommet, int taille_table){
        this.sommet = Sommet;
        this.tableRoutage = new int[3][taille_table];
        this.taille_table = taille_table;
    }


    public void afficher_table_routage(){
        System.out.println("\nAfficher table de routage du sommet \"" + sommet + "\"\n");

        for(int i=0; i < taille_table; i++){
            for(int j=0; j < 3; j++){
                System.out.print(tableRoutage[j][i]);
            }
            System.out.println();
        }
    }
}

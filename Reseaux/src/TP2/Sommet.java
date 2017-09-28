package TP2;

public class Sommet
{

    public String nom;

    // Tableau du prédécesseur (plus court chemin) [i][j]
    // i = Sommet
    // j = Sommet Prédécesseur
    int [][] pi;



    void Sommet(String nom, int nb_sommets){
        this.nom = nom;
        this.pi = new int[2][nb_sommets];
    }


    void init(int nb_sommets){
        for(int i=0; i<2; i++)
            for(int j=0; j < nb_sommets; j++)
               ;
    }





}

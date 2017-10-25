#include "graph.h"
#include <fstream>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

Graph::Graph(const char *name)
{

    int level[(W_WIDTH/T_SIZE)*3][(W_HEIGHT/T_SIZE)*3];
    ifstream file(name,ios::in);
    char a;
    int i = 0,j = 0;


    /* on verifie que l'ouverture n'a pas échoué */
    if(file){
        cout << "fichier ouvert !" << endl;
        while (file.get(a)) {

            string tmp(1,a);
//            cout << atoi(tmp.c_str());
            if(a == '1' || a == '0')
                level[i][j] = atoi(tmp.c_str());
                matrice[i][j] = new Sommet();
            j++;
            if(a == '\n'){
                i++;
                j=0;
            }

        }

        /* maintenant on va "link" les sommets entre eux*/

        for(int k=0;k<(W_WIDTH/T_SIZE)*3;k++){
            for(int l=0;l<(W_HEIGHT/T_SIZE)*3;l++){
                /* si il y a un sommet à la colonne l et la ligne k */
                if(matrice[l][k] != nullptr){
                    /* on regarde si il a un voisin à droite : */
                    if(matrice[l][k+1] != nullptr){
                        matrice[l][k]->setSDroite(matrice[l][k+1]);
                    }
                    /* on regarde si il a un voisin à gauche : */
                    if(matrice[l][k-1] != nullptr){
                        matrice[l][k]->setSGauche(matrice[l][k-1]);
                    }
                    /* on regarde si il a un voisin en haut : */
                    if(matrice[l+1][k] != nullptr){
                        matrice[l][k]->setSHaut(matrice[l+1][k]);
                    }
                    /* on regarde si il a un voisin en bas : */
                    if(matrice[l-1][k] != nullptr){
                        matrice[l][k]->setSBas(matrice[l-1][k]);
                    }
                }
            }
            cout << "" << endl;
        }

        /* affichage de la map */
        for(int k=0;k<(W_WIDTH/T_SIZE)*3;k++){
            for(int l=0;l<(W_HEIGHT/T_SIZE)*3;l++){
                cout << level[k][l];
                cout << " ";
            }
            cout << "" << endl;
        }
        file.close();
    }
    else{
        cout << "erreur impossible d'ouvrir le niveau !" << endl;

    }

}

void Graph::initMatrice(){
    for(int k=0;k<(W_WIDTH/T_SIZE)*3;k++){
        for(int l=0;l<(W_HEIGHT/T_SIZE)*3;l++){
            matrice[l][k] = nullptr;
        }
    }
}

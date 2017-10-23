#include "profil.h"
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

Profil::Profil(string name)
{
    score = 0;
    niv_max_atteint = 0;
    classement = -1;
    nom = name;
}

Profil::Profil(string nom, int score, int niv_max_atteint, int classement){
    this->score = score;
    this->nom = nom;
    this->niv_max_atteint = niv_max_atteint;
    this->classement = classement;
}

Profil* Profil::loadProfile(string filename){
    ifstream file(filename,ios::in);

    if(file){

        string nom;
        string tmp;
        int score;
        int niv_max_atteint;
        int classement;

        getline(file,nom); /* recup le nom */

        getline(file,tmp);
        score = atoi(tmp.c_str());

        getline(file,tmp);
        niv_max_atteint = atoi(tmp.c_str());

        getline(file,tmp);
        classement = atoi(tmp.c_str());
        file.close();
        return new Profil(nom,score,niv_max_atteint,classement);
    }
    else{
        cout << "impossible d'ouvrir le fichier" << endl;
        return nullptr;
    }
}

void Profil::saveProfile(string directory){
    ofstream file(directory+"/"+this->nom+".pf");

    if(file){
        file << nom << endl;
        file << score << endl;
        file << niv_max_atteint << endl;
        file << classement << endl;

        file.close();
    }
    else{
        cout << "impossible d'ouvrir le fichier" << endl;
    }
}

string Profil::getNom(){
    return nom;
}

int Profil::getClassement(){
    return classement;
}

int Profil::getNivMax(){
    return niv_max_atteint;
}

int Profil::getScore(){
    return score;
}

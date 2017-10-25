#include "sommet.h"

Sommet::Sommet()
{
    s_haut = nullptr;
    s_bas = nullptr;
    s_droite = nullptr;
    s_gauche = nullptr;
}

Sommet* Sommet::getSBas(){
    return s_bas;
}

Sommet* Sommet::getSHaut(){
    return s_haut;
}

Sommet* Sommet::getSGauche(){
    return s_gauche;
}

Sommet* Sommet::getSDroite(){
    return s_droite;
}

void Sommet::setSBas(Sommet *s){
    s_bas = s;
}

void Sommet::setSHaut(Sommet *s){
    s_haut = s;
}

void Sommet::setSGauche(Sommet *s){
    s_gauche = s;
}

void Sommet::setSDroite(Sommet *s){
    s_droite = s;
}

void Sommet::setGum(){
    contain_big_gum = false;
    contain_gum = true;
}

void Sommet::setBigGum(){
    contain_big_gum =true;
    contain_gum = false;
}

void Sommet::clearGum(){
    contain_big_gum = false;
    contain_gum = false;
}

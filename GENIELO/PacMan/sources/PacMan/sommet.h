#ifndef SOMMET_H
#define SOMMET_H


class Sommet
{
public:
    Sommet();
    Sommet* getSHaut();
    Sommet* getSBas();
    Sommet* getSDroite();
    Sommet* getSGauche();

    void setSHaut(Sommet* s);
    void setSBas(Sommet* s);
    void setSDroite(Sommet* s);
    void setSGauche(Sommet* s);

    void setBigGum();
    void setGum();
    void clearGum();

private:
        Sommet* s_haut;
        Sommet* s_bas;
        Sommet* s_droite;
        Sommet* s_gauche;
        bool  contain_gum;
        bool  contain_big_gum;

};

#endif // SOMMET_H

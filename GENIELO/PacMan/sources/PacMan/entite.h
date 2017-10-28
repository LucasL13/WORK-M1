#ifndef PERSONNAGE_H
#define PERSONNAGE_H

class personnage
{
    protected:
        //Position actuelle
        int x;
        int y;

        //Position precedente
        int prec_x;
        int prec_y;

        // Direction du personnage
        // 1 -> haut   | 2 -> bas
        // 3 -> gauche | 4 -> droite
        int dir;

    public:
        //Constructeur
        personnage(int x, int y);

        //Fonctions de déplacement
        bool MoveUp();
        bool MoveDown();
        bool MoveLeft();
        bool MoveRight();

        //Getter
        int GetX();
        int GetY();
        int GetPrec_X();
        int GetPrec_Y();
        int GetDir();

        //Setter
        void SetX(int x);
        void SetY(int y);
        void SetDir(int d);
};

#endif // PERSONNAGE_H

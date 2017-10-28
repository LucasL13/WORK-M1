#ifndef PACMAN_H
#define PACMAN_H

#include "personnage.h"

class pacman : public personnage
{
    public:
        pacman(int x, int y);
        bool Move(int Event);
};

#endif // PACMAN_H

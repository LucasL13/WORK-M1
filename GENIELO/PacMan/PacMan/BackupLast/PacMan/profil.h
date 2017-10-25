#ifndef PROFIL_H
#define PROFIL_H
#include <string>
using namespace std;

class Profil
{




public:
    Profil();
    Profil(string name);
    Profil(string nom, int score, int niv_max_atteint, int classement);
    void saveProfile(string directory);
    static Profil* loadProfile(string filename);
    string getNom();
    int getScore();
    int getNivMax();
    int getClassement();

private:
    int score;
    int niv_max_atteint;
    int classement;
    string nom;


};

#endif // PROFIL_H

#include "vueprofil.h"
#include "ui_vueprofil.h"
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string>

#include <sstream>



using namespace std;

VueProfil::VueProfil(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::VueProfil)
{
    ui->setupUi(this);
    //p = Profil::loadProfile("../PacMan/profil/"+profil+".pf");

    //QString str(p->getNom().c_str());
    //ui->label->setText(str);
}

VueProfil::~VueProfil()
{
    delete ui;
}

void VueProfil::setProfil(Profil *p){
    this->p = p;



    QString str(p->getNom().c_str());
    QString strNom("Nom : ");
    ui->nom->setText(strNom+str);

    stringstream ss;
    ss << p->getScore();
    QString str2(ss.str().c_str());
    QString strScore("Score : ");
    ui->score->setText(strScore+str2);

    stringstream ss2;
    ss2 << p->getNivMax();
    QString str4(ss2.str().c_str());
    QString strNiv("Niv max atteint : ");
    ui->niv_max->setText(strNiv+str4);

    stringstream ss3;
    ss3 << p->getClassement();
    QString str3(ss3.str().c_str());
    QString strClass("Classement : ");
    ui->classement->setText(strClass+str3);


}

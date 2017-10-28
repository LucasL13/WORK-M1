#include "vueprofile.h"
#include "ui_vueprofile.h"

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string>

#include <QFileDialog>
#include <QDebug>
#include <QWidget>
#include <QListWidget>

#include <sstream>



using namespace std;

VueProfile::VueProfile(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::VueProfile)
{
    ui->setupUi(this);
    //p = Profil::loadProfile("../PacMan/profil/"+profil+".pf");

    //QString str(p->getNom().c_str());
    //ui->label->setText(str);
}

VueProfile::~VueProfile()
{
    delete ui;
}

void VueProfile::setProfil(Profil *p){
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


    QWidget *scrollwidget = new QWidget;
    scrollwidget->setLayout(new QVBoxLayout);
    scrollwidget->setAccessibleName("TOTO");
    ui->scrollArea->setWidget(scrollwidget);


    // On crée un bouton niveau pour tous les niveaux déja atteints
    for(int i=1; i <= p->getNivMax(); i++){
        std::stringstream s;
        s << "Niveau " << i << std::endl << "Meilleur score : " << 0 << std::endl << "Meilleur temps : " << 0;
        std::stringstream name;
        name << "level" << i;

        QPushButton *bt = new QPushButton(s.str().c_str());
        bt->setStyleSheet("background-color : rgb(237, 212, 0);color: black;border: 3px solid gray;border-radius: 15px;");
        bt->setMinimumWidth(200);
        bt->setMinimumHeight(50);
        bt->setAccessibleName(name.str().c_str());

        connect(bt, SIGNAL (clicked()), this, SLOT (playlingLevelSelected()));

        scrollwidget->layout()->addWidget(bt);
    }

}

void VueProfile::changeProfil(){

    // On crée une boite de dialogue (fichiers) pour permettre a l'utilisateur
    // de naviguer et choisir le profil de jeu a utiliser

    QString fileName = QFileDialog::getOpenFileName(this,
        tr("Open File"), "./profil/", tr("Image Files (*.pf)"));

    setProfil(Profil::loadProfile(fileName.toStdString()));
}

void VueProfile::playlingLevelSelected(){


    // On reset d'abord tous les boutons de niveau
    QWidget *main = ui->scrollArea->widget();
    QObjectList levels = main->children();
    for(int i=0; i < levels.count(); i++){
        QObject *o = levels.at(i);
        QPushButton* btn = qobject_cast<QPushButton*>(o);
        if(btn != NULL){
                btn->setStyleSheet("background-color : rgb(237, 212, 0);color: black;border: 3px solid gray;border-radius: 15px;");
        }
    }

    // On modifie le style du bouton niveau selectionné
    QPushButton* button = qobject_cast<QPushButton*>(sender());
    if( button != NULL )
    {
        button->setStyleSheet("background-color: black; color: white; font-weight: bold;");
    }
}

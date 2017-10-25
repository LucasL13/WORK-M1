#include "mainwindow.h"
#include <QApplication>
#include <iostream>
#include "graph.h"
#include "profil.h"
#include "vueprofil.h"
#include "mainframe.h"

using namespace std;

int main(int argc, char *argv[])
{
      QApplication a(argc, argv);


//    string fileName = "../PacMan/levels/levelTest.lvl";
//    int k = 2;
//    Graph* graph = new Graph(fileName.c_str());

//    Profil* p = new Profil("michel");

//    p->saveProfile("../PacMan/profil");
    MainFrame mf;

    mf.show();

    return a.exec();
}

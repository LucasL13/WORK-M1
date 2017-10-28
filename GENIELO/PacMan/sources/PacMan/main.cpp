#include "mainwindow.h"
#include <QApplication>
#include <iostream>
#include "graph.h"
#include "profil.h"
#include "vueprofile.h"
#include <QStackedWidget>
#include <QVBoxLayout>

using namespace std;

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    MainWindow w;
    w.show();


    //box_layout.addWidget(&widgetStack);
    //w.setLayout(&box_layout);


//    string fileName = "../PacMan/levels/levelTest.lvl";
//    int k = 2;
//    Graph* graph = new Graph(fileName.c_str());

//    Profil* p = new Profil("michel");

//    p->saveProfile("../PacMan/profil");


    return a.exec();
}

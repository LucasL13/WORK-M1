#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <iostream>
#include "vueprofile.h"
#include "profil.h"
#include "vuemenu.h"


using namespace std;

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    /* on enleve les deux vue créée par le designer */
    QWidget* tmp = ui->stackedWidget->widget(0);
    ui->stackedWidget->removeWidget(tmp);

    tmp = ui->stackedWidget->widget(0);
    ui->stackedWidget->removeWidget(tmp);

    /* on créé le stack widget */


    /* on ajoute des element au QStackWidget */
    VueProfile* vp = new VueProfile();
    vp->show();
    vp->setProfil(Profil::loadProfile("../PacMan/profil/pierre.pf"));
    ui->stackedWidget->addWidget(vp);

    VueMenu* vm = new VueMenu();
    vm->show();

    ui->stackedWidget->addWidget(vm);

    ui->stackedWidget->setCurrentIndex(1);
}

MainWindow::~MainWindow()
{
    delete ui;
}

QStackedWidget* MainWindow::getStackedWidget(){
    return ui->stackedWidget;
}



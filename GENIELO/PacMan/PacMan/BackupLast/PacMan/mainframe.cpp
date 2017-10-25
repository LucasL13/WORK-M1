#include "mainframe.h"
#include "ui_mainframe.h"

MainFrame::MainFrame(QWidget *parent){
     ui = new Ui::MainFrame;
     uim = new Ui::vuemenu;
     uip = new Ui::VueProfil;

     ui->setupUi(this);
}

MainFrame::~MainFrame()
{
    delete ui;
}

void MainFrame::goMenu(){
    uim->setupUi(this);
}

void MainFrame::goProfile(){
    uip->setupUi(this);
}



void MainFrame::playAsGuest(){
    std::printf("TOOT MON GARS");
}
void MainFrame::connectToProfile(){
    uip->setupUi(this);

    VueProfil vp;
    QObject::connect(uip->changeProfile, SIGNAL(clicked()), &vp, SLOT(test()));
}

void MainFrame::createProfile(){

}

void MainFrame::backToHome(){
    ui->setupUi(this);
}

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

}
void MainFrame::createProfile(){

}

void MainFrame::backToHome(){
    ui->setupUi(this);
}

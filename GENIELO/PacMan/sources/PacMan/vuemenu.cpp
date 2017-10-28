#include "vuemenu.h"
#include "ui_vuemenu.h"
#include "mainwindow.h"

VueMenu::VueMenu(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::VueMenu)
{
    ui->setupUi(this);
}

VueMenu::~VueMenu()
{
    delete ui;
}

void VueMenu::on_play_as_guest_clicked(){

}

void VueMenu::on_load_profile_clicked(){
    ((QStackedWidget*)this->parent())->setCurrentIndex(0);
}


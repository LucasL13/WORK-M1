#ifndef MAINFRAME_H
#define MAINFRAME_H

#include <QMainWindow>
#include "vuemenu.h"
#include "ui_vuemenu.h"
#include "vueprofil.h"
#include "ui_vueprofil.h"

namespace Ui {
class MainFrame;
}

class MainFrame : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainFrame(QWidget *parent = 0);
    ~MainFrame();

public slots:
    void goMenu();
    void goProfile();
    void playAsGuest();
    void connectToProfile();
    void createProfile();
    void backToHome();

private:
    Ui::MainFrame *ui;
    Ui::vuemenu *uim;
    Ui::VueProfil *uip;
};

#endif // MAINFRAME_H

#ifndef VUEPROFIL_H
#define VUEPROFIL_H

#include <QMainWindow>
#include "profil.h"

namespace Ui {
class VueProfil;
}

class VueProfil : public QMainWindow
{
    Q_OBJECT

public slots:
    void test();

public:
    explicit VueProfil(QWidget *parent = 0);
    ~VueProfil();
    void setProfil(Profil *p);

private:
    Ui::VueProfil *ui;
    Profil* p;
};

#endif // VUEPROFIL_H

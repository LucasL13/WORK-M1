#ifndef VUEPROFILE_H
#define VUEPROFILE_H

#include <QWidget>
#include "profil.h"

namespace Ui {
class VueProfile;
}

class VueProfile : public QWidget
{
    Q_OBJECT

public:
    explicit VueProfile(QWidget *parent = 0);
    ~VueProfile();
    void setProfil(Profil *p);

public slots:
    void changeProfil();
    void playlingLevelSelected();

private:
    Ui::VueProfile *ui;

    Profil* p;


};

#endif // VUEPROFILE_H

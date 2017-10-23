#ifndef VUEPROFIL_H
#define VUEPROFIL_H

#include <QMainWindow>

namespace Ui {
class VueProfil;
}

class VueProfil : public QMainWindow
{
    Q_OBJECT

public:
    explicit VueProfil(QWidget *parent = 0);
    ~VueProfil();

private:
    Ui::VueProfil *ui;
};

#endif // VUEPROFIL_H

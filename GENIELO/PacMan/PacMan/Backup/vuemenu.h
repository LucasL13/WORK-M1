#ifndef VUEMENU_H
#define VUEMENU_H

#include <QMainWindow>

namespace UI {
class VueMenu;
}

class VueMenu : public QMainWindow
{
    Q_OBJECT

public:
    explicit VueMenu(QWidget *parent = 0);
    ~VueMenu();

public slots:
    void playAsGuest();
    void connectToProfile();
    void createProfile();

private:

};

#endif // VUEMENU_H

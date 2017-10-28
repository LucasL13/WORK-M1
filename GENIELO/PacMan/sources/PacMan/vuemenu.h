#ifndef VUEMENU_H
#define VUEMENU_H

#include <QWidget>


namespace Ui {
class VueMenu;
}

class VueMenu : public QWidget
{
    Q_OBJECT

public:
    explicit VueMenu(QWidget *parent = 0);
    ~VueMenu();

private:
    Ui::VueMenu *ui;


public slots:
    void on_play_as_guest_clicked();
    void on_load_profile_clicked();
};

#endif // VUEMENU_H

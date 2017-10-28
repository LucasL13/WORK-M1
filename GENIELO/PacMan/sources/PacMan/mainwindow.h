#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QStackedWidget>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT




public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();
    QStackedWidget* getStackedWidget();

private:
    Ui::MainWindow *ui;
    QStackedWidget* stackedWidget;
};

#endif // MAINWINDOW_H

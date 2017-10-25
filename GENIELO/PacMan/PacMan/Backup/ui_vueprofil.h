/********************************************************************************
** Form generated from reading UI file 'vueprofil.ui'
**
** Created by: Qt User Interface Compiler version 5.9.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_VUEPROFIL_H
#define UI_VUEPROFIL_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_VueProfil
{
public:
    QWidget *centralwidget;
    QPushButton *pushButton;
    QMenuBar *menubar;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *VueProfil)
    {
        if (VueProfil->objectName().isEmpty())
            VueProfil->setObjectName(QStringLiteral("VueProfil"));
        VueProfil->resize(800, 600);
        centralwidget = new QWidget(VueProfil);
        centralwidget->setObjectName(QStringLiteral("centralwidget"));
        pushButton = new QPushButton(centralwidget);
        pushButton->setObjectName(QStringLiteral("pushButton"));
        pushButton->setGeometry(QRect(220, 180, 80, 25));
        VueProfil->setCentralWidget(centralwidget);
        menubar = new QMenuBar(VueProfil);
        menubar->setObjectName(QStringLiteral("menubar"));
        menubar->setGeometry(QRect(0, 0, 800, 22));
        VueProfil->setMenuBar(menubar);
        statusbar = new QStatusBar(VueProfil);
        statusbar->setObjectName(QStringLiteral("statusbar"));
        VueProfil->setStatusBar(statusbar);

        retranslateUi(VueProfil);

        QMetaObject::connectSlotsByName(VueProfil);
    } // setupUi

    void retranslateUi(QMainWindow *VueProfil)
    {
        VueProfil->setWindowTitle(QApplication::translate("VueProfil", "MainWindow", Q_NULLPTR));
        pushButton->setText(QApplication::translate("VueProfil", "PushButton", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class VueProfil: public Ui_VueProfil {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_VUEPROFIL_H

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
#include <QtWidgets/QLabel>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_VueProfil
{
public:
    QWidget *centralwidget;
    QWidget *verticalLayoutWidget;
    QVBoxLayout *verticalLayout;
    QLabel *label;
    QMenuBar *menubar;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *VueProfil)
    {
        if (VueProfil->objectName().isEmpty())
            VueProfil->setObjectName(QStringLiteral("VueProfil"));
        VueProfil->resize(800, 600);
        centralwidget = new QWidget(VueProfil);
        centralwidget->setObjectName(QStringLiteral("centralwidget"));
        verticalLayoutWidget = new QWidget(centralwidget);
        verticalLayoutWidget->setObjectName(QStringLiteral("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(130, 110, 521, 361));
        verticalLayout = new QVBoxLayout(verticalLayoutWidget);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        label = new QLabel(verticalLayoutWidget);
        label->setObjectName(QStringLiteral("label"));

        verticalLayout->addWidget(label);

        VueProfil->setCentralWidget(centralwidget);
        menubar = new QMenuBar(VueProfil);
        menubar->setObjectName(QStringLiteral("menubar"));
        menubar->setGeometry(QRect(0, 0, 800, 19));
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
        label->setText(QApplication::translate("VueProfil", "VUE PROFIL MON GARS", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class VueProfil: public Ui_VueProfil {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_VUEPROFIL_H

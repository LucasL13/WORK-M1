/********************************************************************************
** Form generated from reading UI file 'menu.ui'
**
** Created by: Qt User Interface Compiler version 5.9.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MENU_H
#define UI_MENU_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Menu
{
public:
    QWidget *centralWidget;
    QWidget *verticalLayoutWidget;
    QVBoxLayout *verticalLayout;
    QVBoxLayout *verticalLayout_3;
    QLabel *label;
    QVBoxLayout *verticalLayout_4;
    QPushButton *Menu_playAsGuest;
    QPushButton *Menu_connectToProfile;
    QPushButton *Menu_createProfile;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *Menu)
    {
        if (Menu->objectName().isEmpty())
            Menu->setObjectName(QStringLiteral("Menu"));
        Menu->resize(698, 601);
        centralWidget = new QWidget(Menu);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        verticalLayoutWidget = new QWidget(centralWidget);
        verticalLayoutWidget->setObjectName(QStringLiteral("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(40, 90, 604, 396));
        verticalLayout = new QVBoxLayout(verticalLayoutWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        verticalLayout_3 = new QVBoxLayout();
        verticalLayout_3->setSpacing(6);
        verticalLayout_3->setObjectName(QStringLiteral("verticalLayout_3"));
        label = new QLabel(verticalLayoutWidget);
        label->setObjectName(QStringLiteral("label"));
        label->setPixmap(QPixmap(QString::fromUtf8("Pacman_logo.png")));
        label->setScaledContents(true);

        verticalLayout_3->addWidget(label);


        verticalLayout->addLayout(verticalLayout_3);

        verticalLayout_4 = new QVBoxLayout();
        verticalLayout_4->setSpacing(6);
        verticalLayout_4->setObjectName(QStringLiteral("verticalLayout_4"));
        Menu_playAsGuest = new QPushButton(verticalLayoutWidget);
        Menu_playAsGuest->setObjectName(QStringLiteral("Menu_playAsGuest"));

        verticalLayout_4->addWidget(Menu_playAsGuest);

        Menu_connectToProfile = new QPushButton(verticalLayoutWidget);
        Menu_connectToProfile->setObjectName(QStringLiteral("Menu_connectToProfile"));
        Menu_connectToProfile->setCheckable(false);
        Menu_connectToProfile->setFlat(false);

        verticalLayout_4->addWidget(Menu_connectToProfile);

        Menu_createProfile = new QPushButton(verticalLayoutWidget);
        Menu_createProfile->setObjectName(QStringLiteral("Menu_createProfile"));

        verticalLayout_4->addWidget(Menu_createProfile);


        verticalLayout->addLayout(verticalLayout_4);

        Menu->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(Menu);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 698, 19));
        Menu->setMenuBar(menuBar);
        mainToolBar = new QToolBar(Menu);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        Menu->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(Menu);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        Menu->setStatusBar(statusBar);

        retranslateUi(Menu);
        QObject::connect(Menu_createProfile, SIGNAL(clicked()), Menu, SLOT(handleButtons()));

        QMetaObject::connectSlotsByName(Menu);
    } // setupUi

    void retranslateUi(QMainWindow *Menu)
    {
        Menu->setWindowTitle(QApplication::translate("Menu", "Menu", Q_NULLPTR));
        label->setText(QString());
        Menu_playAsGuest->setText(QApplication::translate("Menu", "Jouer en tant qu'invit\303\251", Q_NULLPTR));
        Menu_connectToProfile->setText(QApplication::translate("Menu", "Se connecter \303\240 un profil", Q_NULLPTR));
        Menu_createProfile->setText(QApplication::translate("Menu", "Cr\303\251er un nouveau profil", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class Menu: public Ui_Menu {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MENU_H

/********************************************************************************
** Form generated from reading UI file 'VueMenu.ui'
**
** Created by: Qt User Interface Compiler version 5.9.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_VUEMENU_H
#define UI_VUEMENU_H

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

class Ui_VueMenu
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

    void setupUi(QMainWindow *VueMenu)
    {
        if (VueMenu->objectName().isEmpty())
            VueMenu->setObjectName(QStringLiteral("VueMenu"));
        VueMenu->resize(698, 601);
        centralWidget = new QWidget(VueMenu);
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

        VueMenu->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(VueMenu);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 698, 19));
        VueMenu->setMenuBar(menuBar);
        mainToolBar = new QToolBar(VueMenu);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        VueMenu->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(VueMenu);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        VueMenu->setStatusBar(statusBar);

        retranslateUi(VueMenu);
        QObject::connect(Menu_playAsGuest, SIGNAL(clicked()), VueMenu, SLOT(playAsGuest()));
        QObject::connect(Menu_connectToProfile, SIGNAL(clicked()), VueMenu, SLOT(connectToProfile()));
        QObject::connect(Menu_createProfile, SIGNAL(clicked()), VueMenu, SLOT(createProfile()));

        QMetaObject::connectSlotsByName(VueMenu);
    } // setupUi

    void retranslateUi(QMainWindow *VueMenu)
    {
        VueMenu->setWindowTitle(QApplication::translate("VueMenu", "Menu", Q_NULLPTR));
        label->setText(QString());
        Menu_playAsGuest->setText(QApplication::translate("VueMenu", "Jouer en tant qu'invit\303\251", Q_NULLPTR));
        Menu_connectToProfile->setText(QApplication::translate("VueMenu", "Se connecter \303\240 un profil", Q_NULLPTR));
        Menu_createProfile->setText(QApplication::translate("VueMenu", "Cr\303\251er un nouveau profil", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class VueMenu: public Ui_VueMenu {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_VUEMENU_H

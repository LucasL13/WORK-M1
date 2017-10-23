/********************************************************************************
** Form generated from reading UI file 'vuemenu.ui'
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
#include <QtWidgets/QCommandLinkButton>
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

class Ui_vuemenu
{
public:
    QWidget *centralWidget;
    QWidget *verticalLayoutWidget;
    QVBoxLayout *verticalLayout;
    QVBoxLayout *verticalLayout_4;
    QLabel *label;
    QPushButton *Menu_playAsGuest;
    QPushButton *Menu_connectToProfile;
    QPushButton *Menu_createProfile;
    QWidget *verticalLayoutWidget_2;
    QVBoxLayout *verticalLayout_2;
    QCommandLinkButton *commandLinkButton;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *vuemenu)
    {
        if (vuemenu->objectName().isEmpty())
            vuemenu->setObjectName(QStringLiteral("vuemenu"));
        vuemenu->resize(698, 601);
        vuemenu->setMinimumSize(QSize(300, 300));
        vuemenu->setAutoFillBackground(true);
        centralWidget = new QWidget(vuemenu);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        centralWidget->setAutoFillBackground(true);
        centralWidget->setStyleSheet(QStringLiteral("background-color: rgb(52, 101, 164);"));
        verticalLayoutWidget = new QWidget(centralWidget);
        verticalLayoutWidget->setObjectName(QStringLiteral("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(40, 90, 604, 418));
        verticalLayout = new QVBoxLayout(verticalLayoutWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        verticalLayout_4 = new QVBoxLayout();
        verticalLayout_4->setSpacing(6);
        verticalLayout_4->setObjectName(QStringLiteral("verticalLayout_4"));
        label = new QLabel(verticalLayoutWidget);
        label->setObjectName(QStringLiteral("label"));
        label->setEnabled(true);
        QSizePolicy sizePolicy(QSizePolicy::Maximum, QSizePolicy::Maximum);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(label->sizePolicy().hasHeightForWidth());
        label->setSizePolicy(sizePolicy);
        label->setMinimumSize(QSize(300, 300));
        label->setPixmap(QPixmap(QString::fromUtf8("Pacman_logo.png")));
        label->setScaledContents(true);

        verticalLayout_4->addWidget(label);

        Menu_playAsGuest = new QPushButton(verticalLayoutWidget);
        Menu_playAsGuest->setObjectName(QStringLiteral("Menu_playAsGuest"));
        Menu_playAsGuest->setAutoFillBackground(false);
        Menu_playAsGuest->setStyleSheet(QLatin1String("\n"
"color: rgb(243, 243, 243);\n"
"\n"
"padding: 8px;\n"
""));

        verticalLayout_4->addWidget(Menu_playAsGuest);

        Menu_connectToProfile = new QPushButton(verticalLayoutWidget);
        Menu_connectToProfile->setObjectName(QStringLiteral("Menu_connectToProfile"));
        Menu_connectToProfile->setStyleSheet(QLatin1String("\n"
"color: rgb(243, 243, 243);\n"
"\n"
"padding: 8px;\n"
""));
        Menu_connectToProfile->setCheckable(false);
        Menu_connectToProfile->setFlat(false);

        verticalLayout_4->addWidget(Menu_connectToProfile);

        Menu_createProfile = new QPushButton(verticalLayoutWidget);
        Menu_createProfile->setObjectName(QStringLiteral("Menu_createProfile"));
        Menu_createProfile->setStyleSheet(QLatin1String("\n"
"color: rgb(243, 243, 243);\n"
"\n"
"padding: 8px;\n"
""));

        verticalLayout_4->addWidget(Menu_createProfile);


        verticalLayout->addLayout(verticalLayout_4);

        verticalLayoutWidget_2 = new QWidget(centralWidget);
        verticalLayoutWidget_2->setObjectName(QStringLiteral("verticalLayoutWidget_2"));
        verticalLayoutWidget_2->setGeometry(QRect(40, 30, 171, 41));
        verticalLayout_2 = new QVBoxLayout(verticalLayoutWidget_2);
        verticalLayout_2->setSpacing(6);
        verticalLayout_2->setContentsMargins(11, 11, 11, 11);
        verticalLayout_2->setObjectName(QStringLiteral("verticalLayout_2"));
        verticalLayout_2->setContentsMargins(0, 0, 0, 0);
        commandLinkButton = new QCommandLinkButton(verticalLayoutWidget_2);
        commandLinkButton->setObjectName(QStringLiteral("commandLinkButton"));
        QFont font;
        font.setPointSize(12);
        commandLinkButton->setFont(font);
        commandLinkButton->setLayoutDirection(Qt::LeftToRight);
        commandLinkButton->setAutoFillBackground(false);
        commandLinkButton->setStyleSheet(QLatin1String("color: rgb(238, 238, 236);\n"
"background-color: rgb(52, 101, 164);\n"
"\n"
""));

        verticalLayout_2->addWidget(commandLinkButton);

        vuemenu->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(vuemenu);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 698, 19));
        vuemenu->setMenuBar(menuBar);
        mainToolBar = new QToolBar(vuemenu);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        vuemenu->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(vuemenu);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        vuemenu->setStatusBar(statusBar);

        retranslateUi(vuemenu);
        QObject::connect(Menu_playAsGuest, SIGNAL(clicked()), vuemenu, SLOT(playAsGuest()));
        QObject::connect(Menu_connectToProfile, SIGNAL(clicked()), vuemenu, SLOT(connectToProfile()));
        QObject::connect(Menu_createProfile, SIGNAL(clicked()), vuemenu, SLOT(createProfile()));
        QObject::connect(commandLinkButton, SIGNAL(clicked()), vuemenu, SLOT(backToHome()));

        QMetaObject::connectSlotsByName(vuemenu);
    } // setupUi

    void retranslateUi(QMainWindow *vuemenu)
    {
        vuemenu->setWindowTitle(QApplication::translate("vuemenu", "Menu", Q_NULLPTR));
        label->setText(QString());
        Menu_playAsGuest->setText(QApplication::translate("vuemenu", "Jouer en tant qu'invit\303\251", Q_NULLPTR));
        Menu_connectToProfile->setText(QApplication::translate("vuemenu", "Se connecter \303\240 un profil", Q_NULLPTR));
        Menu_createProfile->setText(QApplication::translate("vuemenu", "Cr\303\251er un nouveau profil", Q_NULLPTR));
        commandLinkButton->setText(QApplication::translate("vuemenu", "Back to home", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class vuemenu: public Ui_vuemenu {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_VUEMENU_H

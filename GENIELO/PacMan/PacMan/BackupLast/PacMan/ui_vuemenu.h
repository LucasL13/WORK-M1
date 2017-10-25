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
#include <QtWidgets/QPushButton>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_vuemenu
{
public:
    QWidget *centralWidget;
    QWidget *verticalLayoutWidget;
    QVBoxLayout *verticalLayout_2;
    QCommandLinkButton *commandLinkButton;
    QLabel *label;
    QPushButton *Menu_createProfile;
    QPushButton *Menu_playAsGuest;
    QPushButton *Menu_connectToProfile;

    void setupUi(QMainWindow *vuemenu)
    {
        if (vuemenu->objectName().isEmpty())
            vuemenu->setObjectName(QStringLiteral("vuemenu"));
        vuemenu->resize(1280, 960);
        QSizePolicy sizePolicy(QSizePolicy::Maximum, QSizePolicy::Maximum);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(vuemenu->sizePolicy().hasHeightForWidth());
        vuemenu->setSizePolicy(sizePolicy);
        vuemenu->setMinimumSize(QSize(1280, 960));
        vuemenu->setMaximumSize(QSize(1280, 960));
        vuemenu->setAutoFillBackground(true);
        centralWidget = new QWidget(vuemenu);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        centralWidget->setMinimumSize(QSize(1280, 960));
        centralWidget->setMaximumSize(QSize(1280, 960));
        centralWidget->setAutoFillBackground(false);
        centralWidget->setStyleSheet(QStringLiteral("background-color: rgb(52, 101, 164);"));
        verticalLayoutWidget = new QWidget(centralWidget);
        verticalLayoutWidget->setObjectName(QStringLiteral("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(0, 90, 1311, 792));
        verticalLayout_2 = new QVBoxLayout(verticalLayoutWidget);
        verticalLayout_2->setSpacing(6);
        verticalLayout_2->setContentsMargins(11, 11, 11, 11);
        verticalLayout_2->setObjectName(QStringLiteral("verticalLayout_2"));
        verticalLayout_2->setSizeConstraint(QLayout::SetMaximumSize);
        verticalLayout_2->setContentsMargins(0, 0, 0, 0);
        commandLinkButton = new QCommandLinkButton(verticalLayoutWidget);
        commandLinkButton->setObjectName(QStringLiteral("commandLinkButton"));
        QSizePolicy sizePolicy1(QSizePolicy::Minimum, QSizePolicy::Maximum);
        sizePolicy1.setHorizontalStretch(0);
        sizePolicy1.setVerticalStretch(0);
        sizePolicy1.setHeightForWidth(commandLinkButton->sizePolicy().hasHeightForWidth());
        commandLinkButton->setSizePolicy(sizePolicy1);
        QFont font;
        font.setPointSize(12);
        commandLinkButton->setFont(font);
        commandLinkButton->setLayoutDirection(Qt::LeftToRight);
        commandLinkButton->setAutoFillBackground(false);
        commandLinkButton->setStyleSheet(QLatin1String("color: rgb(238, 238, 236);\n"
"background-color: rgb(211, 215, 207);\n"
"\n"
""));

        verticalLayout_2->addWidget(commandLinkButton);

        label = new QLabel(verticalLayoutWidget);
        label->setObjectName(QStringLiteral("label"));
        label->setEnabled(true);
        sizePolicy.setHeightForWidth(label->sizePolicy().hasHeightForWidth());
        label->setSizePolicy(sizePolicy);
        label->setMinimumSize(QSize(0, 0));
        label->setPixmap(QPixmap(QString::fromUtf8("graphics_pacman/logo_small.png")));
        label->setScaledContents(true);

        verticalLayout_2->addWidget(label, 0, Qt::AlignHCenter);

        Menu_createProfile = new QPushButton(verticalLayoutWidget);
        Menu_createProfile->setObjectName(QStringLiteral("Menu_createProfile"));
        sizePolicy.setHeightForWidth(Menu_createProfile->sizePolicy().hasHeightForWidth());
        Menu_createProfile->setSizePolicy(sizePolicy);
        Menu_createProfile->setStyleSheet(QLatin1String("color: rgb(255, 255, 255);\n"
"padding: 20px;\n"
"padding-right: 50px;\n"
"padding-left: 50px;\n"
"margin-bottom: 20px;"));

        verticalLayout_2->addWidget(Menu_createProfile, 0, Qt::AlignHCenter);

        Menu_playAsGuest = new QPushButton(verticalLayoutWidget);
        Menu_playAsGuest->setObjectName(QStringLiteral("Menu_playAsGuest"));
        sizePolicy.setHeightForWidth(Menu_playAsGuest->sizePolicy().hasHeightForWidth());
        Menu_playAsGuest->setSizePolicy(sizePolicy);
        Menu_playAsGuest->setAutoFillBackground(false);
        Menu_playAsGuest->setStyleSheet(QLatin1String("color: rgb(255, 255, 255);\n"
"padding: 20px;\n"
"padding-right: 50px;\n"
"padding-left: 50px;\n"
"margin-bottom: 20px;"));

        verticalLayout_2->addWidget(Menu_playAsGuest, 0, Qt::AlignHCenter);

        Menu_connectToProfile = new QPushButton(verticalLayoutWidget);
        Menu_connectToProfile->setObjectName(QStringLiteral("Menu_connectToProfile"));
        sizePolicy.setHeightForWidth(Menu_connectToProfile->sizePolicy().hasHeightForWidth());
        Menu_connectToProfile->setSizePolicy(sizePolicy);
        Menu_connectToProfile->setStyleSheet(QLatin1String("color: rgb(255, 255, 255);\n"
"padding: 20px;\n"
"padding-right: 50px;\n"
"padding-left: 50px;\n"
"margin-bottom: 20px;"));
        Menu_connectToProfile->setCheckable(false);
        Menu_connectToProfile->setFlat(false);

        verticalLayout_2->addWidget(Menu_connectToProfile, 0, Qt::AlignHCenter);

        vuemenu->setCentralWidget(centralWidget);

        retranslateUi(vuemenu);
        QObject::connect(commandLinkButton, SIGNAL(clicked()), vuemenu, SLOT(backToHome()));
        QObject::connect(Menu_connectToProfile, SIGNAL(clicked()), vuemenu, SLOT(connectToProfile()));

        QMetaObject::connectSlotsByName(vuemenu);
    } // setupUi

    void retranslateUi(QMainWindow *vuemenu)
    {
        vuemenu->setWindowTitle(QApplication::translate("vuemenu", "Menu", Q_NULLPTR));
        commandLinkButton->setText(QApplication::translate("vuemenu", "Back to home", Q_NULLPTR));
        label->setText(QString());
        Menu_createProfile->setText(QApplication::translate("vuemenu", "Cr\303\251er un nouveau profil", Q_NULLPTR));
        Menu_playAsGuest->setText(QApplication::translate("vuemenu", "Jouer en tant qu'invit\303\251", Q_NULLPTR));
        Menu_connectToProfile->setText(QApplication::translate("vuemenu", "Se connecter \303\240 un profil", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class vuemenu: public Ui_vuemenu {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_VUEMENU_H

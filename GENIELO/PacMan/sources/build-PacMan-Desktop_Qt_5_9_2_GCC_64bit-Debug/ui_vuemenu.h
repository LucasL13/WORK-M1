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
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_VueMenu
{
public:
    QWidget *verticalLayoutWidget;
    QVBoxLayout *verticalLayout;
    QPushButton *play_as_guest;
    QPushButton *load_profile;
    QLabel *label;

    void setupUi(QWidget *VueMenu)
    {
        if (VueMenu->objectName().isEmpty())
            VueMenu->setObjectName(QStringLiteral("VueMenu"));
        VueMenu->resize(1280, 800);
        QSizePolicy sizePolicy(QSizePolicy::Minimum, QSizePolicy::Minimum);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(VueMenu->sizePolicy().hasHeightForWidth());
        VueMenu->setSizePolicy(sizePolicy);
        VueMenu->setMinimumSize(QSize(1280, 800));
        VueMenu->setMaximumSize(QSize(1280, 800));
        VueMenu->setAutoFillBackground(true);
        VueMenu->setStyleSheet(QStringLiteral(""));
        verticalLayoutWidget = new QWidget(VueMenu);
        verticalLayoutWidget->setObjectName(QStringLiteral("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(0, 520, 1361, 201));
        verticalLayout = new QVBoxLayout(verticalLayoutWidget);
        verticalLayout->setSpacing(4);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        verticalLayout->setSizeConstraint(QLayout::SetNoConstraint);
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        play_as_guest = new QPushButton(verticalLayoutWidget);
        play_as_guest->setObjectName(QStringLiteral("play_as_guest"));
        play_as_guest->setMinimumSize(QSize(300, 0));
        QFont font;
        font.setBold(true);
        font.setWeight(75);
        play_as_guest->setFont(font);
        play_as_guest->setMouseTracking(false);
        play_as_guest->setLayoutDirection(Qt::LeftToRight);
        play_as_guest->setStyleSheet(QLatin1String("padding: 10px;\n"
"background-color :rgb(168, 146, 6);\n"
"color: white;\n"
"border: 3px solid white;\n"
"border-radius: 15px;\n"
""));

        verticalLayout->addWidget(play_as_guest, 0, Qt::AlignHCenter);

        load_profile = new QPushButton(verticalLayoutWidget);
        load_profile->setObjectName(QStringLiteral("load_profile"));
        QSizePolicy sizePolicy1(QSizePolicy::Maximum, QSizePolicy::Maximum);
        sizePolicy1.setHorizontalStretch(0);
        sizePolicy1.setVerticalStretch(0);
        sizePolicy1.setHeightForWidth(load_profile->sizePolicy().hasHeightForWidth());
        load_profile->setSizePolicy(sizePolicy1);
        load_profile->setMinimumSize(QSize(300, 0));
        load_profile->setFont(font);
        load_profile->setStyleSheet(QLatin1String("padding: 10px;\n"
"background-color :rgb(168, 146, 6);\n"
"color: white;\n"
"border: 3px solid white;\n"
"border-radius: 15px;"));

        verticalLayout->addWidget(load_profile, 0, Qt::AlignHCenter);

        label = new QLabel(VueMenu);
        label->setObjectName(QStringLiteral("label"));
        label->setGeometry(QRect(0, 0, 1281, 801));
        label->setPixmap(QPixmap(QString::fromUtf8("graphics_pacman/Source_bg.jpg")));
        label->setScaledContents(true);
        label->raise();
        verticalLayoutWidget->raise();

        retranslateUi(VueMenu);
        QObject::connect(play_as_guest, SIGNAL(pressed()), VueMenu, SLOT(hide()));
        QObject::connect(play_as_guest, SIGNAL(released()), VueMenu, SLOT(show()));

        QMetaObject::connectSlotsByName(VueMenu);
    } // setupUi

    void retranslateUi(QWidget *VueMenu)
    {
        VueMenu->setWindowTitle(QApplication::translate("VueMenu", "Form", Q_NULLPTR));
        play_as_guest->setText(QApplication::translate("VueMenu", "JOUER EN TANT QU'INVIT\303\211", Q_NULLPTR));
        load_profile->setText(QApplication::translate("VueMenu", "CHARGER UN PROFIL", Q_NULLPTR));
        label->setText(QString());
    } // retranslateUi

};

namespace Ui {
    class VueMenu: public Ui_VueMenu {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_VUEMENU_H

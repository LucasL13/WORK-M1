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
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_VueMenu
{
public:
    QLabel *label;
    QPushButton *guest;
    QPushButton *load_profile;

    void setupUi(QWidget *VueMenu)
    {
        if (VueMenu->objectName().isEmpty())
            VueMenu->setObjectName(QStringLiteral("VueMenu"));
        VueMenu->resize(800, 800);
        VueMenu->setMinimumSize(QSize(800, 800));
        VueMenu->setMaximumSize(QSize(800, 800));
        label = new QLabel(VueMenu);
        label->setObjectName(QStringLiteral("label"));
        label->setGeometry(QRect(190, 40, 461, 81));
        label->setTextFormat(Qt::AutoText);
        label->setAlignment(Qt::AlignCenter);
        label->setWordWrap(false);
        guest = new QPushButton(VueMenu);
        guest->setObjectName(QStringLiteral("guest"));
        guest->setGeometry(QRect(50, 260, 691, 121));
        guest->setCheckable(false);
        guest->setAutoRepeat(false);
        load_profile = new QPushButton(VueMenu);
        load_profile->setObjectName(QStringLiteral("load_profile"));
        load_profile->setGeometry(QRect(50, 520, 691, 111));

        retranslateUi(VueMenu);

        QMetaObject::connectSlotsByName(VueMenu);
    } // setupUi

    void retranslateUi(QWidget *VueMenu)
    {
        VueMenu->setWindowTitle(QApplication::translate("VueMenu", "Form", Q_NULLPTR));
        label->setText(QApplication::translate("VueMenu", "PacMan V1.0", Q_NULLPTR));
        guest->setText(QApplication::translate("VueMenu", "Jouer en tant qu'invit\303\251", Q_NULLPTR));
        load_profile->setText(QApplication::translate("VueMenu", "Charger un profil", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class VueMenu: public Ui_VueMenu {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_VUEMENU_H

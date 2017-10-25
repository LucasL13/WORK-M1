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
#include <QtWidgets/QGroupBox>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_VueProfil
{
public:
    QWidget *centralwidget;
    QGroupBox *groupBox;
    QWidget *verticalLayoutWidget;
    QVBoxLayout *verticalLayout;
    QLabel *nom;
    QLabel *score;
    QLabel *niv_max;
    QLabel *classement;
    QLabel *gomme_av;
    QLabel *fantom_abattus;
    QGroupBox *groupBox_2;
    QListWidget *listWidget;
    QPushButton *pushButton;
    QPushButton *pushButton_2;
    QMenuBar *menubar;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *VueProfil)
    {
        if (VueProfil->objectName().isEmpty())
            VueProfil->setObjectName(QStringLiteral("VueProfil"));
        VueProfil->resize(800, 600);
        VueProfil->setMinimumSize(QSize(800, 600));
        VueProfil->setMaximumSize(QSize(800, 600));
        centralwidget = new QWidget(VueProfil);
        centralwidget->setObjectName(QStringLiteral("centralwidget"));
        groupBox = new QGroupBox(centralwidget);
        groupBox->setObjectName(QStringLiteral("groupBox"));
        groupBox->setGeometry(QRect(10, 10, 391, 251));
        verticalLayoutWidget = new QWidget(groupBox);
        verticalLayoutWidget->setObjectName(QStringLiteral("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(10, 40, 371, 201));
        verticalLayout = new QVBoxLayout(verticalLayoutWidget);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        nom = new QLabel(verticalLayoutWidget);
        nom->setObjectName(QStringLiteral("nom"));

        verticalLayout->addWidget(nom);

        score = new QLabel(verticalLayoutWidget);
        score->setObjectName(QStringLiteral("score"));

        verticalLayout->addWidget(score);

        niv_max = new QLabel(verticalLayoutWidget);
        niv_max->setObjectName(QStringLiteral("niv_max"));

        verticalLayout->addWidget(niv_max);

        classement = new QLabel(verticalLayoutWidget);
        classement->setObjectName(QStringLiteral("classement"));

        verticalLayout->addWidget(classement);

        gomme_av = new QLabel(verticalLayoutWidget);
        gomme_av->setObjectName(QStringLiteral("gomme_av"));

        verticalLayout->addWidget(gomme_av);

        fantom_abattus = new QLabel(verticalLayoutWidget);
        fantom_abattus->setObjectName(QStringLiteral("fantom_abattus"));

        verticalLayout->addWidget(fantom_abattus);

        groupBox_2 = new QGroupBox(centralwidget);
        groupBox_2->setObjectName(QStringLiteral("groupBox_2"));
        groupBox_2->setGeometry(QRect(419, 9, 361, 541));
        listWidget = new QListWidget(groupBox_2);
        listWidget->setObjectName(QStringLiteral("listWidget"));
        listWidget->setGeometry(QRect(20, 40, 321, 481));
        pushButton = new QPushButton(centralwidget);
        pushButton->setObjectName(QStringLiteral("pushButton"));
        pushButton->setGeometry(QRect(80, 460, 261, 61));
        pushButton_2 = new QPushButton(centralwidget);
        pushButton_2->setObjectName(QStringLiteral("pushButton_2"));
        pushButton_2->setGeometry(QRect(80, 320, 261, 61));
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
        groupBox->setTitle(QApplication::translate("VueProfil", "Profil", Q_NULLPTR));
        nom->setText(QApplication::translate("VueProfil", "nom : ", Q_NULLPTR));
        score->setText(QApplication::translate("VueProfil", "score : ", Q_NULLPTR));
        niv_max->setText(QApplication::translate("VueProfil", "Niveau max : ", Q_NULLPTR));
        classement->setText(QApplication::translate("VueProfil", "classement : ", Q_NULLPTR));
        gomme_av->setText(QApplication::translate("VueProfil", "nombre de gommes aval\303\251es : ", Q_NULLPTR));
        fantom_abattus->setText(QApplication::translate("VueProfil", "nombre de fant\303\264mes abattus : ", Q_NULLPTR));
        groupBox_2->setTitle(QApplication::translate("VueProfil", "Rejouer un niveau", Q_NULLPTR));
        pushButton->setText(QApplication::translate("VueProfil", "Continuer !", Q_NULLPTR));
        pushButton_2->setText(QApplication::translate("VueProfil", "Changer de profil", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class VueProfil: public Ui_VueProfil {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_VUEPROFIL_H

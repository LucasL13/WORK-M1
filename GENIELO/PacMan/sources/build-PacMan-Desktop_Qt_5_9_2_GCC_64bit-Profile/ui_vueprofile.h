/********************************************************************************
** Form generated from reading UI file 'vueprofile.ui'
**
** Created by: Qt User Interface Compiler version 5.9.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_VUEPROFILE_H
#define UI_VUEPROFILE_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QGroupBox>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QScrollArea>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_VueProfile
{
public:
    QPushButton *pushButton;
    QGroupBox *groupBox;
    QWidget *verticalLayoutWidget;
    QVBoxLayout *verticalLayout;
    QLabel *nom;
    QLabel *score;
    QLabel *niv_max;
    QLabel *classement;
    QLabel *fantome;
    QLabel *gomme;
    QGroupBox *groupBox_2;
    QScrollArea *scrollArea;
    QWidget *scrollAreaWidgetContents;
    QPushButton *pushButton_2;

    void setupUi(QWidget *VueProfile)
    {
        if (VueProfile->objectName().isEmpty())
            VueProfile->setObjectName(QStringLiteral("VueProfile"));
        VueProfile->setEnabled(true);
        VueProfile->resize(800, 800);
        VueProfile->setMinimumSize(QSize(800, 800));
        VueProfile->setMaximumSize(QSize(800, 800));
        pushButton = new QPushButton(VueProfile);
        pushButton->setObjectName(QStringLiteral("pushButton"));
        pushButton->setGeometry(QRect(70, 510, 221, 61));
        groupBox = new QGroupBox(VueProfile);
        groupBox->setObjectName(QStringLiteral("groupBox"));
        groupBox->setGeometry(QRect(20, 20, 331, 421));
        verticalLayoutWidget = new QWidget(groupBox);
        verticalLayoutWidget->setObjectName(QStringLiteral("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(19, 39, 291, 271));
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

        fantome = new QLabel(verticalLayoutWidget);
        fantome->setObjectName(QStringLiteral("fantome"));

        verticalLayout->addWidget(fantome);

        gomme = new QLabel(verticalLayoutWidget);
        gomme->setObjectName(QStringLiteral("gomme"));

        verticalLayout->addWidget(gomme);

        groupBox_2 = new QGroupBox(VueProfile);
        groupBox_2->setObjectName(QStringLiteral("groupBox_2"));
        groupBox_2->setGeometry(QRect(370, 20, 391, 701));
        scrollArea = new QScrollArea(groupBox_2);
        scrollArea->setObjectName(QStringLiteral("scrollArea"));
        scrollArea->setGeometry(QRect(20, 39, 351, 641));
        scrollArea->setWidgetResizable(true);
        scrollAreaWidgetContents = new QWidget();
        scrollAreaWidgetContents->setObjectName(QStringLiteral("scrollAreaWidgetContents"));
        scrollAreaWidgetContents->setGeometry(QRect(0, 0, 349, 639));
        scrollArea->setWidget(scrollAreaWidgetContents);
        pushButton_2 = new QPushButton(VueProfile);
        pushButton_2->setObjectName(QStringLiteral("pushButton_2"));
        pushButton_2->setGeometry(QRect(70, 630, 221, 61));

        retranslateUi(VueProfile);

        QMetaObject::connectSlotsByName(VueProfile);
    } // setupUi

    void retranslateUi(QWidget *VueProfile)
    {
        VueProfile->setWindowTitle(QApplication::translate("VueProfile", "Form", Q_NULLPTR));
        pushButton->setText(QApplication::translate("VueProfile", "Choisir un autre profil", Q_NULLPTR));
        groupBox->setTitle(QApplication::translate("VueProfile", "Mon profil", Q_NULLPTR));
        nom->setText(QApplication::translate("VueProfile", "Nom : ", Q_NULLPTR));
        score->setText(QApplication::translate("VueProfile", "Score : ", Q_NULLPTR));
        niv_max->setText(QApplication::translate("VueProfile", "Niv max : ", Q_NULLPTR));
        classement->setText(QApplication::translate("VueProfile", "Classement : ", Q_NULLPTR));
        fantome->setText(QApplication::translate("VueProfile", "Nombre de fant\303\264me abatus : ", Q_NULLPTR));
        gomme->setText(QApplication::translate("VueProfile", "Nombre de gommes aval\303\251es : ", Q_NULLPTR));
        groupBox_2->setTitle(QApplication::translate("VueProfile", "GroupBox", Q_NULLPTR));
        pushButton_2->setText(QApplication::translate("VueProfile", "Continuer", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class VueProfile: public Ui_VueProfile {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_VUEPROFILE_H

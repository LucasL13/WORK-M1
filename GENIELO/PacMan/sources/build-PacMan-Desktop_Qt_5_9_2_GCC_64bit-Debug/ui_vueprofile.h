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
    QLabel *label;
    QGroupBox *groupBox;
    QWidget *verticalLayoutWidget;
    QVBoxLayout *verticalLayout;
    QLabel *nom;
    QLabel *score;
    QLabel *niv_max;
    QLabel *classement;
    QLabel *fantome;
    QLabel *gomme;
    QPushButton *pushButton;
    QPushButton *pushButton_2;
    QGroupBox *groupBox_2;
    QScrollArea *scrollArea;
    QWidget *scrollAreaWidgetContents;
    QWidget *verticalLayoutWidget_2;
    QVBoxLayout *verticalLayout_2;

    void setupUi(QWidget *VueProfile)
    {
        if (VueProfile->objectName().isEmpty())
            VueProfile->setObjectName(QStringLiteral("VueProfile"));
        VueProfile->setEnabled(true);
        VueProfile->resize(1280, 800);
        VueProfile->setMinimumSize(QSize(1280, 800));
        VueProfile->setMaximumSize(QSize(1280, 800));
        label = new QLabel(VueProfile);
        label->setObjectName(QStringLiteral("label"));
        label->setGeometry(QRect(0, 0, 1281, 801));
        QFont font;
        font.setBold(true);
        font.setItalic(false);
        font.setWeight(75);
        label->setFont(font);
        label->setPixmap(QPixmap(QString::fromUtf8("graphics_pacman/Source_bg.jpg")));
        label->setScaledContents(true);
        groupBox = new QGroupBox(VueProfile);
        groupBox->setObjectName(QStringLiteral("groupBox"));
        groupBox->setGeometry(QRect(50, 80, 1181, 641));
        QSizePolicy sizePolicy(QSizePolicy::Preferred, QSizePolicy::Minimum);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(groupBox->sizePolicy().hasHeightForWidth());
        groupBox->setSizePolicy(sizePolicy);
        groupBox->setAutoFillBackground(false);
        groupBox->setStyleSheet(QStringLiteral("background-color: white; border-radius: 15px;"));
        verticalLayoutWidget = new QWidget(groupBox);
        verticalLayoutWidget->setObjectName(QStringLiteral("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(140, 70, 291, 271));
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

        pushButton = new QPushButton(groupBox);
        pushButton->setObjectName(QStringLiteral("pushButton"));
        pushButton->setGeometry(QRect(130, 380, 300, 61));
        pushButton->setMinimumSize(QSize(300, 0));
        QFont font1;
        font1.setFamily(QStringLiteral("URW Gothic L"));
        font1.setPointSize(13);
        font1.setBold(true);
        font1.setWeight(75);
        pushButton->setFont(font1);
        pushButton->setAutoFillBackground(false);
        pushButton->setStyleSheet(QLatin1String("background-color : rgb(237, 212, 0);\n"
"color: black;\n"
"border: 3px solid gray;\n"
"border-radius: 15px;"));
        pushButton_2 = new QPushButton(groupBox);
        pushButton_2->setObjectName(QStringLiteral("pushButton_2"));
        pushButton_2->setGeometry(QRect(130, 470, 300, 61));
        pushButton_2->setMinimumSize(QSize(300, 0));
        QFont font2;
        font2.setFamily(QStringLiteral("URW Gothic L"));
        font2.setPointSize(13);
        font2.setBold(false);
        font2.setItalic(false);
        font2.setUnderline(false);
        font2.setWeight(50);
        font2.setStrikeOut(false);
        pushButton_2->setFont(font2);
        pushButton_2->setAutoFillBackground(false);
        pushButton_2->setStyleSheet(QLatin1String("background-color: black; \n"
"color: white; \n"
"border: 3px solid gray;"));
        groupBox_2 = new QGroupBox(groupBox);
        groupBox_2->setObjectName(QStringLiteral("groupBox_2"));
        groupBox_2->setGeometry(QRect(600, 40, 441, 521));
        groupBox_2->setStyleSheet(QStringLiteral("border: 1px solid black; border-radius: 10px;"));
        scrollArea = new QScrollArea(groupBox_2);
        scrollArea->setObjectName(QStringLiteral("scrollArea"));
        scrollArea->setGeometry(QRect(0, 30, 439, 479));
        scrollArea->setWidgetResizable(true);
        scrollAreaWidgetContents = new QWidget();
        scrollAreaWidgetContents->setObjectName(QStringLiteral("scrollAreaWidgetContents"));
        scrollAreaWidgetContents->setGeometry(QRect(0, 0, 437, 477));
        verticalLayoutWidget_2 = new QWidget(scrollAreaWidgetContents);
        verticalLayoutWidget_2->setObjectName(QStringLiteral("verticalLayoutWidget_2"));
        verticalLayoutWidget_2->setGeometry(QRect(140, 240, 251, 211));
        verticalLayout_2 = new QVBoxLayout(verticalLayoutWidget_2);
        verticalLayout_2->setObjectName(QStringLiteral("verticalLayout_2"));
        verticalLayout_2->setContentsMargins(0, 0, 0, 0);
        scrollArea->setWidget(scrollAreaWidgetContents);

        retranslateUi(VueProfile);
        QObject::connect(pushButton, SIGNAL(clicked()), VueProfile, SLOT(changeProfil()));

        QMetaObject::connectSlotsByName(VueProfile);
    } // setupUi

    void retranslateUi(QWidget *VueProfile)
    {
        VueProfile->setWindowTitle(QApplication::translate("VueProfile", "Form", Q_NULLPTR));
        label->setText(QString());
        groupBox->setTitle(QString());
        nom->setText(QApplication::translate("VueProfile", "Nom : ", Q_NULLPTR));
        score->setText(QApplication::translate("VueProfile", "Score : ", Q_NULLPTR));
        niv_max->setText(QApplication::translate("VueProfile", "Niv max : ", Q_NULLPTR));
        classement->setText(QApplication::translate("VueProfile", "Classement : ", Q_NULLPTR));
        fantome->setText(QApplication::translate("VueProfile", "Nombre de fant\303\264me abatus : ", Q_NULLPTR));
        gomme->setText(QApplication::translate("VueProfile", "Nombre de gommes aval\303\251es : ", Q_NULLPTR));
#ifndef QT_NO_STATUSTIP
        pushButton->setStatusTip(QString());
#endif // QT_NO_STATUSTIP
        pushButton->setText(QApplication::translate("VueProfile", "CHOISIR UN AUTRE PROFIL", Q_NULLPTR));
        pushButton_2->setText(QApplication::translate("VueProfile", "JOUER A CE NIVEAU", Q_NULLPTR));
        groupBox_2->setTitle(QApplication::translate("VueProfile", "Choisir le niveau de jeu", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class VueProfile: public Ui_VueProfile {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_VUEPROFILE_H

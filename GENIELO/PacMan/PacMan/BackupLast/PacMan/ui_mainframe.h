/********************************************************************************
** Form generated from reading UI file 'mainframe.ui'
**
** Created by: Qt User Interface Compiler version 5.9.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINFRAME_H
#define UI_MAINFRAME_H

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
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainFrame
{
public:
    QWidget *centralwidget;
    QWidget *verticalLayoutWidget;
    QVBoxLayout *verticalLayout;
    QVBoxLayout *verticalLayout_2;
    QLabel *label;
    QPushButton *Jouer;
    QMenuBar *menubar;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *MainFrame)
    {
        if (MainFrame->objectName().isEmpty())
            MainFrame->setObjectName(QStringLiteral("MainFrame"));
        MainFrame->resize(1280, 960);
        QSizePolicy sizePolicy(QSizePolicy::Maximum, QSizePolicy::Maximum);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(MainFrame->sizePolicy().hasHeightForWidth());
        MainFrame->setSizePolicy(sizePolicy);
        MainFrame->setMinimumSize(QSize(1280, 960));
        MainFrame->setMaximumSize(QSize(1280, 960));
        MainFrame->setAutoFillBackground(true);
        MainFrame->setStyleSheet(QStringLiteral("background-color: rgb(85, 87, 83);"));
        centralwidget = new QWidget(MainFrame);
        centralwidget->setObjectName(QStringLiteral("centralwidget"));
        sizePolicy.setHeightForWidth(centralwidget->sizePolicy().hasHeightForWidth());
        centralwidget->setSizePolicy(sizePolicy);
        centralwidget->setMinimumSize(QSize(1280, 960));
        centralwidget->setMaximumSize(QSize(1280, 960));
        centralwidget->setAutoFillBackground(true);
        centralwidget->setStyleSheet(QStringLiteral("background-color: rgb(85, 87, 83);"));
        verticalLayoutWidget = new QWidget(centralwidget);
        verticalLayoutWidget->setObjectName(QStringLiteral("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(0, -20, 1281, 751));
        verticalLayout = new QVBoxLayout(verticalLayoutWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        verticalLayout->setSizeConstraint(QLayout::SetMaximumSize);
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setSpacing(6);
        verticalLayout_2->setObjectName(QStringLiteral("verticalLayout_2"));
        verticalLayout_2->setSizeConstraint(QLayout::SetFixedSize);
        label = new QLabel(verticalLayoutWidget);
        label->setObjectName(QStringLiteral("label"));
        QFont font;
        font.setStyleStrategy(QFont::PreferAntialias);
        label->setFont(font);
        label->setAutoFillBackground(false);
        label->setPixmap(QPixmap(QString::fromUtf8("graphics_pacman/logo_small.png")));
        label->setScaledContents(true);

        verticalLayout_2->addWidget(label, 0, Qt::AlignHCenter|Qt::AlignVCenter);

        Jouer = new QPushButton(verticalLayoutWidget);
        Jouer->setObjectName(QStringLiteral("Jouer"));
        QSizePolicy sizePolicy1(QSizePolicy::Fixed, QSizePolicy::Fixed);
        sizePolicy1.setHorizontalStretch(0);
        sizePolicy1.setVerticalStretch(0);
        sizePolicy1.setHeightForWidth(Jouer->sizePolicy().hasHeightForWidth());
        Jouer->setSizePolicy(sizePolicy1);
        Jouer->setBaseSize(QSize(0, 0));
        Jouer->setStyleSheet(QLatin1String("background-color: rgb(32, 74, 135);\n"
"color: rgb(255, 255, 255);\n"
"padding: 20px;\n"
"padding-right: 50px;\n"
"padding-left: 50px;\n"
"margin-bottom: 20px;"));

        verticalLayout_2->addWidget(Jouer, 0, Qt::AlignHCenter);


        verticalLayout->addLayout(verticalLayout_2);

        MainFrame->setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainFrame);
        menubar->setObjectName(QStringLiteral("menubar"));
        menubar->setGeometry(QRect(0, 0, 1280, 19));
        MainFrame->setMenuBar(menubar);
        statusbar = new QStatusBar(MainFrame);
        statusbar->setObjectName(QStringLiteral("statusbar"));
        MainFrame->setStatusBar(statusbar);

        retranslateUi(MainFrame);
        QObject::connect(Jouer, SIGNAL(clicked()), MainFrame, SLOT(goMenu()));

        QMetaObject::connectSlotsByName(MainFrame);
    } // setupUi

    void retranslateUi(QMainWindow *MainFrame)
    {
        MainFrame->setWindowTitle(QApplication::translate("MainFrame", "Pacman", Q_NULLPTR));
        label->setText(QString());
        Jouer->setText(QApplication::translate("MainFrame", "JOUER", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class MainFrame: public Ui_MainFrame {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINFRAME_H

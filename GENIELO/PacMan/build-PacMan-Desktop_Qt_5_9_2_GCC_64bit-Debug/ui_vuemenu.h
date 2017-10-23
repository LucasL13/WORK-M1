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
#include <QtWidgets/QDockWidget>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLCDNumber>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_VueMenu
{
public:
    QWidget *dockWidgetContents;
    QWidget *verticalLayoutWidget;
    QVBoxLayout *verticalLayout;
    QLCDNumber *lcdNumber;

    void setupUi(QDockWidget *VueMenu)
    {
        if (VueMenu->objectName().isEmpty())
            VueMenu->setObjectName(QStringLiteral("VueMenu"));
        VueMenu->resize(645, 538);
        dockWidgetContents = new QWidget();
        dockWidgetContents->setObjectName(QStringLiteral("dockWidgetContents"));
        verticalLayoutWidget = new QWidget(dockWidgetContents);
        verticalLayoutWidget->setObjectName(QStringLiteral("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(100, 90, 441, 321));
        verticalLayout = new QVBoxLayout(verticalLayoutWidget);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        lcdNumber = new QLCDNumber(verticalLayoutWidget);
        lcdNumber->setObjectName(QStringLiteral("lcdNumber"));
        lcdNumber->setProperty("value", QVariant(50.42));

        verticalLayout->addWidget(lcdNumber);

        VueMenu->setWidget(dockWidgetContents);

        retranslateUi(VueMenu);

        QMetaObject::connectSlotsByName(VueMenu);
    } // setupUi

    void retranslateUi(QDockWidget *VueMenu)
    {
        VueMenu->setWindowTitle(QApplication::translate("VueMenu", "DockWidget", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class VueMenu: public Ui_VueMenu {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_VUEMENU_H

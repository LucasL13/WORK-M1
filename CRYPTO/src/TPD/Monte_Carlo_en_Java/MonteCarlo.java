package TPD.Monte_Carlo_en_Java;// -*- coding: utf-8 -*-

class MonteCarlo {
    public static void main(String[] args) {
	double x, y;
	int tiragesDansLeDisque = 0, nombreDeTirages = (int) Math.pow(10,6);
	for (int i = 0; i < nombreDeTirages; i++) {
	    x = Math.random();
	    y = Math.random();
	    if (x * x + y * y <= 1) tiragesDansLeDisque++;
	}
	double estimation = 4.0 * tiragesDansLeDisque / nombreDeTirages;
	System.out.println("Estimation de Pi: " + estimation);
	System.out.println("Valeur exacte de Pi: " + Math.PI);
	System.out.println("Taux d'erreur :" + 100*Math.abs( estimation - Math.PI)/Math.PI + " %");
    }
}

/*
$ make
javac *.java 
$ java MonteCarlo
Estimation de Pi: 3.143904
Valeur exacte de Pi: 3.141592653589793
Taux d'erreur :0.07357244127642766 %
$ java MonteCarlo
Estimation de Pi: 3.1452
Valeur exacte de Pi: 3.141592653589793
Taux d'erreur :0.11482540252584578 %
$
*/

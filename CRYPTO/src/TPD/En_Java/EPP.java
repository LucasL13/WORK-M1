package TPD.En_Java;

// -*- coding: utf-8 -*-

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;


public class EPP
{

    static boolean est_probablement_premier(BigInteger n)
    {
        return n.isProbablePrime(100);
    }

    static boolean est_temoin(BigInteger a, BigInteger n)
    {
        // A compléter
        return true;
    }

    static BigInteger fabriquerGrandNombre(int nb_bits){

        SecureRandom sr = new SecureRandom();
        byte bytes[] = new byte[(nb_bits / 8)];
        sr.nextBytes(bytes);

        BigInteger b = new BigInteger(bytes);
        b = b.abs();
        b = b.setBit(0);

        //System.out.println("Fabrique : " + b);
        return b;
    }

    static BigInteger trouverGrandNombrePremier(int nb_bits){
        int nb_tentatives = 0;
        long temps_recherche = 0;

        BigInteger b;
//        long start = System.currentTimeMillis();

        do{
            b = fabriquerGrandNombre(nb_bits);
            nb_tentatives++;
        }while(!est_probablement_premier(b));

//        temps_recherche = System.currentTimeMillis() - start;

//        System.out.println("Grand nombre premier : " + b);
//        System.out.println("\nTrouvé au bout de " + nb_tentatives + " tentatives ("+temps_recherche+"ms)");

        return b;
    }


    static BigInteger trouverGNP_germain(int nb_bits){
        int nb_tentatives = 0;
        long temps_recherche = 0;
        Boolean trouve = false;

        BigInteger b, b2;
        long start = System.currentTimeMillis();

        do{
            b = trouverGrandNombrePremier(nb_bits);
            b2 = b.multiply(new BigInteger("2"));
            b2 = b2.add(new BigInteger("1"));
            if(est_probablement_premier(b2))
                trouve = true;
            nb_tentatives++;
        }while(!trouve);
        temps_recherche = System.currentTimeMillis() - start;

        System.out.println("Grand nombre premier de germain : " + b);
        System.out.println("\nTrouvé au bout de " + nb_tentatives + " tentatives ("+temps_recherche+"ms)");

        return b;
    }

    static void estimation_MonteCarlo(){
        int nb_nbP = 0;
        BigInteger b;
        long temps_recherche = System.currentTimeMillis();

        for(int i=0; i < 100000; i++){
            if(est_probablement_premier(fabriquerGrandNombre(1024))) {
                //System.out.println("trouvé");
                nb_nbP++;
            }
        }

        temps_recherche = System.currentTimeMillis() - temps_recherche;
        System.out.println("Proportion de nb premiers de 1024bits : " + nb_nbP + "/100000\nTrouvé en " + temps_recherche + "ms");
    }

    public static void main(String[] args)
    {
        //trouverGNP_germain(512);

        estimation_MonteCarlo();
    }

}

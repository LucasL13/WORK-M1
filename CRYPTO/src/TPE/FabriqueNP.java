package TPE;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by work on 05/11/17.
 */
public class FabriqueNP {


    public static boolean est_probablement_premier(BigInteger n)
    {
        return n.isProbablePrime(100);
    }

    public static BigInteger fabriquerGrandNombre(int nb_bits){

        SecureRandom sr = new SecureRandom();
        byte bytes[] = new byte[(nb_bits / 8)];
        sr.nextBytes(bytes);

        BigInteger b = new BigInteger(bytes);
        b = b.abs();
        b = b.setBit(0);

        //System.out.println("Fabrique : " + b);
        return b;
    }

    public static BigInteger trouverGrandNombrePremier(int nb_bits){
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

}

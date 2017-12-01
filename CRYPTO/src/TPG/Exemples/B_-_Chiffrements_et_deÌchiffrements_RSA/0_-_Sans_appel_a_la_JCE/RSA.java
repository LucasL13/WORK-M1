// -*- coding: utf-8 -*-

import java.*;
import java.math.BigInteger;

public class RSA {
    public static void main(String[] args) throws Exception {
	BigInteger bigN = new BigInteger("196520034100071057065009920573", 10);
	System.out.println("N: " + bigN);
	BigInteger bigE = BigInteger.valueOf(7);
	System.out.println("E: " + bigE);
	BigInteger bigD = new BigInteger("56148581171448620129544540223", 10);
	System.out.println("D: " + bigD);
	BigInteger bigM = new BigInteger("3463326578241", 10);
	System.out.println("M: " + bigM);
	BigInteger message_chiffre = bigM.modPow(bigE, bigN);
	System.out.println("M^E mod N: " + message_chiffre);
	BigInteger message_dechiffre = message_chiffre.modPow(bigD, bigN);
	System.out.println("(M^E)^D mod N: " + message_dechiffre);
    }
}

/*

> javac RSA.java
> java RSA
N: 196520034100071057065009920573
E: 7
D: 56148581171448620129544540223
M: 3463326578241
M^E mod N: 10132267902671105277276209681
(M^E)^D mod N: 3463326578241

*/

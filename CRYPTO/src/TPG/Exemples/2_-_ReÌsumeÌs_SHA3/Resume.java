// -*- coding: utf-8 -*-

import java.math.*;
import java.io.*;
import java.security.*;

import org.bouncycastle.jcajce.provider.digest.*;

public class Resume {
    public static void main(String[] args) {
	byte[] buffer, resume;
	SHA3.DigestSHA3 fonction_de_hachage;
	try {
	    // On ouvre le fichier
	    File fichier = new File("butokuden.jpg");
	    FileInputStream fis = new FileInputStream(fichier);
	    fonction_de_hachage = new SHA3.DigestSHA3(256);   // On charge SHA3
	    buffer = new byte[1024];
	    int nbOctetsLus = fis.read(buffer);               // Lecture du premier morceau
	    while (nbOctetsLus != -1) {
		fonction_de_hachage.update(buffer, 0, nbOctetsLus); // Digestion du morceau
		nbOctetsLus = fis.read(buffer);               // Lecture du morceau suivant
	    }
	    fis.close();
	    resume = fonction_de_hachage.digest();
	    System.out.print("Le résumé SHA3-256 du fichier \"butokuden.jpg\" vaut: 0x");
	    System.out.println(String.format("%02X", new BigInteger(1, resume)));
	} catch (Exception e) { e.printStackTrace(); }
    }
}

/* 
> javac -cp ./:./bcprov-jdk15on-153.jar Resume.java
> java -cp ./:./bcprov-jdk15on-153.jar Resume
Le résumé SHA3-256 du fichier "butokuden.jpg" vaut: 0x973bc78fee694c0ff00bf10a00330e873134ba685a308169b7d1d5cb63bbd6b7
>
*/


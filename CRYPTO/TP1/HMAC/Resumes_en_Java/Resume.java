// -*- coding: utf-8 -*-

import java.io.*;
import java.security.*;

public class Resume {
    public static void main(String[] args) {
	byte[] buffer, resume;
	MessageDigest fonction_de_hachage;
	try {
	    // On ouvre le fichier
	    File fichier = new File("butokuden.jpg");
	    FileInputStream fis = new FileInputStream(fichier);
	    fonction_de_hachage = MessageDigest.getInstance("MD5");
	    buffer = new byte[1024];
	    int nbOctetsLus = fis.read(buffer);               // Lecture du premier morceau
	    while (nbOctetsLus != -1) {
		fonction_de_hachage.update(buffer, 0, nbOctetsLus); // Digestion du morceau
		nbOctetsLus = fis.read(buffer);               // Lecture du morceau suivant
	    }
	    fis.close();
	    resume = fonction_de_hachage.digest();
	    System.out.print("Le résumé MD5 du fichier \"butokuden.jpg\" vaut: 0x");
	    for(byte octet: resume)
		System.out.print(String.format("%02X", octet));
	    // On affiche le résumé en hexadécimal
	    System.out.println();
	} catch (Exception e) { e.printStackTrace(); }
    }
}

/* 
>
$ javac Resume.java
> java Resume
Le résumé MD5 du fichier "butokuden.jpg" vaut: 0xAEEF572459C1BEC5F94B8D62D5D134B5
> cat butokuden.jpg | md5
aeef572459c1bec5f94b8d62d5d134b5
>
*/


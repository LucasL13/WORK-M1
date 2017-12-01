// -*- coding: utf-8 -*-

import java.io.*;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class AES_fichier {
    private static final byte[] AESKey = {
	(byte) 0x2b,  (byte) 0x7e,  (byte) 0x15,  (byte) 0x16,  (byte) 0x28, 
	(byte) 0xae,  (byte) 0xd2,  (byte) 0xa6,  (byte) 0xab,  (byte) 0xf7, 
	(byte) 0x15,  (byte) 0x88,  (byte) 0x09,  (byte) 0xcf,  (byte) 0x4f,
	(byte) 0x3c };	    
    private static Cipher chiffrement;
    private static SecretKeySpec clefSecrete;

    private static final int BUFFERSIZE = 1024;
    private static byte[] buffer = new byte[BUFFERSIZE];
    private static InputStream in;
    private static OutputStream out;
    private static CipherInputStream cis;
    
    public static void main(String[] args) {
	try {
	    in = new FileInputStream(args[0]); 
	    out = new FileOutputStream(args[1]);
	}
	catch (Exception e) { System.out.println("Fichier inexistant.");}	
	System.out.println("Clef utilisée: 0x" + toHex(AESKey));
        //------------------------------------------------------------------
        //  Etape 1.   Récupérer un objet qui chiffre ou déchiffre en AES
	//             dans le mode CBC (non-déterministe) avec bourrage standard.
        //------------------------------------------------------------------
        try {
	    chiffrement = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
	}
	catch (Exception e) { System.out.println("AES n'est pas disponible.");}	
        //------------------------------------------------------------------
        //  Etape 2.   Fabriquer la clé AES de 128 bits correspondante et
	//             préparer le vecteur d'initialisation.
        //------------------------------------------------------------------
	clefSecrete = new SecretKeySpec(AESKey, "AES");
	byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	IvParameterSpec ivspec = new IvParameterSpec(iv);
	//------------------------------------------------------------------
        //  Etape 3.   Chiffrer le fichier et sauvegarder le résultat
        //------------------------------------------------------------------
        try {
	    chiffrement.init(Cipher.ENCRYPT_MODE, clefSecrete, ivspec);
	    cis = new CipherInputStream(in, chiffrement);
	    int len = cis.read(buffer, 0, BUFFERSIZE);
	    while ( len != -1) {
		out.write(buffer, 0, len);
		len = cis.read(buffer, 0, BUFFERSIZE);
	    }
	    out.close();
	    cis.close();
	    in.close();
	} catch (Exception e) { System.out.println("Chiffrement impossible:" + e.getMessage());}	
	//------------------------------------------------------------------
        //  Etape 4.   Déchiffrer en guise de test
        //------------------------------------------------------------------
	try{
	    in = new FileInputStream(args[1]); 
	    out = new FileOutputStream(args[2]);
	}
	catch (Exception e) { System.out.println("Fichier inexistant:"+ e.getMessage());}	
        try {
	    chiffrement.init(Cipher.DECRYPT_MODE, clefSecrete, ivspec);
	    cis = new CipherInputStream(in, chiffrement);
	    int len = cis.read(buffer, 0, BUFFERSIZE);
	    while ( len != -1) {
		out.write(buffer, 0, len);
		len = cis.read(buffer, 0, BUFFERSIZE);
	    }
	    out.close();
	    cis.close();
	    in.close();
	} catch (Exception e) { System.out.println("Déchiffrement impossible:"+ e.getMessage());}	
    }

    public static String toHex(byte[] donnees) {
	return javax.xml.bind.DatatypeConverter.printHexBinary(donnees);
    }    
}

/* 
> ls
AES_fichier.java	Makefile		butokuden.jpg
> make
javac *.java 
> java AES_fichier butokuden.jpg butokuden-chiffre.jpg butokuden-dechiffre.jpg 
Clef utilisée: 0x2B7E151628AED2A6ABF7158809CF4F3C
> java AES_fichier butokuden.jpg butokuden-chiffre_-_2.jpg butokuden-dechiffre_-_2.jpg 
Clef utilisée: 0x2B7E151628AED2A6ABF7158809CF4F3C
> ls -al
total 4664
-rw-r--r--   1 remi  staff    2893 23 oct 07:43 AES_fichier.class
-rwxr--r--   1 remi  staff    4295 23 oct 07:42 AES_fichier.java
-rwxr--r--   1 remi  staff      42 18 mar  2013 Makefile
-rw-r--r--   1 remi  staff  467808 23 oct 07:48 butokuden-chiffre.jpg
-rw-r--r--   1 remi  staff  467808 23 oct 07:49 butokuden-chiffre_-_2.jpg
-rw-r--r--   1 remi  staff  467796 23 oct 07:48 butokuden-dechiffre.jpg
-rw-r--r--   1 remi  staff  467796 23 oct 07:49 butokuden-dechiffre_-_2.jpg
-rw-r--r--@  1 remi  staff  467796 21 jan  2015 butokuden.jpg
> diff butokuden.jpg butokuden-dechiffre.jpg 
> diff butokuden-chiffre.jpg butokuden-chiffre_-_2.jpg 
>
*/

/*
  Notez que butokuden-chiffre.jpg fait 12 octets de plus, à cause du
  bourrage et que sa taille est bien un multiple de 16.

  D'autre part, butokuden-chiffre.jpg et butokuden-chiffre_-_2.jpg
  sont identiques: le chiffrement dépend uniquement du vecteur
  d'initialisation iv (qui n'est pas joint au fichier chiffré...)
*/


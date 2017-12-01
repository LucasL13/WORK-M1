// -*- coding: utf-8 -*-

import java.io.*;
import java.util.Enumeration;

import java.security.cert.Certificate;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStore;

public class RSA_JKS {
    // Pour manipuler le trousseau
    private static KeyStore magasin;
    private static final String nomDuTrousseau = "MonTrousseau.jks";
    private static final char[] motDePasse = "Alain Turin".toCharArray();
    private static KeyStore.ProtectionParameter protection;
    private static String alias;
    
    private static Cipher chiffrementRSA = null;

    public static void main(String[] args) {
	// Le chiffrement prend des octets et renvoie des octets
	System.out.println("Message clair: \"Alfred\"");
	byte[] messageClair = "Alfred".getBytes();
	byte[] messageChiffre = null;
	byte[] messageDechiffre = null;
	PublicKey clefPublique = null ; 
	PrivateKey clefPrivee = null; 

        //------------------------------------------------------------------
        //  Etape 1.   Charger le trousseau dans le magasin
        //------------------------------------------------------------------
	FileInputStream fis = null;
	try {
	    magasin = KeyStore.getInstance("JKS");
	    fis = new FileInputStream(nomDuTrousseau);
	    magasin.load(fis, motDePasse);
	}
	catch (Exception e){
	    System.out.println("Échec à l'ouverture du trousseau: " + e);
	}
	finally {
	    if (fis != null) try{ fis.close(); } catch (Exception e){}
	}

	//------------------------------------------------------------------
	//  Etape 2.   Pour chiffrer, il faut la clef publique
	//------------------------------------------------------------------
	try {
	    alias = "Certificat de clef publique RSA n°11";
	    Certificate certificat = magasin.getCertificate(alias);
	    if (certificat != null) {
		clefPublique = certificat.getPublicKey () ; 
		System.out.println("Clef publique récupérée!");
	    }
	} catch (Exception e){
	    System.out.println("Échec à lors de la lecture du certificat: " + e);
	};
	//------------------------------------------------------------------
        //  Etape 3.  Chiffrer avec la clef publique RSA
        //------------------------------------------------------------------
	try {
	    Cipher chiffrementRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	    chiffrementRSA.init(Cipher.ENCRYPT_MODE, clefPublique);
	    messageChiffre = chiffrementRSA.doFinal(messageClair);
	    System.out.println("Message chiffré : \n" + toHex(messageChiffre));
	} catch (Exception e){
	    System.out.println("Échec à lors du chiffrement: " + e);
	};
	//------------------------------------------------------------------
        //  Etape 4.  Pour déchiffrer, il faut la clef privée RSA associée
        //------------------------------------------------------------------
	try {
	    alias = "Clef privée RSA n°11";
	    protection = new KeyStore.PasswordProtection(motDePasse);
	    PrivateKeyEntry entreePrivee = null;
	    entreePrivee = (KeyStore.PrivateKeyEntry) magasin.getEntry(alias,protection);
	    if (entreePrivee != null) {
		clefPrivee = entreePrivee.getPrivateKey(); 
		// Certificate certificat = entreePrivee.getCertificate();	    
		// clefPublique = certificat.getPublicKey(); 		    
	    }
	} catch (Exception e){
	    System.out.println("Échec à lors de la lecture de la clef privée: " + e);
	};
	//------------------------------------------------------------------
        //  Etape 5.  Déchiffrer avec la clef privée RSA
        //------------------------------------------------------------------
	try {
	    Cipher chiffrementRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	    chiffrementRSA.init(Cipher.DECRYPT_MODE, clefPrivee);
	    messageDechiffre = chiffrementRSA.doFinal(messageChiffre);
	    System.out.println("Message déchiffré: \"" + new String(messageDechiffre) + "\"");
	} catch (Exception e){
	    System.out.println("Echec à lors du déchiffrement: " + e);
	};
    }
    
    public static String toHex(byte[] donnees) {
	return "0x"+javax.xml.bind.DatatypeConverter.printHexBinary(donnees);
    }    
}

/* 
> java -version
java version "1.8.0_60"
Java(TM) SE Runtime Environment (build 1.8.0_60-b27)
Java HotSpot(TM) 64-Bit Server VM (build 25.60-b23, mixed mode)
>
> ls
Makefile		MonTrousseau.jks	RSA_JKS.java
> make
javac *.java 
> java RSA_JKS
Message clair: "Alfred"
Clef publique récupérée!
Message chiffré : 
0x07F7DF71286A286840F59E2811B4867AD4D9A51C02F88CB9E009BEBE72B500B5159C3A24267AB81C1294BD6AB613E3B67F46E8F404A19FB6FEA79EF738931266B1BF06E117F3554AAE2A38CDFF76122C5085C95C18637D549538414973FFAA919FA7675F02DD7BBD41FB8A1BEC9CBE8DC2A9C45E5D1A6FD11BFA4FA008F65845
Message déchiffré: "Alfred"
>
*/


// -*- coding: utf-8 -*-

import java.*;
import java.math.BigInteger;

import java.security.*;
import javax.crypto.*;
import java.security.spec.*;
import java.security.interfaces.*;

public class RSA {
    public static void main(String[] args) throws Exception {
	BigInteger bigN = new BigInteger(
			  "00af7958cb96d7af4c2e6448089362"+
			  "31cc56e011f340c730b582a7704e55"+
			  "9e3d797c2b697c4eec07ca5a903983"+
			  "4c0566064d11121f1586829ef6900d"+
			  "003ef414487ec492af7a12c34332e5"+
			  "20fa7a0d79bf4566266bcf77c2e007"+
			  "2a491dbafa7f93175aa9edbf3a7442"+
			  "f83a75d78da5422baa4921e2e0df1c"+
			  "50d6ab2ae44140af2b", 16);
	System.out.println("N: " + bigN);
	BigInteger bigE = BigInteger.valueOf(0x10001);
	System.out.println("E: " + bigE);
	BigInteger bigD = new BigInteger(
			  "35c854adf9eadbc0d6cb47c4d11f9c"+
			  "b1cbc2dbdd99f2337cbeb2015b1124"+
			  "f224a5294d289babfe6b483cc253fa"+
			  "de00ba57aeaec6363bc7175fed20fe"+
			  "fd4ca4565e0f185ca684bb72c12746"+
			  "96079cded2e006d577cad2458a5015"+
			  "0c18a32f343051e8023b8cedd49598"+
			  "73abef69574dc9049a18821e606b0d"+
			  "0d611894eb434a59", 16);
	System.out.println("D: " + bigD);

	System.out.println("Texte clair: \"Alfred\"");
	byte[] message = "Alfred".getBytes();
	System.out.println("Message clair (en décimal): \"" +
			    String.format("%d", new BigInteger(message)) +"\"");
	System.out.println("Message clair (en hexadécimal): \"" + toHex(message) + "\"");

        //------------------------------------------------------------------
        //  Etape 1.   Récupérer un objet qui chiffre et déchiffre en RSA
	//             sans bourrage (dangereux) ni mode opératoire (ECB)
        //------------------------------------------------------------------
	Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	// Cipher chiffrement = Cipher.getInstance("RSA/none/PKCS1Padding", "BC");
	Cipher chiffrement = Cipher.getInstance("RSA/None/OAEPWithSHA1AndMGF1Padding", "BC");

        //------------------------------------------------------------------
        //  Etape 2.   Fabriquer la paire de clefs à partir des BigInteger
        //------------------------------------------------------------------
	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(bigN,bigE);
	RSAPrivateKeySpec privKeySpec = new RSAPrivateKeySpec(bigN,bigD);
	RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);
	RSAPrivateKey privKey = (RSAPrivateKey) keyFactory.generatePrivate(privKeySpec);    
	//------------------------------------------------------------------
        //  Etape 3.   Chiffrer et afficher le message chiffré
        //------------------------------------------------------------------
	chiffrement.init(Cipher.ENCRYPT_MODE, pubKey);
	byte[] messageChiffre = chiffrement.doFinal(message);
	System.out.println("Message chiffré (en hexadécimal): \n" + toHex(messageChiffre));
	//------------------------------------------------------------------
        //  Etape 4.   Déchiffrer en guise de vérification
        //------------------------------------------------------------------
	chiffrement.init(Cipher.DECRYPT_MODE, privKey);
	byte[] messageDechiffre = chiffrement.doFinal(messageChiffre);
	System.out.println("Message déchiffré: \"" + new String(messageDechiffre) +"\"");
    }

    public static String toHex(byte[] donnees) {
	return javax.xml.bind.DatatypeConverter.printHexBinary(donnees);
    }    
}

/* Avec padding OAEP à l'aide du provider BouncyCastle: "bcprov-jdk15on-153.jar"
> ls
Makefile		RSA.java		bcprov-jdk15on-153.jar
> javac -cp ./:./bcprov-jdk15on-153.jar RSA.java
> java -cp ./:./bcprov-jdk15on-153.jar RSA
N: 123222041096106014002202761844399073589005500729095166387195646782879897276992065445888286049272774070186000866868675259231313692038646016799226599569398492696664103713610988961046073523750353821004031314323877244756314872197989230784446796982376572822441192459105787690479360367203648689412606470382164160299
E: 65537
D: 37767385438721355925084255873299726737298831090000070299519339562807398822084312962319880401479198204797689845625998747763544228939076896096038340258945436575308486726511081182432653186517212982984179675540506953085758370434690791627136177581186730290072298835087405581873373381013759930358834480546282949209
Texte clair: "Alfred"
Message clair (en décimal): "71933831046500"
Message clair (en hexadécimal): "416C66726564"
Message chiffré (en hexadécimal): 
170A5316FD4831368F3970398A4777E98DA30434C7D00C97B80B1A5AE1CCF6DF15B1272EFA28C45DDAAFAA174EEE52F15663379ADF5C9D30EF8FAAC514C546F94BF74ACE8AC1F6AD0E2D261296986D97717A970341F43F5CFB4B82EFE2179EC555652429D66C55C8F0EBE61B75C03F1EB980BBCCB3CE9AEF58AB4AD9495AD967
Message déchiffré: "Alfred"
>
*/


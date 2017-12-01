// -*- coding: utf-8 -*-

import java.*;
import java.math.BigInteger;
import java.util.Formatter;
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
	Cipher chiffrement = Cipher.getInstance("RSA/ECB/NoPadding");
        //------------------------------------------------------------------
        //  Etape 2.   Fabriquer la paire de clefs à partir des BigInteger
        //------------------------------------------------------------------
	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	RSAPublicKeySpec pubkeyspec = new RSAPublicKeySpec(bigN,bigE);
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

/* Sans padding, le chiffrement est déterministe; c'est pas bien!
> javac RSA.java 
> java RSA
N: 123222041096106014002202761844399073589005500729095166387195646782879897276992065445888286049272774070186000866868675259231313692038646016799226599569398492696664103713610988961046073523750353821004031314323877244756314872197989230784446796982376572822441192459105787690479360367203648689412606470382164160299
E: 65537
D: 37767385438721355925084255873299726737298831090000070299519339562807398822084312962319880401479198204797689845625998747763544228939076896096038340258945436575308486726511081182432653186517212982984179675540506953085758370434690791627136177581186730290072298835087405581873373381013759930358834480546282949209
Texte clair: "Alfred"
Message clair (en décimal): "71933831046500"           <- Codage connu!
Message clair (en hexadécimal): "416C66726564"
Message chiffré (en hexadécimal): 
0A70F028E29154C50956C1478F983E81F53C1627DFDD9250B772466941ADBC8737F0559AE3E6BB67C49DBCB7700305AD68F58DF529973031ED203AE27EC314D2D062E551E7FF4178E37F4C8441A3AFC141743B62004C662C34DEB4415658CC2A4C9682B30C05174B9A166683C7A30C56F28DA1FDE3CB341CDBC5286E82F86011
Message déchiffré: "Alfred"
>
*/


// -*- coding: utf-8 -*-

import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    // Je souhaite utiliser la clef nulle de 128 bits (16 octets)
    private static final byte[] AESKey = {
         (byte) 0x00,  (byte) 0x00,  (byte) 0x00,  (byte) 0x00,
         (byte) 0x00,  (byte) 0x00,  (byte) 0x00,  (byte) 0x00,
         (byte) 0x00,  (byte) 0x00,  (byte) 0x00,  (byte) 0x00,
         (byte) 0x00,  (byte) 0x00,  (byte) 0x00,  (byte) 0x00 };
    // Je souhaite chiffrer le bloc nul de 128 bits (16 octets)
    private static final byte[] texteClair = {
         (byte) 0x00,  (byte) 0x00,  (byte) 0x00,  (byte) 0x00,
         (byte) 0x00,  (byte) 0x00,  (byte) 0x00,  (byte) 0x00,
         (byte) 0x00,  (byte) 0x00,  (byte) 0x00,  (byte) 0x00,
         (byte) 0x00,  (byte) 0x00,  (byte) 0x00,  (byte) 0x00 };   
    private static byte[] texteChiffre, texteDechiffre;
    private static Cipher chiffrement;
    private static SecretKeySpec clefSecrete;

    public static void main(String[] args) {
        System.out.print("Texte clair: 0x" + toHex(texteClair));
        System.out.println(" (" + texteClair.length +" octets)");
	System.out.println("Clef utilisée: 0x" + toHex(AESKey));
        //------------------------------------------------------------------
        //  Etape 1.   Récupérer un objet qui chiffre et déchiffre en AES
	//             dans le mode ECB sans bourrage
        //------------------------------------------------------------------
        try { chiffrement = Cipher.getInstance("AES/ECB/NoPadding"); }
	catch (Exception e) { System.out.println("AES n'est pas disponible!");}	
        //------------------------------------------------------------------
        //  Etape 2.   Fabriquer la clé AES nulle de 128 bits
        //------------------------------------------------------------------
	clefSecrete = new SecretKeySpec(AESKey, "AES");
	//------------------------------------------------------------------
        //  Etape 3.   Chiffrer et afficher le bloc chiffré 
        //------------------------------------------------------------------
        try {
	    chiffrement.init(Cipher.ENCRYPT_MODE, clefSecrete);
	    texteChiffre = chiffrement.doFinal(texteClair);
	    System.out.print("Texte chiffre: 0x" + toHex(texteChiffre));
	    System.out.println(" (" + texteChiffre.length +" octets)");
	} catch (Exception e) { System.out.println("Chiffrement impossible.");}	
	//------------------------------------------------------------------
        //  Etape 4.   Déchiffrer et afficher le bloc déchiffré
        //------------------------------------------------------------------
        try {
	    chiffrement.init(Cipher.DECRYPT_MODE, clefSecrete);
	    texteDechiffre = chiffrement.doFinal(texteChiffre);
	    System.out.print("Texte déchiffre: 0x" + toHex(texteDechiffre));
	    System.out.println(" (" + texteDechiffre.length +" octets)");
	} catch (Exception e) { System.out.println("Déchiffrement impossible.");}	
    }

    public static String toHex(byte[] donnees) {
	return javax.xml.bind.DatatypeConverter.printHexBinary(donnees);
    }    
}

/* 
> make
javac *.java 
> java AES
Texte clair: 0x00000000000000000000000000000000 (16 octets)
Clef utilisée: 0x00000000000000000000000000000000
Texte chiffre: 0x66E94BD4EF8A2C3B884CFA59CA342B2E (16 octets)
Texte déchiffre: 0x00000000000000000000000000000000 (16 octets)
>
*/


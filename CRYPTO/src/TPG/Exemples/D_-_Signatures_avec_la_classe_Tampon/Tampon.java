// -*- coding: utf-8 -*-

import java.io.*;
import java.security.*;
import javax.crypto.*;
import java.security.spec.*;
import java.security.interfaces.*;


public class Tampon {

  private static void printUsage()throws Exception {
    System.out.println(
      "Usage: Tampon -signer clefprive document appendice");
    System.out.println(
      "Usage: Tampon -verifier clefpublique document appendice");
    }

  public static void main(String[] args) throws Exception {
      if (args.length != 4 ) { printUsage(); return; }
      String options = args[0];
      String clef = args[1];
      String document = args[2];
      String appendice = args[3];


      /* On se prépare à récupérer la clef (privée ou publique) */
      FileInputStream fis = new FileInputStream(clef);
      ObjectInputStream ois = new ObjectInputStream(fis);
      
      /* On initialise un objet de Signature avec la clef */
      Signature signeur = Signature.getInstance("MD5withRSA");
      if (options.equals("-signer")) {
	  PrivateKey clefPrivee = (PrivateKey) ois.readObject();
	  signeur.initSign(clefPrivee);	
      } else  if (options.equals("-verifier")) {
	  PublicKey clefPublique = (PublicKey) ois.readObject();
	  signeur.initVerify(clefPublique);
      } else { printUsage(); return; }
      ois.close();


      /* On calcule toujours le résumé du document */
      fis = new FileInputStream(document);
      byte[] buffer = new byte[8192];
      int nbOctetsLus;
      while ((nbOctetsLus = fis.read(buffer)) != -1)
	  signeur.update(buffer, 0, nbOctetsLus);
      fis.close();
      /* Le résumé obtenu est stocké par l'objet "signeur" */

      if (options.equals("-signer")) {
	  byte[] tampon = signeur.sign(); // Déchiffrement du résumé...
	  FileOutputStream fos = new FileOutputStream(appendice);
	  fos.write(tampon); // Ecriture de l'appendice d'un seul coup
	  fos.close();
      } else  if (options.equals("-verifier")) {
	  fis = new FileInputStream(appendice);
	  byte[] tampon = new byte[fis.available()];
	  fis.read(tampon); // Lecture de l'appendice d'un seul coup
	  fis.close();
	  if (signeur.verify(tampon)) // Chiffrement et comparaison
	      System.out.println("La signature est correcte.");
	  else
	      System.out.println("La signature est fausse!");
      }
  }
}

/* 

> javac Tampon.java
> java Tampon
Usage: Tampon -signer clefprive document appendice
Usage: Tampon -verifier clefpublique document appendice
> java Tampon -signer privee.cle Makefile appendice.hex
> java Tampon -verifier publique.cle Makefile appendice.hex 
La signature est correcte.
> java Tampon -verifier publique.cle Tampon.java appendice.hex 
La signature est fausse!
> 
*/


package TP2.RC4.RC4_en_Java;// -*- coding: utf-8 -*-

import java.io.*;
import java.util.concurrent.ExecutionException;

import static jdk.nashorn.internal.objects.Global.print;

public class 	MonRC4
{     
    static int[] state = new int[256];       // Ces int sont <256.
    static int i = 0, j = 0;                 // Ils représentent un octet.
    static int[] clef = {'K', 'Y', 'O', 'T', 'O'};     // La clef courte fait 5 octets
    static int lg = clef.length;
    private static int LG_FLUX = 0;
    // Ce programme ne produira que les 10 premiers octets de le clef longue.


	private static void lireFichier() {

	    try {
            FileInputStream fis = null;
            fis = new FileInputStream("butokuden.jpg");

            int nb_bytes_lus = 0;
            int b;

            do{
                b = fis.read();
                nb_bytes_lus++;
            }while( b != -1);

            LG_FLUX = nb_bytes_lus;

            System.out.println("Taille fichier : " + nb_bytes_lus);
        }
        catch(IOException e){ e.printStackTrace(); }
    }

    private static void copyAndEncrypt(String fileIN, String fileOut){
        try {
            FileInputStream fis = new FileInputStream(fileIN);
            File out = new File(fileOut);
            out.createNewFile();
            FileOutputStream fos = new FileOutputStream(out);
            int b = fis.read();
            int i = 0, j = 0, w = 0, k = 0, c=0;

            do{
                i = (i + 1) % 256;
                j = (j + state[i]) % 256;
                echange(i,j);
                w = state[(state[i] + state[j]) % 256];

                k = w ^ b;
                fos.write(k);
                c++;
                b = fis.read();
            }while( b != -1);

            System.out.println("Taille new fichier : " + c);
            System.out.println("Last octet clée longue : " + w);

            fis.close();
            fos.close();
        }
        catch(IOException e){ e.printStackTrace(); }
    }

     
    private static void echange(int k, int l)
    {	
	int temp = state[k]; 
	state[k] = state[l]; 
	state[l] = temp; 
    }

    private static void initialisation()
    {	
	for (i=0; i < 256; i++) state[i] = i;
	j = 0;
	for (int i=0; i < 256; i++) {
	    j = (j + state[i] + clef[i % lg]) % 256;
	    echange(i,j);                // Echange des octets en i et j
	}
    }

    public static void main(String[] args)
    {
        initialisation();
        lireFichier();
        copyAndEncrypt("butokuden.jpg", "confidentiel.jpg");
        initialisation();
        copyAndEncrypt("confidentiel.jpg", "confidentiel_decrypted.jpg");
    }
     
}
/*
> make
javac *.java 
$ java MonRC4
0xB2 0x39 0x63 0x05 0xF0 0x3D 0xC0 0x27 0xCC 0xC3
> 
*/

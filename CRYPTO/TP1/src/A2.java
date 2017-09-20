import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * Created by work on 17/09/17.
 */
public class A2 {


    private static void cert(String text) {
        byte[] buffer, resume;
        MessageDigest fonction_de_hachage;

        try{
            File fichier = new File(text);
            FileInputStream fis = new FileInputStream(fichier);
            fonction_de_hachage = MessageDigest.getInstance("MD5");
            buffer = new byte[1024];
            int nbOctetsLus = fis.read(buffer);               // Lecture du premier morceau

            while (nbOctetsLus != -1) {
                fonction_de_hachage.update(buffer, 0, nbOctetsLus); // Digestion du morceau
                nbOctetsLus = fis.read(buffer);               // Lecture du morceau suivant
                System.out.println(buffer);
            }
            fis.close();
            resume = fonction_de_hachage.digest();
            System.out.print("Le résumé MD5 du fichier " + fichier + "aut: 0x");
            for(byte octet: resume)
                System.out.print(String.format("%02X", octet));
            // On affiche le résumé en hexadécimal
            System.out.println();
    } catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        cert("HMAC/email1.txt");
    }

}

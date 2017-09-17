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


        try {
            File fichier = new File(text);
            FileInputStream fis = new FileInputStream(fichier);
            fonction_de_hachage = MessageDigest.getInstance("MD5");

        }
        catch (Exception e){ e.printStackTrace(); }
    }

    public static void main(String[] args) {
        cert("HMAC/email1.txt");
    }

}

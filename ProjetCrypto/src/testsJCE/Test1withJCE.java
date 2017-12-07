package testsJCE;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by work on 07/12/17.
 */
public class Test1withJCE
{

    private static Cipher chiffrement;
    private static SecretKeySpec clefSecrete;
    private static byte[] texteChiffre, texteDechiffre;

    private static final byte[] AESKey = {
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };

    private static final byte[] State = {
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };

    public static void main(String[] args) {

        try {
            FileInputStream fis = new FileInputStream("butokuden.jpg");

            chiffrement = Cipher.getInstance("AES/ECB/NoPadding");
            clefSecrete = new SecretKeySpec(AESKey, "AES");
            chiffrement.init(Cipher.ENCRYPT_MODE, clefSecrete);

            texteChiffre = chiffrement.doFinal(State);

            for (byte b : texteChiffre)
                System.out.print((String.format("%02X", b)));

            System.out.println();

            chiffrement.init(Cipher.DECRYPT_MODE, clefSecrete);
            texteDechiffre = chiffrement.doFinal(texteChiffre);

            for (byte b : texteDechiffre)
                System.out.print((String.format("%02X", b)));

            System.out.println();


        }catch (Exception e){ e.printStackTrace(); }

    }


}

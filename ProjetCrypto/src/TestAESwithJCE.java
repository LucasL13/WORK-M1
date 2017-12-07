import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by work on 07/12/17.
 */
public class TestAESwithJCE
{

    private static Cipher chiffrement;
    private static SecretKeySpec clefSecrete;

    private static final byte[] AESKey = {
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };

    static byte[] iv = {
            (byte) 0x62,
            (byte) 0xB8,
            (byte) 0x60,
            (byte) 0x7E,
            (byte) 0x7C,
            (byte) 0xA9,
            (byte) 0x4F,
            (byte) 0x8D,
            (byte) 0x6A,
            (byte) 0x1A,
            (byte) 0x28,
            (byte) 0x38,
            (byte) 0x96,
            (byte) 0x3D,
            (byte) 0x84,
            (byte) 0x27};

    public static void main(String[] args) {

        try {
            FileInputStream fis = new FileInputStream("aes-butokuden.jpg");
            FileOutputStream fos = new FileOutputStream("butokuden-decrypted.jpg");

            byte[] buffer = new byte[1024];

            IvParameterSpec ivspec = new IvParameterSpec(iv);

            chiffrement = Cipher.getInstance("AES/CBC/PKCS5Padding");
            clefSecrete = new SecretKeySpec(AESKey, "AES");
            chiffrement.init(Cipher.DECRYPT_MODE, clefSecrete, ivspec);

            CipherInputStream cis = new CipherInputStream(fis, chiffrement);

            int nb_octets_lus = cis.read(buffer);
            while(nb_octets_lus != -1){
                fos.write(buffer, 0, nb_octets_lus);
                nb_octets_lus = cis.read(buffer);
            }

            fis.close();
            fos.close();
            cis.close();


        }catch (Exception e){ e.printStackTrace(); }

    }


}

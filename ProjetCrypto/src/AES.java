import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.setOut;

/**
 * Created by work on 01/12/17.
 */
public class AES {

    private static final String ERROR_WRONG_PARAMETER = "[AES] Les paramètres d'appels du programme ne sont pas corrects.";
    private static final String ERROR_DISPLAY_USAGE = "[AES] Usage : java AES [-e|-d] [filepath] [keyphrase]";

    private static final boolean DISPLAY_MESSAGES = false;

    private static AES_functions aes_func;

    private static int mode;
    private static final int MODE_ENCRYPT = 5;
    private static final int MODE_ENCRYPT_FILE = 10;
    private static final int MODE_DECRYPT = 6;
    private static final int MODE_DECRYPT_FILE = 12;
    private static final int MODE_PLUSFILE = 2;

    private static String pathfile_in;
    private static final String pathfile_outPrefix = "aes-";
    private static int lecture_bloc;

    private static File in_file;
    private static File out_file;
    private static FileInputStream fis;
    private static FileOutputStream fos;


    static int[][] init_vector = {
            {0x00, 0x00, 0x00, 0x00},
            {0x00, 0x00, 0x00, 0x00},
            {0x00, 0x00, 0x00, 0x00},
            {0x00, 0x00, 0x00, 0x00}
    };

    // Une fonction pour affecter la valeur 0x00 à l'ensemble des octets du bloc State courant
    private static void setBlocNul(){
        int[][] bloc = {
                {0x00, 0x00, 0x00, 0x00},
                {0x00, 0x00, 0x00, 0x00},
                {0x00, 0x00, 0x00, 0x00},
                {0x00, 0x00, 0x00, 0x00}
        };
        aes_func.State = bloc;
    }

    // Une fonction pour affecter la chaine de caracteres en parametres sous formes d'octets du bloc State courant
    // Recoit une chaine de caractere de 32 caracteres (ne commençant pas par "0x")
    // Exemple : setBlocCustom("000000000405060700CC000000000000");
    private static void setBlocCustom(String bloc){
        for(int i=0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                aes_func.State[j][i] = Integer.decode("0x"+bloc.substring((j * 2) + (i * 8), ((j * 2) + (i * 8) + 2)));
            }
        }
        System.out.print("Mise à jour de la valeur du bloc State courant : 0x");
        displayBloc();
    }

    // Une fonction pour affecter la valeur 0x00 à l'ensemble des octets de la clef
    private static void setClefNul(){
        int[][] key = {
                {0x00, 0x00, 0x00, 0x00},
                {0x00, 0x00, 0x00, 0x00},
                {0x00, 0x00, 0x00, 0x00},
                {0x00, 0x00, 0x00, 0x00}
        };
        aes_func.clef_K = key;
    }

    // Une fonction pour affecter la chaine de caracteres en parametres sous formes d'octets de la clefs
    // Recoit une chaine de caractere de 32 caracteres (ne commençant pas par "0x")
    // Exemple : setBlocCustom("000000000405060700CC000000000000");
    private static void setClefCustom(String clef){
        for(int i=0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                aes_func.clef_K[i][j] = Integer.decode("0x"+clef.substring((j * 2) + (i * 8), ((j * 2) + (i * 8) + 2)));
            }
        }
        System.out.print("Mise à jour de la valeur de la Clef : 0x");
        displayClef();
    }


    private static String getMD5(String mdp){
        byte[] buffer = mdp.getBytes();
        byte[] resume = new byte[1024];

        try {
            MessageDigest hachage = MessageDigest.getInstance("MD5");
            resume = hachage.digest(buffer);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Le hachage de la clef \"" + mdp + "\" a échoué. Sortie du programme..");
            exit(200);
        }

        System.out.print("Le hachage de la clef \"" + mdp + "\" vaut : ");
        StringBuilder sb = new StringBuilder();
        for (byte b : resume) {
            sb.append(String.format("%02X", b));
        }
        System.out.println(sb.toString());

        return sb.toString();
    }


    // Une fonction qui affiche le bloc State courant sous la forme d'une chaine hexadécimale de 32 caracteres
    private static void displayBloc(){
        for(int i=0; i < 4; i++)
            for(int j=0; j<4; j++)
                System.out.format("%02X", aes_func.State[j][i]);
        System.out.println();
    }


    // Une fonction qui affiche la clef sous la forme d'une chaine hexadécimale de 32 caracteres
    private static void displayClef(){
        for(int i=0; i < 4; i++)
            for(int j=0; j<4; j++)
                System.out.format("%02X", aes_func.clef_K[i][j]);
        System.out.println();
    }

    private static void crypter(){
        aes_func.AES_Encrypt();
    }

    public static void lire_bloc_forEncrypt(){
        try {

//            byte b[] = new byte[16];
//            fis.read(b);

            //System.out.println(b[1]);

            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++) {
                    if(lecture_bloc == 0)
                        aes_func.State[j][i] = fis.read() ^ init_vector[j][i];
                    else
                        aes_func.State[j][i] = fis.read() ^ aes_func.State[j][i];
                }
            }
            lecture_bloc += 16;

            if(DISPLAY_MESSAGES) System.out.println("Lecture bloc = [" + lecture_bloc + "-" + (lecture_bloc+16) + "]");

        }
        catch (Exception e){ e.printStackTrace(); }
    }

    public static void lire_bloc_forDecrypt(){
        try{
            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++) {
                    aes_func.State[j][i] = fis.read();
                }
            }
            lecture_bloc += 16;

            if(DISPLAY_MESSAGES) System.out.println("Lecture bloc = [" + lecture_bloc + "-" + (lecture_bloc+16) + "]");
        }
        catch (Exception e){ e.printStackTrace(); }
    }

    public static void ecrire_bloc(){
        try{
            for(int i=0; i < 4; i++)
                for(int j=0; j < 4; j++)
                    fos.write(aes_func.State[j][i]);
        }
        catch (Exception e){ e.printStackTrace(); }
    }

    private static void crypter_fichier(){

        pkcs5 pk = new pkcs5();

        try {
            pk.sourceName = pathfile_in;
            pk.sourcePadded = "pkcs5-"+pathfile_in;
            pk.ouvrir_fichier();

            in_file = new File(pk.sourcePadded);
            out_file = new File(pathfile_outPrefix + pathfile_in);

            fis = new FileInputStream(in_file);
            fos = new FileOutputStream(out_file);

            System.out.print("Vecteur choisi : ");

            for(int i=0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    init_vector[j][i] = (int) (Math.random() * 255);
                    fos.write(init_vector[j][i]);
                    System.out.print(String.format("%02X", init_vector[j][i]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        while(lecture_bloc < in_file.length()){
            lire_bloc_forEncrypt();
            if(DISPLAY_MESSAGES) aes_func.afficher_state();
            crypter();
            ecrire_bloc();
        }


//        System.out.println("Longueur du bloc 'State' : \n"+ State.length);
//        System.out.println("Longueur de la clée courte : \n"+ clef_K.length * 4);
//        afficher_state();
//        System.out.println("\nAES_Encrypt() ->\n");
//        AES_Encrypt();
//        afficher_state();

        if(DISPLAY_MESSAGES) System.out.println("Taille fichier source (" + pk.sourcePadded + ") = " + in_file.length());
        if(DISPLAY_MESSAGES) System.out.println("Taille fichier destination (" + pathfile_outPrefix+pathfile_in + ") = " + out_file.length());

        try {
            fis.close();
            fos.close();
        }catch (Exception e){ e.printStackTrace(); }

    }

    private static void decrypter(){
        aes_func.AES_Decrypt();
    }

    private static void decrypter_fichier(){

        try {
            System.out.println("Decryptage de : " + pathfile_in + " => " + pathfile_outPrefix +  pathfile_in);
            in_file = new File(pathfile_in);
            out_file = new File( pathfile_outPrefix +  pathfile_in);

            fis = new FileInputStream(in_file);
            fos = new FileOutputStream(out_file);

            System.out.print("Vecteur d'initialisation lu : ");
            for(int i =0; i < 4; i++)
                for(int j=0; j < 4; j++) {
                    int c = fis.read();
                    if(c == -1){
                        System.out.println("Erreur lecture vecteur initialisation");
                        exit(250);
                    }
                    init_vector[j][i] = c;
                    System.out.print(String.format("%02X", init_vector[j][i]));
                }
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }


        // premiere itération avec le vecteur d'initialisation
        lire_bloc_forDecrypt();
        int[][] blocSave = copyBloc(aes_func.State);
        decrypter();
        for(int i=0; i <4; i++)
            for(int j=0; j < 4; j++)
                aes_func.State[j][i] ^= init_vector[j][i];
        ecrire_bloc();


        // on rentre dans la boucle normale
        while(lecture_bloc+16 < in_file.length()){
            lire_bloc_forDecrypt();
            if(DISPLAY_MESSAGES) System.out.println();
            if(DISPLAY_MESSAGES) displayBloc();
            int[][] blocOld = copyBloc(aes_func.State);
            decrypter();
            for(int i=0; i <4; i++)
                for(int j=0; j < 4; j++)
                    aes_func.State[j][i] ^= blocSave[j][i];
            ecrire_bloc();
            blocSave = copyBloc(blocOld);

            if(DISPLAY_MESSAGES) System.out.print("Decryptage => ");
            if(DISPLAY_MESSAGES) displayBloc();
            if(DISPLAY_MESSAGES) System.out.println();
        }

        System.out.println("Decryptage réussi... \nRechere de padding eventuel en cours..");


        try {
            fis = new FileInputStream(out_file);
            fis.getChannel().position(out_file.length() -1);
            int c = fis.read();
            int compteur = 1;
            boolean padding = true;
            while(compteur != c && padding){
                fis.getChannel().position(out_file.length() - compteur);
                int d = fis.read();
                if(c == d)
                    compteur++;
                else
                    padding = false;
            }

            if(padding){
                System.out.println("Padding present : en cours de retrait.");
                fis = new FileInputStream(out_file);
                byte[] buffer = new byte[(int) out_file.length()-compteur];
                fis.read(buffer, 0, (int) out_file.length()-compteur);
                fis.close();
                fos = new FileOutputStream(out_file);
                fos.write(buffer);
            }

        } catch (IOException e){ e.printStackTrace(); }



        try {
            fis.close();
            fos.close();
        }catch (Exception e){ e.printStackTrace(); }

    }


    private static int[][] copyBloc(int [][]bloc){
        int[][] copy = new int[4][4];
        for(int i = 0; i < 4; i++)
            for(int j= 0; j < 4; j++)
                copy[j][i] = bloc[j][i];
        return copy;
    }

    public static void main(String[] args) {

        //System.out.println("Nombre d'arguments : " + args.length);
        //afficher_usage();

        aes_func = new AES_functions();

        if (args.length == 0) {
            setBlocNul();
            setClefNul();
            mode = MODE_ENCRYPT;
        }

        if (args.length > 0) {
            if (args[0].equals("-e")) {
                setBlocNul();
                setClefNul();
                mode = MODE_ENCRYPT;
            }
            else if (args[0].equals("-d")) {
                setBlocNul();
                setClefNul();
                mode = MODE_DECRYPT;
            }
            else {
                System.out.println(ERROR_WRONG_PARAMETER);
                System.out.println(ERROR_DISPLAY_USAGE);
            }
        }

        if (args.length > 1) {
            mode *= MODE_PLUSFILE;
            pathfile_in = args[1];
        }

        if(args.length > 2){
            setClefCustom(getMD5(args[2]));
        }

        if (args.length > 3) {
            System.out.println(ERROR_WRONG_PARAMETER);
            System.out.println(ERROR_DISPLAY_USAGE);
        }

        switch (mode){
            case MODE_ENCRYPT:
                                    crypter();
                                    System.out.print("Résultat : 0x");    displayBloc();       break;
            case MODE_DECRYPT:      decrypter();
                                    System.out.print("Résultat : 0x");    displayBloc();       break;
            case MODE_ENCRYPT_FILE: crypter_fichier();      break;
            case MODE_DECRYPT_FILE: decrypter_fichier();    break;
        }

        exit(0);

    }
}



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.System.exit;

/**
 * Created by work on 01/12/17.
 */
public class AES {

    private static final String ERROR_WRONG_PARAMETER = "[AES] Les paramètres d'appels du programme ne sont pas corrects.";
    private static final String ERROR_DISPLAY_USAGE = "[AES] Usage : java AES [-e|-d] [filepath] [keyphrase]";

    private static final boolean DISPLAY_MESSAGES = true;

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


    private static void setBlocNul(){
        int[][] bloc = {
                {0x00, 0x00, 0x00, 0x00},
                {0x00, 0x00, 0x00, 0x00},
                {0x00, 0x00, 0x00, 0x00},
                {0x00, 0x00, 0x00, 0x00}
        };
        aes_func.State = bloc;
    }

    private static void setBlocCustom(String bloc){}

    private static void setClefNul(){
        int[][] key = {
                {0x00, 0x00, 0x00, 0x00},
                {0x00, 0x00, 0x00, 0x00},
                {0x00, 0x00, 0x00, 0x00},
                {0x00, 0x00, 0x00, 0x00}
        };
        aes_func.clef_K = key;
    }

    private static void setClefCustom(String clef){}

    private static String getMD5(String mdp){return "";}

    private static void displayBloc(){
        System.out.print("Resultat: 0x");
        for(int i=0; i < 4; i++)
            for(int j=0; j<4; j++)
                System.out.format("%02X", aes_func.State[j][i]);
        System.out.println();
    }

    private static void displayClef(){}

    private static void crypter(){
        aes_func.AES_Encrypt();
        displayBloc();
    }

    public static void lire_bloc(){
        try {

//            byte b[] = new byte[16];
//            fis.read(b);

            //System.out.println(b[1]);

            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++) {
                    if(lecture_bloc == 0)
                        aes_func.State[i][j] = fis.read() ^ init_vector[i][j];
                    else
                        aes_func.State[i][j] = fis.read() ^ aes_func.State[i][j];
                }
            }
            lecture_bloc += 16;

            System.out.println("Lecture bloc = " + lecture_bloc);

        }
        catch (Exception e){ e.printStackTrace(); }
    }

    public static void ecrire_bloc(){
        try{
            for(int i=0; i < 4; i++)
                for(int j=0; j < 4; j++)
                    fos.write(aes_func.State[i][j]);
        }
        catch (Exception e){ e.printStackTrace(); }
    }

    private static void crypter_fichier(){

        pkcs5 pk = new pkcs5();

        try {
            pk.sourceName = pathfile_in;
            pk.ouvrir_fichier();

            in_file = new File(pk.sourcePadded);
            out_file = new File(pathfile_outPrefix + pathfile_in);

            fis = new FileInputStream(in_file);
            fos = new FileOutputStream(out_file);

            for(int i=0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    init_vector[i][j] = (int) (Math.random() * 255);
                    fos.write(init_vector[i][j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        while(lecture_bloc*16 < in_file.length()){
            lire_bloc();
            aes_func.afficher_state();
            crypter();
            //aes_func.afficher_state();
            ecrire_bloc();
        }


//        System.out.println("Longueur du bloc 'State' : \n"+ State.length);
//        System.out.println("Longueur de la clée courte : \n"+ clef_K.length * 4);
//        afficher_state();
//        System.out.println("\nAES_Encrypt() ->\n");
//        AES_Encrypt();
//        afficher_state();

        System.out.println("Taille fichier source (" + pk.sourcePadded + ") = " + in_file.length());
        System.out.println("Taille fichier destination (" + pathfile_outPrefix+pathfile_in + ") = " + out_file.length());

        try {
            fis.close();
            fos.close();
        }catch (Exception e){ e.printStackTrace(); }

    }

    private static void decrypter(){
        aes_func.AES_Decrypt();
        displayBloc();
    }

    private static void decrypter_fichier(){

        pkcs5 pk = new pkcs5();

        try {
            pk.sourceName = pathfile_in;
            pk.ouvrir_fichier();

            in_file = new File(pk.sourcePadded);
            out_file = new File(pathfile_outPrefix + pathfile_in);

            fis = new FileInputStream(in_file);
            fos = new FileOutputStream(out_file);

            for(int i=0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    init_vector[i][j] = (int) (Math.random() * 255);
                    fos.write(init_vector[i][j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        while(lecture_bloc*16 < in_file.length()){
            lire_bloc();
            aes_func.afficher_state();
            decrypter();
            //aes_func.afficher_state();
            ecrire_bloc();
        }


//        System.out.println("Longueur du bloc 'State' : \n"+ State.length);
//        System.out.println("Longueur de la clée courte : \n"+ clef_K.length * 4);
//        afficher_state();
//        System.out.println("\nAES_Encrypt() ->\n");
//        AES_Encrypt();
//        afficher_state();

        System.out.println("Taille fichier source (" + pk.sourcePadded + ") = " + in_file.length());
        System.out.println("Taille fichier destination (" + pathfile_outPrefix+pathfile_in + ") = " + out_file.length());

        try {
            fis.close();
            fos.close();
        }catch (Exception e){ e.printStackTrace(); }

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
            case MODE_ENCRYPT:      crypter();              break;
            case MODE_DECRYPT:      decrypter();            break;
            case MODE_ENCRYPT_FILE: crypter_fichier();      break;
            case MODE_DECRYPT_FILE: decrypter_fichier();    break;
        }

        exit(0);

    }
}



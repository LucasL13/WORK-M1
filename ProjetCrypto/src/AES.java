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
                System.out.format("%02X", aes_func.State[i][j]);
        System.out.println();
    }

    private static void displayClef(){}

    private static void crypter(){
        aes_func.AES_Encrypt();
        displayBloc();
    }

    private static void crypter_fichier(){

    }

    private static void decrypter(){}
    private static void decrypter_fichier(){}

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



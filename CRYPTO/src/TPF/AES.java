package TPF;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;

/**
 * Created by work on 06/10/17.
 */
public class AES {

    private static boolean debug = false;

    private static String cryptedName = "cbc-secret.jpg";

    private static File in_file;
    private static File out_file;

    private static FileInputStream fis;
    private static FileOutputStream fos;

    private static int lecture_bloc = 0;

    public static Diversification clefs;

    static int[][] State = {
            {0x00, 0x00, 0x00, 0x00},
            {0x00, 0x00, 0x00, 0x00},
            {0x00, 0x00, 0x00, 0x00},
            {0x00, 0x00, 0x00, 0x00}
    };

    static int[][] clef_K = {
            {0x00, 0x00, 0x00, 0x00},
            {0x00, 0x00, 0x00, 0x00},
            {0x00, 0x00, 0x00, 0x00},
            {0x00, 0x00, 0x00, 0x00}
    };
    //static int[][] clef_K = {{0xFF, 0xFF, 0xFF, 0xFF}, {0xFF, 0xFF, 0xFF, 0xFF}, {0xFF, 0xFF, 0xFF, 0xFF}, {0xFF, 0xFF, 0xFF, 0xFF}, {0xFF, 0xFF, 0xFF, 0xFF}, {0xFF, 0xFF, 0xFF, 0xFF}, {0xFF, 0xFF, 0xFF, 0xFF}, {0xFF, 0xFF, 0xFF, 0xFF}};

    static int[][] Mat_Columns = {
            {0x02, 0x03, 0x01, 0x01},
            {0x01, 0x02, 0x03, 0x01},
            {0x01, 0x01, 0x02, 0x03},
            {0x03, 0x01, 0x01, 0x02}
    };

    static int[][] init_vector = {
            {0x00, 0x00, 0x00, 0x00},
            {0x00, 0x00, 0x00, 0x00},
            {0x00, 0x00, 0x00, 0x00},
            {0x00, 0x00, 0x00, 0x00}
    };


    public static int gmul(int a, int b) {
        int p = 0;
        int hi_bit_set;
        int i;
        for(i = 0; i < 8; i++) {
            if((b & 1) == 1)
                p ^= a;
            hi_bit_set = (a & 0x80);
            a <<= 1;
            if(hi_bit_set == 0x80)
                a ^= 0x1b;
            b >>= 1;
        }
        return p & 0xFF;
    }


    public static void SubBytes(){
        for(int i=0; i < 4; i++)
            for(int j=0; j < 4; j++)
                State[i][j] = clefs.SBox[State[i][j]];
    }


    public static void Inv_SubBytes(){
        for(int i=0; i <4; i++){
            for(int j=0; j<4; j++){
                int trouve = 0;
                while(trouve != -1){
                    if(clefs.SBox[trouve] == State[i][j]){
                        State[i][j] = trouve;
                        trouve = -1;
                    }
                    else
                        trouve++;
                }
            }
        }
    }


    public static void ShiftRows(){

        int[] tmp = new int[4];
        for(int i=0; i < 4; i++){
            for(int j=0; j < 4; j++)
                tmp[j] = State[i][(j+i) % 4];

            for(int j=0; j < 4; j++)
                State[i][j] = tmp[j];
        }
    }


    public static void MixColumns(){

        for(int colonne = 0; colonne < 4; colonne++){
            // state [][colonne]
            int[] tmp = new int[4];

            for(int x=0; x<4; x++) {
                tmp[x] = gmul(State[0][colonne], Mat_Columns[x][0]);
            }

            for(int i=0; i<4; i++){
                for(int j=1; j<4; j++){
                    tmp[i] ^= gmul(State[j][colonne], Mat_Columns[i][j]);
                }
            }

            for(int x=0; x<4; x++)
                State[x][colonne] = tmp[x];
        }
    }


    public static void afficher_state(){
        System.out.println();
        for(int i=0; i < 4; i++) {
            for(int j=0; j < 4; j++) {
                System.out.format("%02X | ", State[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


    public static void afficher_cle_ronde(int r){
        System.out.println("J'affiche clé de ronde ("+r+") : " );
        for(int i =0; i < 4; i++){
            for(int j=0; j<4; j++){
                System.out.format("%02X | ", clefs.W[j + (r*4)][i]);
            }
            System.out.println();
        }
    }


    public static void AddRoundKey(int r){
        if(debug) afficher_cle_ronde(r);
        for(int i = 0; i < 4; i++){
            for(int j=0; j <4; j++){
                if(debug) System.out.format("State[%d][%d] = %02X\n", i, j, State[i][j]);
                if(debug) System.out.format("clefs.W[%d][%d] = %02X\n", (j + (r*4)), i, clefs.W[j + (r*4)][i]);
                State[i][j] ^= clefs.W[j + (r*4)][i];
                if(debug) System.out.format("State[%d][%d] = %02X\n\n", i, j, State[i][j]);
            }
        }
    }


    public static void AES_Encrypt(){
        clefs = new Diversification();
        clefs.K = clef_K;
        clefs.calcule_la_clef_etendue();
        clefs.affiche_la_clef();

        AddRoundKey(0);

        for(int r=1; r<clefs.Nr; r++){
            SubBytes();
            if(debug) System.out.println("After SubBytes :");
            if(debug) afficher_state();

            ShiftRows();
            if(debug) System.out.println("After ShiftRows :");
            if(debug) afficher_state();

            MixColumns();
            if(debug) System.out.println("After MixColums :");
            if(debug) afficher_state();

            AddRoundKey(r);
            if(debug) System.out.println("After RoundKey :");
            if(debug) afficher_state();
        }

        SubBytes();
        ShiftRows();
        AddRoundKey(clefs.Nr);
    }

    public static void lire_bloc(){
        try {

//            byte b[] = new byte[16];
//            fis.read(b);

            //System.out.println(b[1]);

            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++) {
                    if(lecture_bloc == 0)
                        State[i][j] = fis.read() ^ init_vector[i][j];
                    else
                        State[i][j] = fis.read() ^ State[i][j];
                }
            }
            lecture_bloc += 16;

        }
        catch (Exception e){ e.printStackTrace(); }
    }

    public static void ecrire_bloc(){
        try{
            for(int i=0; i < 4; i++)
                for(int j=0; j < 4; j++)
                    fos.write(State[i][j]);
        }
        catch (Exception e){ e.printStackTrace(); }
    }

    public static void main(String[] args) {

        System.out.println("Starting main class : AES");

        pkcs5 pk = new pkcs5();
        try {
            pk.sourceName = "butokuden.jpg";
            pk.ouvrir_fichier();

            in_file = new File(pk.sourcePadded);
            out_file = new File(cryptedName);

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



        while(lecture_bloc < in_file.length()){
            lire_bloc();
            AES_Encrypt();
            ecrire_bloc();
        }

//        System.out.println("Longueur du bloc 'State' : \n"+ State.length);
//        System.out.println("Longueur de la clée courte : \n"+ clef_K.length * 4);
//        afficher_state();
//        System.out.println("\nAES_Encrypt() ->\n");
//        AES_Encrypt();
//        afficher_state();

        System.out.println("Taille fichier source (" + pk.sourcePadded + ") = " + in_file.length());
        System.out.println("Taille fichier destination (" + cryptedName + ") = " + out_file.length());

        try {
            fis.close();
            fos.close();
        }catch (Exception e){ e.printStackTrace(); }
    }

}

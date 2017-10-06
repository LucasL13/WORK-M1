package TP2.AES.Diversification;

import java.util.Arrays;

/**
 * Created by work on 06/10/17.
 */
public class AES {

    public static Diversification clefs;

    //static int[] State = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
//    static int[][] State = {
//            {0x00, 0x04, 0x08, 0x0C},
//            {0x01, 0x05, 0x09, 0x0D},
//            {0x02, 0x06, 0x0A, 0x0E},
//            {0x03, 0x07, 0x0B, 0x0F},
//    };
//
    static int[][] State = {
            {0x0E, 0x0B, 0x0D, 0x09},
            {0x09, 0x0E, 0x0B, 0x0D},
            {0x0D, 0x09, 0x0E, 0x0B},
            {0x0B, 0x0D, 0x09, 0x0E},
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
        int[] tmp = new int[4];

        for(int x = 0; x < 4; x++) {
            System.out.print("Je vais MixColumns la colonne [");
            for (int i = 0; i < 4; i++)
                tmp[i] = 0;

            for (int i = 0; i < 4; i++) {
                System.out.format("%02X, ", State[i][x]);
                tmp[x] ^= gmul(State[i][x],Mat_Columns[x][i]);
            }
            System.out.println("]");
            for(int i=0; i < 4; i++)
                State[i][x] = tmp[i];
        }

    }



    public static void afficher_state(){
        System.out.println();
        for(int i=0; i < State.length; i++) {
            for(int j=0; j < 4; j++) {
                System.out.format("%02X | ", State[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("Starting main class : AES");

        System.out.println("Longueur du bloc 'State' : \n"+ State.length);
        System.out.println("Longueur de la clée courte : \n"+ clef_K.length * 4);

        afficher_state();

        //SubBytes();
        //afficher_state();

        //ShiftRows();
        //afficher_state();

        MixColumns();
        afficher_state();

        clefs = new Diversification();
        clefs.K = clef_K;

        clefs.calcule_la_clef_etendue();

        //clefs.affiche_la_clef();

    }

}

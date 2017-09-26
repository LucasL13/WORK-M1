package TP1;

public class TP1
{
    private static int reçu[] = new int[7];
    private static int transmit[] = new int[7];
    private static int lut[] = new int[4];


    public static void affiche(int[] l)
    {
        for(int i = l.length-1; i>=0; i--)
        {
            System.out.print(l[i]);
        }
        System.out.println();
    }


    public static void lire(int A, int B, int C, int D)
    {
        lut[3] = A;
        lut[2] = B;
        lut[1] = C;
        lut[0] = D;
    }


    public static void calcul (int A, int B, int C, int D)
    {
        int P0, P1, P2;

        P0 = (A + C + D) % 2;
        P1 = (A + B + D) % 2;
        P2 = (A + B + C) % 2;

        transmit[6] = A;
        transmit[5] = B;
        transmit[4] = C;
        transmit[3] = P2;
        transmit[2] = D;
        transmit[1] = P1;
        transmit[0] = P0;
    }
    public static void calcul (int[] l)
    {
        int A, B, C, D, P0, P1, P2;

        A = lut[3];
        B = lut[2];
        C = lut[1];
        D = lut[0];

        P0 = (A + C + D) % 2;
        P1 = (A + B + D) % 2;
        P2 = (A + B + C) % 2;

        transmit[6] = A;
        transmit[5] = B;
        transmit[4] = C;
        transmit[3] = P2;
        transmit[2] = D;
        transmit[1] = P1;
        transmit[0] = P0;
    }


    public static void envoie()
    {
        int A, B, C, D;
        reçu = transmit.clone();

        //A =
        //B =

        int err1 = 6;
        reçu[err1] = (reçu[err1]+1)%2;

        int err2 = 5;
        reçu[err2] = (reçu[err2]+1)%2;
    }

    public static int test(int[] l)
    {
        int p0, p1, p2;
        int res;

        p0 = (l[0] + l[6] + l[4] + l[2]) % 2;
        p1 = (l[1] + l[6] + l[5] + l[2]) % 2;
        p2 = (l[3] + l[6] + l[5] + l[2]) % 2;

        res = p0 + p1*2 + p2*4;
        if(res == 0)
        {
            return -1;
        }
        else
        {
            return 7-res;
        }
    }


    public static void corriger()
    {
        int n = test(reçu);
        reçu[n] = (reçu[n]+1)%2;
    }


    public static void main(String[] arg)
    {
        lire(1,0,1,0);
        affiche(lut);
        calcul(lut);
        affiche(transmit);
        envoie();
        affiche(reçu);
        if(test(reçu) == -1)
            System.out.println("Pas d'erreur");
        else
            System.out.println("Erreur en "+test(reçu));
        corriger();
        if(test(reçu) == -1)
            System.out.println("Pas d'erreur, apres correction");
        else
            System.out.println("Erreur double");
    }
}

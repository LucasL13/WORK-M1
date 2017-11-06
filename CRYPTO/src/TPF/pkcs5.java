package TPF;

import java.io.*;

/**
 * Created by work on 06/11/17.
 */
public class pkcs5 {

    static File source;
    static String sourceName;
    static FileInputStream fis;
    static FileOutputStream fos;

    public static void add_padding(int paddToAdd) throws IOException{

        String sourcePadded = "pkcs5-" + sourceName;

        fis = new FileInputStream(source);
        fos = new FileOutputStream(new File(sourcePadded));

        int c = fis.read();

        do{
            fos.write(c);
            c = fis.read();
        }while(c != -1);

        int toAdd = 16 - paddToAdd;

        for(int i=0; i < toAdd; i++)
            fos.write(toAdd);

        fis.close();
        fos.close();

        File copy = new File(sourcePadded);
        System.out.println("Taille du nouveau fichier   : " + copy.length() + " ( = " + (copy.length() % 16) + "%16 )");

    }

    public static void ouvrir_fichier() throws IOException{
        source = new File(sourceName    );

        System.out.println("Taille du fichier d'origine : " + source.length() + " ( = " + (source.length() % 16) + "%16 )");

        int length_modulo = (int) (source.length() % 16);

        if( length_modulo != 0 )
            add_padding(length_modulo);
    }

    public static void main(String[] args) {

        sourceName = "butokuden.jpg";

        try {
            ouvrir_fichier();
        }
        catch (IOException e) { e.printStackTrace(); }

    }

}

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/** Une classe pour externaliser la procédure de padding **/
/** Prend un fichier en entrée et construit "pkcs5-filename" en sortie **/

public class pkcs5 {

    File source;
    String sourceName;
    FileInputStream fis;
    FileOutputStream fos;
    String sourcePadded = "pkcs5-" + sourceName;

    private boolean DISPLAY_MESSAGES = false;

    public void add_padding(int paddToAdd) throws IOException{


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
        if(DISPLAY_MESSAGES) System.out.println("Taille du nouveau fichier   : " + copy.length() + " ( = " + (copy.length() % 16) + "%16 )");
    }

    public void ouvrir_fichier() throws IOException{
        source = new File(sourceName);

        if(DISPLAY_MESSAGES)System.out.println("Taille du fichier d'origine : " + source.length() + " ( = " + (source.length() % 16) + "%16 )");

        int length_modulo = (int) (source.length() % 16);

        if( length_modulo != 0 )
            add_padding(length_modulo);
    }


}

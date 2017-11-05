package TPE;// -*- coding: utf-8 -*-

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.sound.midi.Soundbank;
import java.math.BigInteger;
import java.util.Random;
import java.util.StringJoiner;

public class RSA {
    private static int LG_MAX = 10;
    // LG_MAX est la longueur maximale du texte clair (en nombre de caractères ASCII)
    
    private static String message, messageDechiffré ;
    
    private static BigInteger code, code_chiffre, code_dechiffre ;
    private static BigInteger n ;    // Le module de la clef publique
    private static BigInteger e ;    // L'exposant de la clef publique
    private static BigInteger d ;    // L'exposant de la clef privée

    private static FabriqueNP fnp;
    
    public static void main(String[] args) {

        fnp = new FabriqueNP();

        message = "SalutCecIesTuNMEssageCOde";                     // C'est le message ASCII à chiffrer
        System.out.println("Mess length = " + message.length());
        os2ip(); // <----------------------------------------------------- Exercice 2
        System.out.println("Message de " + message.length() + " caractères codé par "
                           + code);
        fabrique(); // <-------------------------------------------------- Exercice 1 
        System.out.println("Clef publique (n) : " + n);
        System.out.println("Clef publique (e) : " + e);
        System.out.println("Clef privée (d)   : " + d);
        /* Chiffrement */
        code_chiffre = code.modPow(e, n);
        System.out.println("Code chiffré      : " + code_chiffre);
        /* Déchiffrement */
        code_dechiffre = code_chiffre.modPow(d, n);
        System.out.println("Code déchiffré    : " + code_dechiffre);
        /* Retour à la chaîne de caractères */
        i2osp(); // <----------------------------------------------------- Exercice 3
        System.out.println("Message déchiffré : " + messageDechiffré);
    }

    static void fabrique() {
        n = new BigInteger("196520034100071057065009920573", 10);
        e = new BigInteger("7", 10);
        d = new BigInteger("56148581171448620129544540223", 10);

        BigInteger p = fnp.trouverGrandNombrePremier(1024);
        BigInteger q = fnp.trouverGrandNombrePremier(1024);

        n = p.multiply(q);

        BigInteger pp = p.add(new BigInteger("-1"));
        BigInteger qq = q.add(new BigInteger("-1"));

        BigInteger w = pp.multiply(qq);

//      System.out.println("n = " + n);
        System.out.println("w = " + w);

        BigInteger ww = w.add(BigInteger.ONE);

        do{
            d = new BigInteger(1024, new Random());
        }while( (d.compareTo(w) != -1) ||
                (!(d.gcd(w)).equals(BigInteger.ONE)));

        e = d.modInverse(w);
    }
    
    static void os2ip() {
        BigInteger x = BigInteger.ZERO;

        byte X[] = message.getBytes();

        for(int i=0; i < X.length; i++){
            x = x.add(new BigInteger(String.valueOf(X[X.length - 1 - i])).multiply(new BigInteger("256").pow(i)));
        }
        code = x;
    }
    
    static void i2osp() {

        int l = 0;
        BigInteger b = BigInteger.ZERO;

        do{
            l++;
            b = new BigInteger("256").pow(l);
        }while( b.compareTo(code_dechiffre) != 1);

        System.out.println("l = " + l);

        byte text[] = new byte[l];

        for(int i=(l-1); i >= 0; i--){
            BigInteger t = code_dechiffre.divide(new BigInteger("256").pow(i));
            t = t.mod(new BigInteger("256"));
            text[ (l-1) - i] = t.byteValueExact();
        }
        messageDechiffré = new String(text);
    }  
}

/*
  $ make
  javac *.java 
  $ java RSA
      Message de 6 caractères codé par 71933831046500
  Clef publique (n) : 196520034100071057065009920573
  Clef publique (e) : 7
  Clef privée (d)   : 56148581171448620129544540223
  Code chiffré      : 45055523945410630249803126960
  Code déchiffré    : 71933831046500
  Message déchiffré : Alfred
  $ make
  javac *.java 
  $ java RSA
  Message de 6 caractères codé par 71933831046500
  Clef publique (n) : 16030892766762842431340770210743036685031398079921553670604685486569286656606605595631225776977235841385444053108119117583512126256994204056608207489789843098528364927014755351774272172033675560634957816099973125577252317756472338260132789745526498461934134997517242632492781502437184042928092412552614439386554580469944958405794663512768805525125398746656203495698863562460606952192777731756933050677561945329887692965305208866537474206424649676076215454488192033687912480859440407832297462421827997413801358456441362944296749071007302495790239766670317209239851687609894689591009328799675082867777410155073861046373
  Clef publique (e) : 65537
  Clef privée (d)   : 9708502584994998490927493929602989548329892881762767065723178768664036916561883761701074981891549667280898949720940045728208436320553274623598574168328713132743195507167182506247171254528229595520110406655903281416011481937750997231253649465186791033372992630902533837124654742080532137019027234298385142732230978418956266501279510883188069572386849161422361779286163075281592096969347680186375873739144649265540663588175935242430348639507261039476863831234908279114835989501398488944103778312539088385386460829754324904254033503310708682092578403088242533994291075874942217014420782887844958939621076485086812511233
  Code chiffré      : 7641292418053626962665278875580142015436460693577851491986938480114503327125526178265350136700682289592344443712694725930902760855426496212236767617337463872232819344134167623007333019186753732712166095984572956817430043705938253278806854139247820333438321470755767267235030054425043622595048582419904482851580978445154834596054769781348588115772614694966955178203514232958280538491681561814416551716032551912706735393315194946034135755318871886272457362041587641660881976673827763481534604491121402419146647773577086036781911424740442920947275720115487215153942722314144483330241566289484443670774019111345130196205
  Code déchiffré    : 71933831046500
  Message déchiffré : Alfred
*/

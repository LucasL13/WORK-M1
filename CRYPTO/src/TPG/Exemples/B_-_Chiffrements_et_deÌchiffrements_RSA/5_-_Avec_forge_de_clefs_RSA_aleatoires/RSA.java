// -*- coding: utf-8 -*-

import java.*;
import java.math.BigInteger;

import java.security.*;
import javax.crypto.*;
import java.security.spec.*;
import java.security.interfaces.*;

public class RSA {
    public static void main(String[] args) throws Exception {

	System.out.println("Texte clair: \"Alfred\"");
	byte[] message = "Alfred".getBytes();
	System.out.println("Message clair (en décimal): \"" +
			    String.format("%d", new BigInteger(message)) +"\"");
	System.out.println("Message clair (en hexadécimal): \"" + toHex(message) + "\"");

        //------------------------------------------------------------------
        //  Etape 1.   Récupérer un objet qui chiffre et déchiffre en RSA
	//             avec bourrage mais sans mode opératoire (ECB)
        //------------------------------------------------------------------
	Cipher chiffrement = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        //------------------------------------------------------------------
        //  Etape 2.   Fabriquer une paire de clef et l'afficher.
        //------------------------------------------------------------------
	SecureRandom alea = new SecureRandom();
	KeyPairGenerator forge = KeyPairGenerator.getInstance("RSA");
	forge.initialize(1024, alea);                   // Des clefs de taille 1024, SVP
	KeyPair paireDeClefs = forge.generateKeyPair();
	Key clefPublique = paireDeClefs.getPublic();
	Key clefPrivee = paireDeClefs.getPrivate();

	KeyFactory usine = KeyFactory.getInstance("RSA");
	RSAPublicKeySpec specif = usine.getKeySpec(clefPublique, RSAPublicKeySpec.class);
	System.out.println("N = " + specif.getModulus());
	System.out.println("E = " + specif.getPublicExponent());
	RSAPrivateKeySpec specifPrivee = usine.getKeySpec(clefPrivee, RSAPrivateKeySpec.class);
	System.out.println("D = " + specifPrivee.getPrivateExponent());
	
	//------------------------------------------------------------------
        //  Etape 3.   Chiffrer et afficher le message chiffré
        //------------------------------------------------------------------
	chiffrement.init(Cipher.ENCRYPT_MODE, clefPublique);
	byte[] messageChiffre = chiffrement.doFinal(message);
	System.out.println("Message chiffré (en hexadécimal): \n" + toHex(messageChiffre));
	//------------------------------------------------------------------
        //  Etape 4.   Déchiffrer en guise de vérification
        //------------------------------------------------------------------
	chiffrement.init(Cipher.DECRYPT_MODE, clefPrivee);
	byte[] messageDechiffre = chiffrement.doFinal(messageChiffre);
	System.out.println("Message déchiffré: \"" + new String(messageDechiffre) +"\"");
    }

    public static String toHex(byte[] donnees) {
	return javax.xml.bind.DatatypeConverter.printHexBinary(donnees);
    }    
}

/*

> make
javac *.java 
>
> java RSA
Texte clair: "Alfred"
Message clair (en décimal): "71933831046500"
Message clair (en hexadécimal): "416C66726564"
N = 90199370006844068611700045809609120158214459793160910504050407350312107044648043006523173038378837188894449687911389682828811349038901121446927453162284818803342329872130308796632690913325811431893015797025264041612354176078279022605767385323383650988999277129755709628020086343596443320758866659060302309293
E = 65537
D = 9634185117535262222590297399442510964912968530023137670756257556070383894785179380283842886745683511943804992834272667040018301772621692328432674247157996201808737991448564311757915895866801532252869658801975581067700310957506855639673012983314114990301195894344743616046125276226765072623676267343104193
Message chiffré (en hexadécimal): 
04715059520020B4D16C7972695F8CF0B1B1CC9431DE175D18749D005D193A359DFF520FD0805D8B30B074E299E26F6B2ED9465CCA3BF0F83455A8A805509141F1E7DA72790B60C32AD4261BA38E940EE7CE450868791E1B3C5EE73FBB2202DC6970141703B7ECF39EFA1E38A817AAF0C33E015E13ACA840C8712F34E451916F
Message déchiffré: "Alfred"
>
>
> java RSA
Texte clair: "Alfred"
Message clair (en décimal): "71933831046500"
Message clair (en hexadécimal): "416C66726564"
N = 97334098430163622889862066849953111097135253021230006948385718463173051526849355345111127494864729128291867486462558301765020386801792364633709593550759706556284715329355988222874316659948347409150691030981902764336621482700572835608142739990213328399935789307932712562867099086681500390520343095085159803817
E = 65537
D = 51880231416794721680709549097495492269178153692381503619650120044487252024595292444198268240087503515716183454187864665719451487736091229097833949096161517585298879650467722169275598061784849055717045315385989471268416479790593818697743645295391609949486280432197556044365901300188955859019488075046727353553
Message chiffré (en hexadécimal): 
68D361DCAA8BB75DC843869312073F786B35FB47DC08B9A365E64A4CF2C89F7E59341C9F1382871EFD5A88ADC9FC1E5FC73C02EA0942FF58FB10F51D4F47F656F244DD7F136D5511723FCF199E8626CF096A06D2A7A1F17348B98C5EA8647BFFA3170B8D01B4DEBBBB954B02F3FC8224F2C2C5D19EF325799E2E997C64AA0604
Message déchiffré: "Alfred"
>
*/


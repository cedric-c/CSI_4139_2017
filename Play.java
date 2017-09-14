/**
 * @todo (1) Generate 2 public-key / private-key pairs, one for encryption/decryption and one for signing/verifying. 
 * @todo Take a file as input and call the appropriate routines to hash and sign it, and also to encrypt it with a symmetric key and then encrypt the symmetric key with the public key of a recipient.
 * @todo In addition, your program must be able to input a protected file, decrypt it, hash it, and verify the digital signature using the originator’s public verification key.
 * During the lab demonstration of your program, the TA will supply a file to be protected. The TA will observe the protection of an unprotected file, and the un-protection of a protected file.
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import java.security.SecureRandom;
import java.security.KeyPairGenerator;
import java.security.NoSuchProviderException;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.PrivateKey;

public class Play {
    public static void main(String[] args){
        try{
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hash = sha.digest();
            
        } catch(NoSuchAlgorithmException e){
            System.out.println("Caught1");
        }
        
        try{
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");// RSA, DSA,
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(2048, random);
            System.out.println("Keygen made");
            
            KeyPair pair = keyGen.generateKeyPair();
            PrivateKey priv = pair.getPrivate();
            PublicKey pub = pair.getPublic();
            
                        
        } catch(NoSuchAlgorithmException e){
            System.out.println("Caught2");            
        } catch(NoSuchProviderException e){
            System.out.println("No Provider");
        }
        // byte[] publicKey = keyGen.genKeyPair().getPublic().getEncoded();
        // StringBuffer retString = new StringBuffer();
        // for (int i = 0; i < publicKey.length; ++i) {
            // retString.append(Integer.toHexString(0x0100 + (publicKey[i] & 0x00FF)).substring(1));
        // }
        // System.out.println(retString);        
        
        // String in = Play.toBase64("Hello World");
        // System.out.println(in);
        // String b = DatatypeConverter.parseBase64Binary(in);
        // System.out.println(b);
        // String out = Play.fromBase64(in);
        // System.out.println(out);

    }
    
    public static String toBase64(String content){
        byte[] array = content.getBytes();
        return DatatypeConverter.printBase64Binary(array);
    }
    
    public static String fromBase64(String content){
        byte[] array = DatatypeConverter.parseBase64Binary(content);
        String ret = array.toString();
        return ret;
    }
}
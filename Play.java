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
public class Play {
    public static void main(String[] args){
        try{
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hash = sha.digest();
            
        } catch(NoSuchAlgorithmException e){
            System.out.println("Caught");
        }
        String str = "Your string";
        byte[] array = str.getBytes();
        System.out.println("encrypted string:" + DatatypeConverter.printBase64Binary(array));
        
        String out = Play.toBase64("Hello World");
        System.out.println(out);
        
        String in = Play.fromBase64(out);
        System.out.println(in);
        
        // byte[] original = cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted‌​));
        System.out.println("Bonjour Pauline");
        
        byte[]   bytesEncoded = Base64.encodeBase64(str .getBytes());
        System.out.println("ecncoded value is " + new String(bytesEncoded ));

        // Decode data on other side, by processing encoded data
        byte[] valueDecoded= Base64.decodeBase64(bytesEncoded );
        System.out.println("Decoded value is " + new String(valueDecoded));        
        
        // sha.update("i1");
        // sha.update("i2");
        // sha.update("i3");
    }
    
    public static String toBase64(String content){
        byte[] encoded = Base64.encodeBase64(content.getBytes());
        return new String(encoded);
    }
    
    public static String fromBase64(String content){
        byte[] array = content.getBytes();
        byte[] decoded = Base64.decodeBase64(array);
        return new String(decoded);
    }
}
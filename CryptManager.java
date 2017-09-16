import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.io.File;
import java.security.InvalidKeyException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;
/**
 * Generates the key objects from java File objects or paths.
 */
public class CryptManager{
    
    public static PrivateKey getPrivateKey(String filename, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        // byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        byte[] keyBytes = SimpleIO.readBytes(filename);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(algorithm);
        return kf.generatePrivate(spec);
    }

    public static PublicKey getPublicKey(String filename, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        // byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        byte[] keyBytes = SimpleIO.readBytes(filename);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(algorithm);
        return kf.generatePublic(spec);
    }

    public static SecretKeySpec getSecretKey(String filename, String algorithm) throws IOException{
        // byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        byte[] keyBytes = SimpleIO.readBytes(filename);
        return new SecretKeySpec(keyBytes, algorithm);
    }
    
    public static SecretKeySpec getSecretKey(File file, String algorithm) throws IOException{
        byte[] keyBytes = SimpleIO.readBytes(file);
        return new SecretKeySpec(keyBytes, algorithm);
    }
    
    /**
     * @param byte[]        toEncrypt 
     * @param File          output    
     * @param SecretKeySpec secretKey 
     */
    public static void encryptKey(PublicKey pub, File keyFile, File encryptedKeyFile, String algorithm)  throws NoSuchAlgorithmException, InvalidKeyException, IOException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        
        byte[] toEncrypt = SimpleIO.readBytes(keyFile);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, pub);
        SimpleIO.writeBytes(encryptedKeyFile, cipher.doFinal(toEncrypt));
    }
    
    public static void encryptData(File unencrypted, File encrypted, SecretKeySpec secret, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, IOException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        // File originalFile = new File("confidential.txt");
        // File encryptedFile = new File("EncryptedFiles/encryptedFile");
        // new EncryptData(originalFile, encryptedFile, startEnc.getSecretKey("OneKey/secretKey", "AES"), "AES");{
        //             public EncryptData(File originalFile, File encrypted, SecretKeySpec secretKey, String cipherAlgorithm) throws IOException, GeneralSecurityException{
        //                 this.cipher = Cipher.getInstance(cipherAlgorithm);      
        //                 encryptFile(getFileInBytes(originalFile), encrypted, secretKey);
        //             }
                    
        //             public void encryptFile(byte[] input, File output, SecretKeySpec key) throws IOException, GeneralSecurityException {
        //                 this.cipher.init(Cipher.ENCRYPT_MODE, key);
        //                 writeToFile(output, this.cipher.doFinal(input));
        //             }                
        // }
        
        // passes unencrypted, encrypted, SecretKeySpec, and Cipher Algo
        
        Cipher cipher = Cipher.getInstance(algorithm);
        byte[] unencryptedBytes = SimpleIO.readBytes(unencrypted);
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        SimpleIO.writeBytes(encrypted, cipher.doFinal(unencryptedBytes));
        
    }
    
    
}
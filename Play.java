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
import java.nio.file.Files;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;

import java.nio.file.Paths;

import java.io.*;
// import java.security.SecureRandom;
// import java.security.KeyPairGenerator;
// import java.security.NoSuchProviderException;
// import java.security.KeyPair;
// import java.security.PublicKey;
// import java.security.PrivateKey;
// import java.security.Signature;
import javax.crypto.KeyGenerator;


import java.security.*;

public class Play {
    public static void main(String[] args) throws InvalidKeyException, FileNotFoundException, BadPaddingException, NoSuchAlgorithmException{
        try{
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hash = sha.digest();
            
        } catch(NoSuchAlgorithmException e){
            System.out.println("Caught1");
        }
        
        try{
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");// RSA, DSA,
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);
            System.out.println("Keygen made");
            
            KeyPair pair = keyGen.generateKeyPair();
            PrivateKey priv = pair.getPrivate();
            PublicKey pub = pair.getPublic();
            PublicKey pub2 = pair.getPublic();
            
            byte[] encodedPublic = pub.getEncoded();
            System.out.println("Public 1: " + encodedPublic.toString());            
            
            byte[] encodedPrivate = priv.getEncoded();
            System.out.println("Private 1: " + encodedPrivate.toString());
            
            Signature dsa = Signature.getInstance("SHA1withDSA", "SUN"); 
            dsa.initSign(priv);
            
            String path = "/Users/ced/Documents/uottawa/csi/4139_17/labs/01_encryption/file";
            FileInputStream fis = new FileInputStream(path);
            BufferedInputStream bufin = new BufferedInputStream(fis);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bufin.read(buffer)) >= 0) {
                dsa.update(buffer, 0, len);
            };
            bufin.close();  
            
            byte[] realSig = dsa.sign();
            System.out.println(realSig.toString());
            
            /* save the signature in a file */
            String pth = "/Users/ced/Documents/uottawa/csi/4139_17/labs/01_encryption/";
            FileOutputStream sigfos = new FileOutputStream(pth + "sue.signature");
            sigfos.write(realSig);
            sigfos.close();
            
            /* save the public key in a file */
            byte[] key = pub.getEncoded();
            FileOutputStream keyfos = new FileOutputStream(pth + "sue.publicKey");
            keyfos.write(key);
            keyfos.close();
            
            /* save the private key in a file */
            byte[] keyPriv = priv.getEncoded();
            FileOutputStream keyfos2 = new FileOutputStream(pth + "sue.privateKey");
            keyfos2.write(keyPriv);
            keyfos2.close();
            
            
            // Path encryptThisFile = new Path();
            String fileInput = Files.readAllBytes(Paths.get(pth + "file")).toString();
            // ENCRYPTION
            // specify mode and padding instead of relying on defaults (use OAEP if available!)
            String i1 = "DES/CFB8/NoPadding";       // no providers
            String i2 = "DES/CBC/PKCS5Padding";     // no providers
            String i3 = "DES/OFB32/PKCS5Padding";   // no providers
            String i4 = "AES/CBC/PKCS5Padding";
            
            KeyGenerator gen = KeyGenerator.getInstance("AES");
            gen.init(128);  // Key size
            Key key1 = gen.generateKey();
            
            
            Cipher encrypt=Cipher.getInstance(i4);
            // init with the *public key*!
            encrypt.init(Cipher.ENCRYPT_MODE, key1);
            // encrypt with known character encoding, you should probably use hybrid cryptography instead 
            byte[] encryptedMessage = encrypt.doFinal(fileInput.getBytes());
            String encryptedFilePath = pth + "file.encrypted";
            
            FileOutputStream encryptedFOS = new FileOutputStream(encryptedFilePath);
            encryptedFOS.write(encryptedMessage);
            encryptedFOS.close();
            

                        
        } catch(NoSuchAlgorithmException e){
            System.out.println("NoSuchAlgorithmException");            
            throw e;
        } catch(NoSuchProviderException e){
            System.out.println("No Provider");
        } catch(InvalidKeyException e){
            System.out.println("Invalid Key");
            throw e;
        } catch(FileNotFoundException e){
            System.out.println("File Not Found");
            throw e;
        } catch(IOException e){
            System.out.println("No Provider");
        } catch(SignatureException e){
            System.out.println("Signature Exception");
        } catch(NoSuchPaddingException e){
            System.out.println("Signature Exception");
        } catch(IllegalBlockSizeException e){
            System.out.println("Signature Exception");
        }

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
    
    public static String readFile(String path) throws IOException, FileNotFoundException{
        String content = Files.readAllBytes(Paths.get(path)).toString();
        return content;
    }
    
    public static void writeFile(String path, String content) throws IOException{
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(content.getBytes());
        fos.close();
    }
}
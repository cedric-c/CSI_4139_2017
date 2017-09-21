/*
 * @author <cclem054@uottawa.ca>
 * @version 1.0
 * @since September 21, 2017
 * (c) Copyright 2017 Cédric Clément.
 */
import java.security.Signature;
import java.io.*;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class SimpleChecksum{
    private Signature signature;
    private byte[] data;
    
    
    public SimpleChecksum(File file, PrivateKey priv) throws NoSuchAlgorithmException, InvalidKeyException, FileNotFoundException, IOException, SignatureException, NoSuchProviderException{
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(priv);
        this.setSignature(signature);
        
        FileInputStream fis     = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        byte[] buffer = new byte[1024];
        int len;
        while((len = bis.read(buffer)) >= 0) {
            signature.update(buffer, 0, len);
        };
        bis.close();
        byte[] sig = signature.sign();
        this.data = sig;
    }
    
    public void setSignature(Signature signature){
        this.signature = signature;
    }
    
    
    public String toString(){
        return this.data.toString();
    }

}
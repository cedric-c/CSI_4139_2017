import java.io.*;
import javax.crypto.Cipher;
public class SimpleCrypto{
    
    /**
     * Call the Cipher object's getInstance method and pass the transformation to it.
     *
     */
    public static void main(String[] args) throws IOException{
        String filePath = SimpleIO.LAB_01_PATH + "newFile";
        SimpleIO.writeBytesToFile(filePath, "awdawd".getBytes());
        String fileContents = SimpleIO.readBytesFromFile(filePath).toString();
        System.out.println(fileContents);
        
        
    }
}
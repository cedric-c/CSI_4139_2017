import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.io.*;
import java.util.List;

public class SimpleIO{
    private static final String LAB_01_PATH = "/Users/ced/Documents/uottawa/csi/4139_17/labs/01_encryption/";
    
    public static void main(String[] args) throws IOException{
        String b64Apple = SimpleIO.toBase64("Apple");
        System.out.println(b64Apple);
        String apple = SimpleIO.fromBase64(b64Apple);
        System.out.println(apple);
        
        String fileContents = SimpleIO.readFile(LAB_01_PATH + "file");
        System.out.println(fileContents);
        
        SimpleIO.writeFile(LAB_01_PATH + "testFile", "content for test file");
        SimpleIO.writeFile(LAB_01_PATH + "testFile", "more content");
        String otherContents = SimpleIO.readFile(LAB_01_PATH + "testFile");
        System.out.println(otherContents);
        
        String message = SimpleIO.toBase64("Hello world, this is a base64 encoded message");
        SimpleIO.writeFile(LAB_01_PATH + "base64", message);
        String fileRead = SimpleIO.readFile(LAB_01_PATH + "base64");
        System.out.println("Encoded message read from encoded file: " + fileRead);

        String decodedMessage = SimpleIO.fromBase64(fileRead);
        System.out.println("Decoded message read from encoded file: " + decodedMessage);
    }

    /**
     * Encodes a string into base64 encoding.
     * @param string content The string to encode.
     * @return string The base64 encoded string.
     */
    public static String toBase64(String content){
        byte[] encoded = Base64.getEncoder().encode(content.getBytes());
        return new String(encoded);
    }
    
    /**
     * Decodes a base64 encoded string.
     * @param string content The base64 encoded string to decode.
     * @return string Decoded base64 string.
     */
    public static String fromBase64(String content){
        byte[] decoded = Base64.getDecoder().decode(content);
        return new String(decoded);
    }

    /**
     * Gets the content of a file.
     * @param string path The path of the file to read the content from.
     * @return string The content read from the file.
     */
    public static String readFile(String path) throws IOException, FileNotFoundException{
        List<String> content = Files.readAllLines(Paths.get(path));
        String ret           = String.join("\n", content);
        return ret;
    }
    
    /**
     * Writes content to a file.
     * @param string path The path of the file to write to.
     * @param string content The content to write to the file.
     * @return void
     */
    public static void writeFile(String path, String content) throws IOException{
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(content.getBytes());
        fos.close();
    }
    
    
}
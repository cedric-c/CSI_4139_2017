import java.io.*;
public class SimpleCrypto{
    
    public static void main(String[] args) throws IOException{
        String filePath = SimpleIO.LAB_01_PATH + "newFile";
        SimpleIO.writeFile(filePath, "Hello World");
        String fileContents = SimpleIO.readFile(filePath);
        System.out.println(fileContents);
    }
}
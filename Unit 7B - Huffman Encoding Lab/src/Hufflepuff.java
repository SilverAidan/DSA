import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
public class Hufflepuff {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String content = Files.readString(fileChooser.getSelectedFile().toPath());
            Huffman brian = new Huffman(content);
            while(true){
                System.out.println("\nWelcome To Huffman\n1. Encode\n2. Decode\n3. toString\n4. Exit");
                int among = new Scanner(System.in).nextInt();
                switch (among) {
                    case 1:
                        System.out.println(brian.encode());
                        break;
                    case 2:
                        System.out.println("\nEnter a string to decode: ");
                        String hello = new Scanner(System.in).next();
                        System.out.println("\n" + brian.decode(hello));
                        break;
                    case 3:
                        System.out.println("\n" + brian);
                        break;
                    case 4:
                        System.exit(0);
                    default:
                        System.exit(0);
                }
            }
        }
    }
}

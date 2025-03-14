import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class Hufflepuff {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        // Open a file chooser dialog to select a file
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            // Read the content of the selected file
            String content = Files.readString(fileChooser.getSelectedFile().toPath());
            Huffman brian = new Huffman(content);
            while(true){
                // Display menu options
                System.out.println("\nWelcome To Huffman\n1. Encode\n2. Decode\n3. toString\n4. Exit");
                int among = new Scanner(System.in).nextInt();
                switch (among) {
                    case 1:
                        // Encode the content
                        System.out.println("Encoded String: " + brian.encode());
                        break;
                    case 2:
                        // Decode a given string
                        JFileChooser decodeFileChooser = new JFileChooser(System.getProperty("user.dir"));
                        if (decodeFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                            String encodedString = Files.readString(decodeFileChooser.getSelectedFile().toPath());
                            String decodedString = brian.decode(encodedString);
                            System.out.println("Decoded String: \"" + decodedString +"\"");
                        }
                        break;
                    case 3:
                        // Print the binary map
                        System.out.println("\n" + brian);
                        break;
                    case 4:
                        // Exit the program
                        System.exit(0);
                    default:
                        // Exit the program for any other input
                        System.exit(0);
                }
            }
        }
    }
}

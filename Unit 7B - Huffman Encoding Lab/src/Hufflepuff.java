import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;
public class Hufflepuff {
    public static void main(String[] args) throws IOException {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String content = Files.readString(fileChooser.getSelectedFile().toPath());
            Huffman brian = new Huffman(content);
            System.out.println(brian);
            System.out.println(brian.encode());
            System.out.println(brian.decode("11010101111000010000111110111"));
        }
    }
}

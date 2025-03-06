import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;
public class Hufflepuff {
    public static void main(String[] args) throws IOException {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String content = Files.readString(fileChooser.getSelectedFile().toPath());
            System.out.println(content);
        }
    }
}

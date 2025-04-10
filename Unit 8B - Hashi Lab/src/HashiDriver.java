import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFileChooser;

public class HashiDriver {

    public static void main(String[] args)  throws IOException {
        char[][] puzzle = loadPuzzle();
        Hashi brian = new Hashi(puzzle);
        System.out.println(brian.getConnections());
    }

    public static char[][] loadPuzzle() throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir"))); 
        int result = chooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) 
            return null;
        File file = chooser.getSelectedFile();
        String content = Files.readString(file.toPath());
        String[] lines = content.split("\n");
        char[][] puzzle = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            puzzle[i] = lines[i].toCharArray();
        }
        return puzzle;
    }
}

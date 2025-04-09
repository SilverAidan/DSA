import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JFileChooser;

public class HashiDriver {

    public static void main(String[] args)  throws IOException {
        char[][] bruh = loadPuzzle();
        for (char[] row : bruh) {
            System.out.println(String.valueOf(row));
        }
    }

    public static char[][] loadPuzzle() throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir"))); 
        int result = chooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) return null;
        File file = chooser.getSelectedFile();
        String content = Files.readString(file.toPath());
        String[] lines = content.split("\n");
        char[][] puzzle = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            puzzle[i] = lines[i].toCharArray();
        }
        return puzzle;
    }

    public static void displayGraph(Hashi puzzle) {
        // TODO Auto-generated method stub
    }

    public static void validatePuzzle(Hashi puzzle) {
        // TODO Auto-generated method stub
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RadixDriver {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        // Create the frame for displaying the Kirby sorting panel
        JFrame frame = new JFrame("Kirby Sorter 2.0");
        JFileChooser j = new JFileChooser(new File(".\\"));
        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        // File selection loop
        File selectedFile = null;
        while (selectedFile == null || !selectedFile.getName().toLowerCase().endsWith(".csv")) {
            j.showOpenDialog(null);
            selectedFile = j.getSelectedFile();
            if (selectedFile == null || !selectedFile.getName().toLowerCase().endsWith(".csv")) {
                JOptionPane.showMessageDialog(null, "Please choose a valid CSV file.", "Invalid File", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        // MSD or LSD selection
        int MSDLSD = JOptionPane.showOptionDialog(null, "Select an option:", "Choose Option", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"MSD", "LSD"}, "MSD");

        // Read the CSV and create Piece objects
        ArrayList<Integer> arr = new ArrayList<>();
        try (Scanner s = new Scanner(selectedFile)) {
            while (s.hasNextLine()) {
                try {
                    arr.add(Integer.parseInt(s.nextLine().trim()));
                } catch (NumberFormatException ignored) {}
            }
        }
        Piece[] pieces = arr.stream().map(i -> new Piece(arr.indexOf(i), i, arr.size())).toArray(Piece[]::new);

        // Create Panel and display
        frame.add(new Panel(pieces));
        frame.setVisible(true);
    }
}
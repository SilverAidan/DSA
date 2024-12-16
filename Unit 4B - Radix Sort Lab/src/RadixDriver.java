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
        JFrame frame = new JFrame();
        JFileChooser j = new JFileChooser();
        frame.setSize(1500, 800);
        frame.setTitle("Kirby Sorter 2.0");
        frame.setLocationRelativeTo(null); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
        j.setCurrentDirectory(new File(".\\"));

        File selectedFile;
        while (true) {
            j.showOpenDialog(null);
            selectedFile = j.getSelectedFile();
            if (selectedFile != null && selectedFile.getName().toLowerCase().endsWith(".csv")) {
                break;  // Exit loop when a valid CSV is selected
            }
            // Show a dialog if the file is not a CSV
            JOptionPane.showMessageDialog(null, "Please choose a valid CSV file.", "Invalid File", JOptionPane.INFORMATION_MESSAGE);
        }

        // Read the CSV file and add the values to an ArrayList
        ArrayList<Integer> arr = new ArrayList<>();
        Scanner s = new Scanner(selectedFile);

        while (s.hasNextLine()) {
            String line = s.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                arr.add(value);  // Add valid integer values to the list
            } catch (NumberFormatException e) {
                // Ignore lines that cannot be parsed as integers
            }
        }
        s.close();

        // Create an array of Piece objects based on the parsed values
        Piece[] pieces = new Piece[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            pieces[i] = new Piece(i, arr.get(i), arr.size());  // Create each Piece based on the pink value
        }

        // Create the Panel and add it to the frame
        Panel p = new Panel(pieces);
        frame.add(p);
        frame.setVisible(true);  // Make the frame visible

        System.out.println(arr);  // Optionally print the parsed array
    }
}

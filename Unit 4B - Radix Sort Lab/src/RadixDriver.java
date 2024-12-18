import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RadixDriver {
    // Animation speed control - higher value = faster animation
    public static int slide = 15; // Initialize to 15 for default speed
    
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        // Setup main window and controls
        JFrame frame = new JFrame("Kirby Sorter 2.0");
        JFileChooser j = new JFileChooser(new File(".\\"));
        
        // Setup speed control slider (1-99)
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 99, 15);  // Set initial value to 15

        slider.setMajorTickSpacing(10); 
        slider.setMinorTickSpacing(1);  
        slider.setPaintTicks(true);     
        slider.setPaintLabels(true);   
        slider.setValue(15);  // Set initial value to 15

        frame.setSize(1500, 800);
        frame.add(slider, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        // Handle slider value changes
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                slide = Math.max(1, slider.getValue()); // Ensure never zero
            }
        });
        
        // Loop until valid CSV file is selected
        File selectedFile = null;
        while (true) {
            j.showOpenDialog(null);
            selectedFile = j.getSelectedFile();
            if (selectedFile == null) {
                System.exit(0); 
            }
            if (selectedFile.getName().toLowerCase().endsWith(".csv")) {
                break; // Valid file selected, exit the loop
            } else {
                JOptionPane.showMessageDialog(null, "Please choose a valid CSV file.", "Invalid File", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        // Get sort type from user
        int MSDLSD = JOptionPane.showOptionDialog(null, "Select an option:", "Choose Option", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"MSD", "LSD"}, "MSD");

        // Load integers from CSV
        ArrayList<Integer> arr = new ArrayList<>();
        try (Scanner s = new Scanner(selectedFile)) {
            while (s.hasNextLine()) {
                try {
                    arr.add(Integer.parseInt(s.nextLine().trim()));
                } catch (NumberFormatException ignored) {}
            }
        }

        // Initialize pieces with loaded values
        Piece[] pieces = new Piece[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            pieces[i] = new Piece(i, arr.get(i), arr.size());
        }

        // Start visualization
        Panel panel = new Panel(pieces);
        frame.add(panel);
        frame.setVisible(true);
        
        // Run selected sort
        if(MSDLSD == 0){  // Changed from 1 to 0 for MSD
            Radix.msdRadixSort(pieces, panel);
        }
        else{
            Radix.lsdRadixSort(pieces, panel);
        }
    }
}
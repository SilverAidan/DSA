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
    public static int slide;
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        // Create the frame for displaying the Kirby sorting panel
        JFrame frame = new JFrame("Kirby Sorter 2.0");
        JFileChooser j = new JFileChooser(new File(".\\"));
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);

        slider.setMajorTickSpacing(5); 
        slider.setMinorTickSpacing(1);  
        slider.setPaintTicks(true);     
        slider.setPaintLabels(true);   
        slider.setValue(100);

        frame.setSize(1500, 800);
        frame.add(slider, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        // Add ChangeListener to the slider
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                slide = 100 - slider.getValue(); // Invert the value for delay
            }
        });
        
        // File selection loop
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

        // MSD or LSD selection
        int MSDLSD = JOptionPane.showOptionDialog(null, "Select an option:", "Choose Option", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"MSD", "LSD"}, "MSD");

        ArrayList<Integer> arr = new ArrayList<>();
        try (Scanner s = new Scanner(selectedFile)) {
            while (s.hasNextLine()) {
                try {
                    arr.add(Integer.parseInt(s.nextLine().trim()));
                } catch (NumberFormatException ignored) {}
            }
        }

        // Create the Piece objects with the correct index
        Piece[] pieces = new Piece[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            pieces[i] = new Piece(i, arr.get(i), arr.size());
        }

        // Create Panel and display
        Panel panel = new Panel(pieces);
        frame.add(panel);
        frame.setVisible(true);
        if(MSDLSD == 1){
            Radix.msdRadixSort(pieces);
        }
        else{
            Radix.lsdRadixSort(pieces);
        }
    }
}

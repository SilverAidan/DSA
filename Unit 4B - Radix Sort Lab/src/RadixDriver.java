import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RadixDriver {
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame();
		Panel p = new Panel();
		JFileChooser j = new JFileChooser();
		frame.add(p);
		frame.setSize(1500, 800);
		frame.setTitle("Kirby Sorter 2.0");
		frame.setLocation(225, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setCurrentDirectory(new File(".\\"));
		while (true) {
			j.showOpenDialog(null); 
			File selectedFile = j.getSelectedFile();
			if (selectedFile.getName().toLowerCase().endsWith(".csv")) {
				break; 
			}
			JOptionPane.showMessageDialog(null, "Please choose a valid CSV file.", "Invalid File", JOptionPane.INFORMATION_MESSAGE);
		}
		File csv = j.getSelectedFile();
		frame.setVisible(true);
	}
}

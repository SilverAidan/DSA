import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Piece[] kirbies;
    private Boolean isSelected = false;
    
    // Constructor that accepts an array of Kirbies
    public Panel(Piece[] kirbies) {
        super();
        Piece.kirbyCount = kirbies.length;
        this.kirbies = kirbies;
    }
    
    // Paint the component (background, pillars, and kirbies)
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Always call the parent method first
        
        Graphics2D newG = (Graphics2D) g;
        
        // Background
        newG.setColor(new Color(101, 162, 248));
        newG.fillRect(0, 0, 1500, 800);
        newG.setColor(new Color(202, 170, 103));
        newG.fillRect(0, 675, 1500, 200);
        newG.setColor(new Color(72, 197, 44));
        newG.fillRect(0, 675, 1500, 10);
        
        // Pillars
        newG.setColor(new Color(217, 228, 128));
        newG.fillRect(50, 100, 175, 575);
        newG.fillOval(50, 25, 175, 175);
        newG.fillRect(800, 375, 100, 300);
        newG.fillOval(800, 325, 100, 100);
        newG.setColor(new Color(93, 193, 151));
        newG.fillRect(500, 375, 100, 300);
        newG.fillOval(500, 325, 100, 100);
        newG.fillRect(1200, 375, 200, 300);
        newG.fillOval(1200, 275, 200, 200);
        
        // Draw Kirbies
        for (int i = 0; i < kirbies.length; i++) {
            kirbies[i].drawKirby(newG, isSelected);
        }
    }

    // Getter for the kirbies array
    public Piece[] getKirbies() {
        return kirbies;
    }
    
    // Setter for the kirbies array
    public void setKirbies(Piece[] kirbies) {
        this.kirbies = kirbies;
    }
}

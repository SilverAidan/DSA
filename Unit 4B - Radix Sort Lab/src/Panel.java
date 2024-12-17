import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panel extends JPanel {
    private Piece[] kirbies;
    private Boolean isSelected = false;
    public static int[] currentCounts = new int[10];
    
    // Constructor that accepts an array of Kirbies
    public Panel(Piece[] kirbies) {
        super();
        Piece.kirbyCount = kirbies.length;
        this.kirbies = kirbies;
    }
    
    // Paint the component (background, pillars, and kirbies)
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
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
        
        // Draw count array state as horizontal boxes
        int boxWidth = 50;
        int boxHeight = 50;
        int totalWidth = boxWidth * 10;  // Width of all boxes combined
        int startX = (getWidth() - totalWidth) / 2;  // Center horizontally
        int startY = 200;  // Move down to center more vertically
        
        // Draw title
        newG.setColor(Color.BLACK);
        newG.drawString("Count Array State:", startX, startY - 10);
        
        // Draw boxes with counts
        for (int i = 0; i < currentCounts.length; i++) {
            int x = startX + (i * boxWidth);
            
            // Draw box
            newG.setColor(Color.WHITE);
            newG.fillRect(x, startY, boxWidth, boxHeight);
            newG.setColor(Color.BLACK);
            newG.drawRect(x, startY, boxWidth, boxHeight);
            
            // Draw index number below box
            newG.drawString(String.valueOf(i), x + (boxWidth/3), startY + boxHeight + 15);
            
            // Draw count value in box
            String count = String.valueOf(currentCounts[i]);
            int textX = x + (boxWidth - newG.getFontMetrics().stringWidth(count))/2;
            int textY = startY + (boxHeight + newG.getFontMetrics().getHeight())/2;
            newG.drawString(count, textX, textY);
        }
        
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

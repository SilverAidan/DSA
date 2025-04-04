import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panel extends JPanel {
    private Piece[] kirbies;
    public static int[] currentCounts = new int[10];
    private Piece selectedPiece = null;
    private Piece[] sortedPieces = null;
    
    // Constructor that accepts an array of Kirbies
    public Panel(Piece[] kirbies) {
        super();
        Piece.kirbyCount = kirbies.length;
        this.kirbies = kirbies;
    }
    
    // Render background, counting array, and Kirby pieces
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
            boolean isSelected = (kirbies[i] == selectedPiece);
            kirbies[i].drawKirby(newG, isSelected);
        }
        
        // Draw sorted array if it exists
        if (sortedPieces != null) {
            for (Piece p : sortedPieces) {
                p.drawKirby(newG, false);
            }
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
    
    // Calculate final x position for sorted pieces
    private int calculateFinalX(int index, int totalPieces) {
        int spacing = Math.max(100, (1400 - 100 * totalPieces) / (totalPieces + 1));
        return 50 + spacing * (index + 1) + 100 * index;
    }

    // Display final sorted arrangement
    public void drawSortedKirbies(Piece[] pieces) throws InterruptedException {
        sortedPieces = new Piece[pieces.length];
        
        // Create pieces and store exact final positions
        final int[] finalXPositions = new int[pieces.length];
        for (int i = 0; i < pieces.length; i++) {
            sortedPieces[i] = new Piece(i, pieces[i].pinkValue, pieces.length);
            sortedPieces[i].kirbyX = pieces[i].kirbyX;
            sortedPieces[i].kirbyY = pieces[i].kirbyY;
            // Revert to original spacing formula
            finalXPositions[i] = (int) ((1500 / (pieces.length + 1)) * (i + 0.5));
        }
        
        // Animate to center
        int boxWidth = 50;
        int startX = (getWidth() - (boxWidth * 10)) / 2;
        
        for (int i = 0; i < pieces.length; i++) {
            final int initialX = sortedPieces[i].kirbyX;
            final int initialY = sortedPieces[i].kirbyY;
            int targetX = startX + (sortedPieces[i].pinkValue / 10 * boxWidth);
            
            for (int step = 0; step <= 10; step++) {
                double progress = step / 10.0;
                sortedPieces[i].kirbyX = (int) (initialX + (targetX - initialX) * progress);
                sortedPieces[i].kirbyY = (int) (initialY + (200 - initialY) * progress);
                repaint();
                Thread.sleep(Math.max(1, 250/RadixDriver.slide));
            }
        }
        
        Thread.sleep(500);
        
        // Animate to final positions with proper spacing
        for (int i = 0; i < pieces.length; i++) {
            final int startingX = sortedPieces[i].kirbyX;
            final int startingY = sortedPieces[i].kirbyY;
            final int endingX = finalXPositions[i];
            
            for (int step = 0; step <= 10; step++) {
                double progress = step / 10.0;
                sortedPieces[i].kirbyX = (int) (startingX + (endingX - startingX) * progress);
                sortedPieces[i].kirbyY = (int) (startingY + (550 - startingY) * progress);
                repaint();
                Thread.sleep(Math.max(1, 250/RadixDriver.slide));
            }
            
            // Ensure final position is exact and valid
            sortedPieces[i].kirbyX = finalXPositions[i];
            sortedPieces[i].kirbyY = 550;
            repaint();
        }
    }
    
    // Animate piece movement to counting box
    public void animatePieceToBox(Piece piece, int boxIndex) throws InterruptedException {
        selectedPiece = piece;
        int boxWidth = 50;
        int startX = (getWidth() - (boxWidth * 10)) / 2;
        int targetX = startX + (boxIndex * boxWidth);
        
        // Store original position exactly as is, without validation
        final int originalX = piece.kirbyX;
        final int originalY = piece.kirbyY;
        
        // Animate to box
        for (int i = 0; i < 10; i++) {
            piece.kirbyX = originalX + ((targetX - originalX) * i / 10);
            piece.kirbyY = originalY + ((200 - originalY) * i / 10);
            repaint();
            Thread.sleep(Math.max(1, 500/RadixDriver.slide));
        }
        
        Thread.sleep(Math.max(1, 500/RadixDriver.slide));
        
        // Animate back
        for (int i = 10; i >= 0; i--) {
            piece.kirbyX = originalX + ((targetX - originalX) * i / 10);
            piece.kirbyY = originalY + ((200 - originalY) * i / 10);
            repaint();
            Thread.sleep(Math.max(1, 500/RadixDriver.slide));
        }
        
        // Return to exact original position
        piece.kirbyX = originalX;
        piece.kirbyY = originalY;
        selectedPiece = null;
        repaint();
        Thread.sleep(10);
    }
}

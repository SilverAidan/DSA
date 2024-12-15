import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class Piece {
    Color c;
    int kirbyX, kirbyY;
    public static int kirbyCount;

    public Piece(int number, int pinkValue, int kirbyCount) {
        // Define constant green and blue values (no changes)
        int idealG = 185;  // Green value stays the same
        int idealB = 220;  // Blue value stays the same

        // Adjust the red component based on the pinkValue (lower pinkValue results in less red)
        int r = (int)(250 * (pinkValue / 100.0));  // Red value adjusted by the pinkValue

        // Clamp values to be between 0 and 255
        r = Math.min(Math.max(r, 0), 255);
        int g = Math.min(Math.max(idealG, 0), 255);
        int b = Math.min(Math.max(idealB, 0), 255);

        // Set the color based on the adjusted red, green, and blue values
        this.c = new Color(r, g, b);

        // Calculate the dynamic Kirby size and spacing based on the number of Kirbys
        int availableWidth = 1500 - 80;  // Account for a small margin on the left side
        int spaceBetweenKirbys = 10;  // Space between Kirbys

        // Handle the case where kirbyCount is 1 to avoid division by zero
        int maxKirbySize = kirbyCount == 1 
            ? availableWidth  // If there's only one Kirby, it can take up the whole available width
            : (availableWidth - (kirbyCount - 1) * spaceBetweenKirbys) / kirbyCount;

        // Set Kirby size dynamically, with a minimum size of 40px
        int kirbySize = Math.max(40, maxKirbySize);

        // Update the X position of Kirby (taking into account the dynamic size and space between them)
        kirbyX = (availableWidth - kirbySize * kirbyCount - spaceBetweenKirbys * (kirbyCount - 1)) / 2 
                 + (kirbySize + spaceBetweenKirbys) * number;

        // Kirby Y position remains constant as before (adjust as needed)
        kirbyY = 600;  // Fixed Y position for Kirby (can adjust for vertical positioning)
    }

    public void drawKirby(Graphics2D newG, boolean isSelected) {
        // Shoes
        newG.setColor(new Color(255, 0, 80));
        newG.fillOval(kirbyX, 660, 40, 20);
        newG.fillOval(kirbyX + 40, 660, 40, 20);

        // Arms
        newG.setColor(c);
        if (isSelected)
            newG.fillOval(kirbyX, 600, 20, 40);
        else
            newG.fillOval(kirbyX - 5, 620, 20, 40);
        newG.fillOval(kirbyX + 65, 620, 20, 40);
        newG.setColor(c.darker());
        newG.fillOval(kirbyX - 1, 622, 20, 40);
        newG.fillOval(kirbyX + 61, 622, 20, 40);

        // Body
        newG.setColor(c);
        newG.fillOval(kirbyX, 600, 80, 80);

        // Eye shapes
        newG.setColor(Color.black);
        newG.fillOval(kirbyX + 22, 620, 10, 20);
        newG.fillOval(kirbyX + 50, 620, 10, 20);

        // Blue spots in eyes
        newG.setColor(new Color(3, 38, 168));
        newG.fillOval(kirbyX + 22, 626, 10, 13);
        newG.fillOval(kirbyX + 50, 626, 10, 13);

        // White spots in eyes
        newG.setColor(new Color(255, 255, 255));
        newG.fillOval(kirbyX + 25, 622, 5, 10);
        newG.fillOval(kirbyX + 52, 622, 5, 10);

        // Mouth
        newG.setColor(new Color(255, 0, 0));
        newG.fillOval(kirbyX + 34, 650, 15, 10);
        newG.setColor(c);
        newG.fillOval(kirbyX + 34, 643, 15, 10);

        // Pink value (showing the "pinkness" of Kirby)
        int idealR = 250; 
        int currentR = c.getRed();
        int deltaR = Math.abs(currentR - idealR);
        int pinkScore = (int)(100 - Math.min(100, deltaR * 100 / idealR)); 
        newG.setColor(Color.BLACK);
        newG.drawString("Pink: " + pinkScore, kirbyX + 20, 590);
        
        // If pink score is low, draw a golden item (like a star)
        if (pinkScore <= 30) {
            AffineTransform originalTransform = newG.getTransform();
            double angle = Math.toRadians(30); 
            int rectCenterX = kirbyX + 60 + 7;  
            int rectCenterY = 600 + 5;  
            newG.rotate(angle, rectCenterX, rectCenterY);
            newG.setColor(new Color(255, 222, 34));
            newG.fillRect(kirbyX + 60, 600, 15, 10);
            int newX = kirbyX + 60;
            int newY = 600;
            int width = 15;
            int height = 10;
            int TWidth = width / 3;
            for (int i = 0; i < 3; i++) {
                int[] xpoints = new int[] {newX, newX + TWidth / 2, newX + TWidth, newX};
                int[] ypoints = new int[] {newY, newY + height, newY, newY};
                newG.fillPolygon(new Polygon(xpoints, ypoints, xpoints.length));
                newX += width / 3;
            }

            newG.setTransform(originalTransform);
        }
    }
}

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class Piece {
    Color c;
    int kirbyX, kirbyY;
    public static int kirbyCount;

    public Piece(int number, int pinkValue) {
        int idealR = 250; // The ideal red value for perfect pink
        int idealG = 185; // The ideal green value
        int idealB = 220; // The ideal blue value
        
        // Adjust the red component based on the pinkValue (lower pinkValue results in less red)
        int r = (int)(idealR * (pinkValue / 100.0));
        
        // Keep green and blue values constant as the original perfect pink
        int g = idealG;
        int b = idealB;

        // Clamp values to be between 0 and 255
        r = Math.min(Math.max(r, 0), 255);
        g = Math.min(Math.max(g, 0), 255);
        b = Math.min(Math.max(b, 0), 255);

        // Set the color based on the adjusted red, green, and blue values
        this.c = new Color(r, g, b);
        
        // Calculate the x position for Kirby (arranging them horizontally)
        kirbyX = (1500 - 80 * kirbyCount) / (kirbyCount + 1) * (number + 1) + 80 * number;
    }

    public void drawKirby(Graphics2D newG, boolean isSelected) {
        // shoes
        newG.setColor(new Color(255, 0, 80));
        newG.fillOval(kirbyX, 660, 40, 20);
        newG.fillOval(kirbyX + 40, 660, 40, 20);
        
        // arms
        newG.setColor(c);
        if (isSelected)
            newG.fillOval(kirbyX, 600, 20, 40);
        else
            newG.fillOval(kirbyX - 5, 620, 20, 40);
        newG.fillOval(kirbyX + 65, 620, 20, 40);
        newG.setColor(c.darker());
        newG.fillOval(kirbyX - 1, 622, 20, 40);
        newG.fillOval(kirbyX + 61, 622, 20, 40);

        // body
        newG.setColor(c);
        newG.fillOval(kirbyX, 600, 80, 80);

        // eye shapes
        newG.setColor(Color.black);
        newG.fillOval(kirbyX + 22, 620, 10, 20);
        newG.fillOval(kirbyX + 50, 620, 10, 20);

        // blue spots in eyes
        newG.setColor(new Color(3, 38, 168));
        newG.fillOval(kirbyX + 22, 626, 10, 13);
        newG.fillOval(kirbyX + 50, 626, 10, 13);

        // white spots in eyes
        newG.setColor(new Color(255, 255, 255));
        newG.fillOval(kirbyX + 25, 622, 5, 10);
        newG.fillOval(kirbyX + 52, 622, 5, 10);

        // mouth
        newG.setColor(new Color(255, 0, 0));
        newG.fillOval(kirbyX + 34, 650, 15, 10);
        newG.setColor(c);
        newG.fillOval(kirbyX + 34, 643, 15, 10);

		//Pink value (chatgpt idea)
        int idealR = 250; 
        int currentR = c.getRed();
        int deltaR = Math.abs(currentR - idealR);
        int pinkScore = (int)(100 - Math.min(100, deltaR * 100 / idealR)); 
        newG.setColor(Color.BLACK);
        newG.drawString("Pink: " + pinkScore, kirbyX + 20, 590);
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
			int THeight = 20;
			for (int i = 0; i < 3; i++) {

			int[] xpoints = new int[] {newX, newX + TWidth / 2, newX + TWidth, newX};
			int[] ypoints = new int[] {newY, newY + height, newY, newY};
				newG.fillPolygon(new Polygon(xpoints, ypoints, xpoints.length));
				newX += width / 3;
			}


			newG.setTransform(originalTransform);
		}
		
    }

    public Color getC() {
        return c;
    }

    public void setC(Color c) {
        this.c = c;
    }
}

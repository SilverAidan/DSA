import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Piece {
    Color c;
    int kirbyX, kirbyY;
    public static int kirbyCount;

    public Piece(int number, int pinkValue, int kirbyCount) {
        int idealR = 250; // The ideal red value for perfect pink
        
        int r = (int)(idealR * (pinkValue / 100.0));
        int g = 185;
        int b = 220;
        r = Math.min(Math.max(r, 0), 255);
        g = Math.min(Math.max(g, 0), 255);
        b = Math.min(Math.max(b, 0), 255);

        this.c = new Color(r, g, b);
        kirbyX = (int) ((1500 / (kirbyCount + 1)) * (number + 0.5));
        kirbyY = (int) ((1.76 * kirbyCount) + 566.94);
    }
    

    public void drawKirby(Graphics2D GG, boolean isSelected) {
        BufferedImage buffy = new BufferedImage(100, 110, BufferedImage.TYPE_INT_ARGB);
        Graphics newGG = buffy.getGraphics();
        Graphics2D newG = (Graphics2D)newGG;
        int tempKX = kirbyX;
        int tempKY = kirbyY;
        
        kirbyX = 0;
        kirbyY = 10;
        // Shoes
        newG.setColor(new Color(255, 0, 80));
        newG.fillOval(kirbyX, kirbyY + 60, 40, 20);
        newG.fillOval(kirbyX + 40, kirbyY + 60, 40, 20);
    
        // Arms
        newG.setColor(c);
        if (isSelected)
            newG.fillOval(kirbyX, kirbyY, 20, 40);
        else
            newG.fillOval(kirbyX - 5, kirbyY + 20, 20, 40);
        newG.fillOval(kirbyX + 65, kirbyY + 20, 20, 40);
        newG.setColor(c.darker());
        newG.fillOval(kirbyX - 1, kirbyY + 22, 20, 40);
        newG.fillOval(kirbyX + 61, kirbyY + 22, 20, 40);
    
        // Body
        newG.setColor(c);
        newG.fillOval(kirbyX, kirbyY, 80, 80);
    
        // Eye shapes
        newG.setColor(Color.black);
        newG.fillOval(kirbyX + 22, kirbyY + 20, 10, 20);
        newG.fillOval(kirbyX + 50, kirbyY + 20, 10, 20);
    
        // Blue spots in eyes
        newG.setColor(new Color(3, 38, 168));
        newG.fillOval(kirbyX + 22, kirbyY + 26, 10, 13);
        newG.fillOval(kirbyX + 50, kirbyY + 26, 10, 13);
    
        // White spots in eyes
        newG.setColor(new Color(255, 255, 255));
        newG.fillOval(kirbyX + 25, kirbyY + 22, 5, 10);
        newG.fillOval(kirbyX + 52, kirbyY + 22, 5, 10);
    
        // Mouth
        newG.setColor(new Color(255, 0, 0));
        newG.fillOval(kirbyX + 34, kirbyY + 50, 15, 10);
        newG.setColor(c);
        newG.fillOval(kirbyX + 34, kirbyY + 43, 15, 10);
    
        // Pink value (showing the "pinkness" of Kirby)
        int idealR = 250;
        int currentR = c.getRed();
        int deltaR = Math.abs(currentR - idealR);
        int pinkScore = (int) (100 - Math.min(100, deltaR * 100 / idealR));
        newG.setColor(Color.BLACK);

        // Dynamically position the text slightly to the left
        String pinkText = "Pink: " + pinkScore;
        int textWidth = newG.getFontMetrics().stringWidth(pinkText);
        int offset = 10; // Offset to move the text to the left
        int textX = (buffy.getWidth() - textWidth) / 2 - offset;
        int textY = 10; // Position near the top of the BufferedImage
        newG.drawString(pinkText, textX, textY);

        // If pink score is less than thirty, draw a fake crown
        if (pinkScore <= 30) {
            AffineTransform originalTransform = newG.getTransform();
            double angle = Math.toRadians(30);
            int rectCenterX = kirbyX + 60 + 7;
            int rectCenterY = kirbyY + 5;
            newG.rotate(angle, rectCenterX, rectCenterY);
            newG.setColor(new Color(255, 222, 34));
            newG.fillRect(kirbyX + 60, kirbyY, 15, 10);
    
            newG.setTransform(originalTransform);
        }
        buffy = scaleBufImg(buffy, (-0.01875 * kirbyCount) + 1.1875);
        //buffy = scaleBufImg(buffy, .25);
        kirbyX = tempKX;
        kirbyY = tempKY;
        
        System.out.println(kirbyX + ", " + kirbyY + ", " + buffy.getWidth() + ", " + buffy.getHeight());
        GG.drawImage(buffy, kirbyX, kirbyY, buffy.getWidth(), buffy.getHeight(), null);
    }
    
    //Farhan code
    public BufferedImage scaleBufImg(BufferedImage b, double scaleFactor) {
        int w = b.getWidth();
        int h = b.getHeight();
        BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(scaleFactor, scaleFactor);
        AffineTransformOp scaleOp = 
           new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(b, after);
        return after;
    }
}

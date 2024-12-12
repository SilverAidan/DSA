import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Piece[] kirbies;
	private Boolean isSelected = false;
	
	public Panel() {
        Piece.kirbyCount = 10;
        kirbies = new Piece[10];
        for (int i = 0; i < kirbies.length; i++) {
            int pinkValue = (int)(Math.random() * 101); // Random pink value between 0 and 100
            kirbies[i] = new Piece(i, pinkValue); 
        }
    }
    
	
	public Panel(Piece[] kirbies, int width, int height) {
		super();
		Piece.kirbyCount = kirbies.length;
		this.kirbies = kirbies;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D newG = (Graphics2D)g;
		
		//background
		newG.setColor(new Color(101,162,248));
		newG.fillRect(0, 0, 1500, 800);
		newG.setColor(new Color(202,170,103));
		newG.fillRect(0, 675, 1500, 200);
		newG.setColor(new Color(72,197,44));
		newG.fillRect(0, 675, 1500, 10);
		
		//pillars
		newG.setColor(new Color(217,228,128));
		newG.fillRect(50, 100, 175, 575);
		newG.fillOval(50, 25, 175, 175);
		newG.fillRect(800, 375, 100, 300);
		newG.fillOval(800, 325, 100, 100);
		newG.setColor(new Color(93,193,151));
		newG.fillRect(500, 375, 100, 300);
		newG.fillOval(500, 325, 100, 100);
		newG.fillRect(1200, 375, 200, 300);
		newG.fillOval(1200, 275, 200, 200);
		
		for(int i = 0; i < kirbies.length; i++) {
			kirbies[i].drawKirby(newG, isSelected);
		}	
	}

	public Piece[] getkirbies() {
		return kirbies;
	}
	
	public void setkirbies(Piece[] kirbies) {
		this.kirbies = kirbies;
	}
}

package arkanoid;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Block extends Rectangle {
	
	Image pic;
	
	boolean destroyed = false;
	
	// movement vars
	int dx = 3;
	int dy = -3;
	
	// side rectangles to recognize from which site the ball hit the block
	Rectangle leftSideRect, rightSideRect;

	// if the ball will drop powerup reward when hit
	boolean powerup = false;
	
	public Block(int a, int b, int w, int h, String imageFileName) {
		x = a;
		y = b;
		width = w;
		height = h;
		
		leftSideRect = new Rectangle(a-1, b, 1, h);
		rightSideRect = new Rectangle(a+w+1, b, 1, h);
		
		pic = Toolkit.getDefaultToolkit().getImage(imageFileName);
	}
	
	public void draw(Graphics g, Component c) {
		// draw the block pic to the given component if not destroyed
		if(!destroyed) {
		g.drawImage(pic, x, y, width, height, c);
		}
	}
}

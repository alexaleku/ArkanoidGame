package arkanoid;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;


public class BlockBreackerPanel extends JPanel implements KeyListener {

	ArrayList<Block> blocks = new ArrayList<>();
	ArrayList<Block> balls = new ArrayList<>();
	ArrayList<Block> powerups = new ArrayList<>();
	
	Block paddle;
	
	Thread thread;
	Animator animator;
	
	int ballSize = 25;

	BlockBreackerPanel() {
		paddle = new Block(175, 480, 150, 25, "paddle.png");

		for (int i = 0; i < 8; i++) {
			blocks.add(new Block((i * 60 + 2), 0, 60, 25, "blue.png"));
		}
		for (int i = 0; i < 8; i++) {
			blocks.add(new Block((i * 60 + 2), 25, 60, 25, "red.png"));
		}
		for (int i = 0; i < 8; i++) {
			blocks.add(new Block((i * 60 + 2), 50, 60, 25, "green.png"));
		}
		for (int i = 0; i < 8; i++) {
			blocks.add(new Block((i * 60 + 2), 75, 60, 25, "yellow.png"));
		}
		
		Random random = new Random();
		
		// add powerups to some bricks
		for (int i = 0; i < 8; i++) {
			blocks.get(random.nextInt(32)).powerup = true;
		}
		
		balls.add(new Block(237, 437, 25, 25, "ball.png"));

		addKeyListener(this);
		setFocusable(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		for (Block bl : blocks) {
			bl.draw(g, this);
		}
		for (Block ball : balls) {
			ball.draw(g, this);
		}
		for (Block p : powerups) {
			p.draw(g, this);
		}
		paddle.draw(g, this);
	}

	public void update() {
		for (Block p : powerups) {
			p.y+=1;
			if (p.intersects(paddle) && !p.destroyed) {
				p.destroyed = true;
				balls.add(new Block(paddle.dx+75, 437, 25, 25, "ball.png"));
			}
		}
		for (Block ball : balls) {
			
			ball.x += ball.dx;
			// check if ball tauched the field LEFT or RIGHT boundary then change X moving direction
			if (ball.x > (getWidth() - ballSize) && ball.dx > 0 || ball.x < 0)
				ball.dx *= -1;
			
			ball.y += ball.dy;
			// check if the ball tauched upper boundary or paddle then change Y direction
			if (ball.y < 0 || ball.intersects(paddle))
				ball.dy *= -1;
			
			// cheeck if ball tauched any block
			for (Block bl : blocks) {
				
				// if ball tauched right or left block section
				if ((bl.rightSideRect.intersects(ball) || bl.leftSideRect
						.intersects(ball)) && !bl.destroyed) {
					ball.dx *= -1;
					bl.destroyed = true;
					// if block have powerup then throw it
					if (bl.powerup) {
						powerups.add(new Block(bl.x, bl.y, 25, 19, "extra.png"));
					}
				}
				// if the ball tauched other space on block (upper side or bottom)
				else if (ball.intersects(bl) && !bl.destroyed) {
					bl.destroyed = true;
					ball.dy *= -1;
					// if block have powerup then throw it
					if (bl.powerup) {
						powerups.add(new Block(bl.x, bl.y, 25, 19, "extra.png"));
					}
				}
			
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			animator = new Animator(this);
			thread = new Thread(animator);
			thread.start();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
			paddle.x -= 25;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT
				&& paddle.x < (getWidth() - paddle.width)) {
			paddle.x += 25;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}

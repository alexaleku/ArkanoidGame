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
	private boolean gameStarted = false;

	
	BlockBreackerPanel() {
		
		// setting of initial positions of GUI elements and a brick map		
		
		// create paddle object
		paddle = new Paddle(175, 480);

		// add brics 4 rows each have 8 bricks (should be consistent with frame width)
		for (int i = 0; i < 100; i += 25) {
			for (int j = 0; j < 8; j++) {
				blocks.add(new Brick((j * 60 + 2), i));
			}
		}

		Random random = new Random();

		// add powerups "true" to some bricks
		for (int i = 0; i < 8; i++) {
			blocks.get(random.nextInt(32)).powerup = true;
		}

		// create a ball
		balls.add(new Ball(237, 437));

		// next two is needed to set keylisteners to the panel
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
			p.y += 1;
			if (p.intersects(paddle) && !p.destroyed) {
				p.destroyed = true;
				balls.add(new Ball(paddle.dx + 75, 437));
			}
		}
		for (Block ball : balls) {

			ball.x += ball.dx;
			// check if ball tauched the field LEFT or RIGHT boundary then
			// change X moving direction
			if (ball.x > (getWidth() - Ball.getBallWidth()) && ball.dx > 0 || ball.x < 0)
				ball.dx *= -1;

			ball.y += ball.dy;
			// check if the ball tauched upper boundary or paddle then change Y
			// direction
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
					addPowerup(bl);
				}
				// if the ball tauched other space on block (upper side or
				// bottom)
				else if (ball.intersects(bl) && !bl.destroyed) {
					bl.destroyed = true;
					ball.dy *= -1;
					addPowerup(bl);
				}

			}
		}
	}

	private void addPowerup(Block bl) {
		if (bl.powerup) {
			powerups.add(new Powerup(bl.x, bl.y));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (gameStarted) {
// put logic to stop the game
			} else {
				animator = new Animator(this);
				thread = new Thread(animator);
				thread.start();
				gameStarted = true;
			}
			
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

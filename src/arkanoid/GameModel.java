package arkanoid;

import java.util.ArrayList;
import java.util.Random;

public class GameModel {
	
	ArrayList<Block> bricks = new ArrayList<>();
	ArrayList<Block> balls = new ArrayList<>();
	ArrayList<Block> powerups = new ArrayList<>();

	Block paddle;

	GameModel gameModel;
	private int playFieldWidth;
	private int playFieldHeight;
	
	public GameModel(int playFieldWidth, int playFieldHeight) {
		this.playFieldWidth = playFieldWidth;
		this.playFieldHeight = playFieldHeight;
		// setting of initial positions of GUI elements and a brick map		
		
				// create paddle object
				paddle = new Paddle(175, 480);

				// add brics 4 rows each have 8 bricks (should be consistent with frame width)
				for (int i = 0; i < 100; i += 25) {
					for (int j = 0; j < 8; j++) {
						bricks.add(new Brick((j * 60 + 2), i));
					}
				}

				Random random = new Random();

				// add powerups "true" to some bricks
				for (int i = 0; i < 8; i++) {
					bricks.get(random.nextInt(32)).powerup = true;
				}

				// create a ball
				balls.add(new Ball(237, 437));
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
			if (ball.x > (playFieldWidth - Ball.getBallWidth()) && ball.dx > 0 || ball.x < 0)
				ball.dx *= -1;

			ball.y += ball.dy;
			// check if the ball tauched upper boundary or paddle then change Y
			// direction
			if (ball.y < 0 || ball.intersects(paddle))
				ball.dy *= -1;

			// cheeck if ball tauched any block
			for (Block bl : bricks) {

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
	
	public void movePaddleLeft() {
		if (paddle.x > 0) {
			paddle.x -= 25;
		}
		
	}

	public void movePaddleRight() {
		if (paddle.x < (playFieldWidth - paddle.width)) {
			paddle.x += 25;
		}
	}
	
	public ArrayList<Block> getBricks() {
		return bricks;
	}
	public ArrayList<Block> getBalls() {
		return balls;
	}
	public ArrayList<Block> getPowerups() {
		return powerups;
	}
	public Block getPaddle() {
		return paddle;
	}

}

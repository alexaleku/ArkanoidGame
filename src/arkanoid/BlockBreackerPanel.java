package arkanoid;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class BlockBreackerPanel extends JPanel implements KeyListener {

	private boolean gameStarted = false;
	private GameModel gameModel;

	Thread thread;
	Animator animator;
	
	BlockBreackerPanel(GameModel gameModel) {
		this.gameModel = gameModel;
		
		// next two is needed to set keylisteners to the panel
		addKeyListener(this);
		setFocusable(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		for (Block bl : gameModel.getBricks()) {
			bl.draw(g, this);
		}
		for (Block ball : gameModel.getBalls()) {
			ball.draw(g, this);
		}
		for (Block p : gameModel.getPowerups()) {
			p.draw(g, this);
		}
		gameModel.getPaddle().draw(g, this);
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
				animator = new Animator(this, gameModel);
				thread = new Thread(animator);
				thread.start();
				gameStarted = true;
			}
			
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gameModel.movePaddleLeft();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gameModel.movePaddleRight();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}

package arkanoid;


public class Animator implements Runnable {
	
	BlockBreackerPanel bp;
	GameModel gm;
	
	Animator(BlockBreackerPanel bp, GameModel gm) {
		this.bp = bp;
		this.gm = gm;
	}
	
	@Override
	public void run() {
		while(true) {
			gm.update();
			bp.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

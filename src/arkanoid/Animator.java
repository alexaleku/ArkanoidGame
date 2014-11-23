package arkanoid;


public class Animator implements Runnable {
	
	BlockBreackerPanel bp;
	
	Animator(BlockBreackerPanel bp) {
		this.bp = bp;
	}
	
	@Override
	public void run() {
		while(true) {
			bp.update();
			bp.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

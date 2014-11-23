package arkanoid;
import javax.swing.JFrame;


public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Arkanoid");
		
		int playFieldWidth = 490;
		int playFieldHeight = 600;
		
		GameModel gameModel = new GameModel(playFieldWidth, playFieldHeight);
		BlockBreackerPanel panel = new BlockBreackerPanel(gameModel);
		
		frame.getContentPane().add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.setSize(playFieldWidth, playFieldHeight);
		frame.setResizable(false);
		
	}
	
}

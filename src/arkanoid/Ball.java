package arkanoid;

public class Ball extends Block{
	
	private static int w = 25;
	private static int h = 25;
	private static String imageFileName = "ball.png";

	public Ball(int a, int b) {
		super(a, b, w, h, imageFileName);
	}

	public static int getBallWidth() {
		return w;
	}
	
	

}

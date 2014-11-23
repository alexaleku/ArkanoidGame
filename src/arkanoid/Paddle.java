package arkanoid;

public class Paddle extends Block{
	private static int w = 150;
	private static int h = 25;
	private static String imageFileName = "paddle.png";

	public Paddle(int a, int b) {
		super(a, b, w, h, imageFileName);
	}
}

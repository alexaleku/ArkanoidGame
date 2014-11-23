package arkanoid;

public class Powerup extends Block{
	private static int w = 25;
	private static int h = 19;
	private static String imageFileName = "extra.png";

	public Powerup(int a, int b) {
		super(a, b, w, h, imageFileName);
	}
}

package arkanoid;

import java.util.Random;

public class Brick extends Block{
	private static int w = 60;
	private static int h = 25;
	private static String[] imageFileName = {"blue.png", "red.png", "green.png", "yellow.png"};

	public Brick(int a, int b) {
		super(a, b, w, h, imageFileName[new Random().nextInt(4)]);
	}
}

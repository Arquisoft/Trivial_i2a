

import java.awt.Point;

public class Representation extends Bitmap {


	public Representation(int width, int height) {
		super(width, height);
	}

	public void render(Point mouse, Game game) {
		for (int y = 0; y < 768; y++) {
			for (int x = 0; x < 1024; x++) {
				String colour = Art.getColour(Art.click.pixels[x + y * 1024]);
				pixels[x + y * 1024] = Art.skin.pixels[x + y * 1024];
				if (game.getBoard().getBoxes().get(colour) != null) {
					if (game.getBoard().getBoxes()
							.get(colour)
							.getPossibleMove()) {
						pixels[x + y * 1024] = 000000;
					}
				}
			}
		}
	}
}



import java.awt.Point;

import logic.Game;

public class Screen extends Bitmap {
	public Representation viewport;
	
	public Screen(int width, int height) {
		super(width, height);
		viewport = new Representation(width,height);
	}

	public void render(Point mouse, Game game) {
		viewport.render(mouse,game);
		draw(viewport,0,0);
	}
}

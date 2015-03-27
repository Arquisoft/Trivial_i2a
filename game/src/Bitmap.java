

public class Bitmap {
	public final int width;
	public final int height;
	public final int[] pixels;

	public Bitmap(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void draw(Bitmap bitmap, int xOffs, int yOffs) {
		for (int y = 0; y < bitmap.height; y++) {
			int yPix = y + yOffs;
			if (yPix < 0 || yPix >= height)
				continue;
			for (int x = 0; x < bitmap.width; x++) {
				int xPix = x + xOffs;
				if (xPix < 0 || xPix >= width)
					continue;

				int src = bitmap.pixels[x + y * bitmap.width];
				pixels[xPix + yPix * width] = src;

			}

		}
	}

	public int[] getPixels() {
		return pixels;
	}
}



import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Art {
	public static Bitmap skin = loadBitmap("/res/tex/skin.png");
	public static Bitmap click = loadBitmap("/res/tex/click.png");
	
	public static Bitmap loadBitmap(String fileName) {
		try {
			BufferedImage img = ImageIO.read(Art.class.getResource(fileName));
			
			int w = img.getWidth();
			int h = img.getHeight();
			
			Bitmap result = new Bitmap(w , h);
			img.getRGB(0, 0, w, h, result.pixels, 0, w);
			return result;
		} catch (Exception e) {
			throw new RuntimeException( e );
		}
	}
	/**
	private static String getCol(int c) {
		String hex = Integer.toHexString(c & 0xffffff);
		return hex;
	}
	*/
	
	public static String getColour(int c)
	{
		String value = Integer.toHexString(c & 0xffffff);
		if(value.length()<6){
  		  value = "00"+value;
  	  }
  	  value = value.toUpperCase();
  	  return value;
	}
}

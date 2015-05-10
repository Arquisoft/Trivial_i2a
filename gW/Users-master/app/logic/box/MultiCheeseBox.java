package box;

import java.lang.reflect.Constructor;
import java.util.Random;
import game.Game;

public class MultiCheeseBox extends AbstractBox {

	private Random rand = new Random();
	private Box box;
	private Class<?>[] boxes = {BlueBox.class,
								GreenBox.class,
								RedBox.class,
								YellowBox.class
							   };
	
	@Override
	public boolean execute (Game game) {
		Class<?> x = boxes[rand.nextInt(boxes.length)];
		Constructor<?> ctor;
		try {
			ctor = x.getConstructor();
			this.box = (Box) ctor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.box.execute(game);
	}

@Override
	public String getClassHTML(){
	    return "centerBox";
	}
}

package box;

public class MultiCheeseBox extends AbstractBox {
	
	private Box box;
	
	@Override
	public boolean execute () {
		//TODO: Auto-generated method stub
		//this.box = randomBox();
		
		return this.box.execute();
	}

}

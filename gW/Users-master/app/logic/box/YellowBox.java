package box;



public class YellowBox extends AbstractBox {
	
	
	@Override
	public boolean execute () {
		return false;
	}

    @Override
	public String getClassHTML(){
	    return "yellowBox";
	}
}

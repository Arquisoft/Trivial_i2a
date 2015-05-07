package box;

public class ThrowAgainBox extends AbstractBox {
	
	@Override
	public boolean execute () {
		return true;
	}
    
    @Override
	public String getClassHTML(){
	    return "redBox";
	}
}

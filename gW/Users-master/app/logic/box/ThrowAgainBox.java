package box;
import game.Game;
public class ThrowAgainBox extends AbstractBox {
	
	@Override
	public boolean execute (Game game) {
		return true;
	}
    
    @Override
	public String getClassHTML(){
	    return "throwAgainBox";
	}
}

package box;

import cheese.Cheese;
import game.Game;

public interface Box {
	
	public int getRow();
	public int getColumn();
	public void setRow(int row);
	public void setColumn(int column);
	
	public Cheese getCheese();
	public void setCheese(Cheese cheese);
	
	public boolean execute(Game game); //true if the player plays again

	public void setPossibleMove(boolean b);
	public boolean isPossibleMove();
	
	public void setMovable(boolean b);
	public boolean isMovable();
	
	public String getClassHTML();
}

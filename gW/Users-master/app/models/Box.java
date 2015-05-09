
import cheese.Cheese;
import javax.persistence.*;
import game.Game;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.Required;

public interface Box{
	
	public int getRow();
	public int getColumn();
	public void setRow(int row);
	public void setColumn(int column);
	
	
	public boolean execute(); //true if the player plays again

	public void setPossibleMove(boolean b);
}

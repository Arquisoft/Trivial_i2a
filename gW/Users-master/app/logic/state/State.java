package state;

import box.Box;
import game.Game;

public interface State {

	//Returns the next state
	public boolean onGamePressed(Game game, Box boxPressed);
}

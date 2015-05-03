package state;

import box.Box;
import game.Game;

public interface State {

	//Returns the next state
	public State onGamePressed(Game game, Box boxPressed);
}

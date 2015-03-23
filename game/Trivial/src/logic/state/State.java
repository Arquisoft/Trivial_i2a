package logic.state;

import logic.Box;
import logic.Game;

public interface State {

	//Returns the next state
	public State onGamePressed(Game game, Box boxPressed);
}

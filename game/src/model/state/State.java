package model.state;

import model.Box;
import model.Game;

public interface State {

	//Returns the next state
	public State onGamePressed(Game game, Box boxPressed);
}

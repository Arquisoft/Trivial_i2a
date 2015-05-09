package model.state;

import model.Box;
import model.Game;

public class AnsweringQuestionsState implements State {

	@Override
	public State onGamePressed(Game game, Box boxPressed) {
		return this;
	}

}

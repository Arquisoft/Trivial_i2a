package logic.state;

import logic.Box;
import logic.Game;

public class AnsweringQuestionsState implements State {

	@Override
	public State onGamePressed(Game game, Box boxPressed) {
		return this;
	}

}

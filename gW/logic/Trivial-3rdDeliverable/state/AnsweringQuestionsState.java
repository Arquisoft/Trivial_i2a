package state;

import box.Box;
import game.Game;

public class AnsweringQuestionsState implements State {

	@Override
	public State onGamePressed(Game game, Box boxPressed) {
		return this;
	}

}

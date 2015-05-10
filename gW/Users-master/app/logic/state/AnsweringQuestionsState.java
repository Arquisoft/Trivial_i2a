package state;

import box.Box;
import game.Game;

public class AnsweringQuestionsState implements State {

	@Override
	public boolean onGamePressed(Game game, Box boxPressed) {
		return true;
	}

}

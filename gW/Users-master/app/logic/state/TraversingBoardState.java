package state;

import game.Game;
import box.Box;

public class TraversingBoardState implements State {

	@Override
	public boolean onGamePressed(Game game, Box boxPressed) {
		
				game.throwDice();
				game.getBoard().getActualPlayer().setActualBox(boxPressed);
			
				/*Question question = game.getRandomQuestion(game.getBoard()
						.getActualPlayer().getActualBox());*/
				return boxPressed.execute(game);
	}

}

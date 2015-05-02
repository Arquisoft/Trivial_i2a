package state;

import game.Game;
import box.Box;

public class TraversingBoardState implements State {

	@Override
	public State onGamePressed(Game game, Box boxPressed) {
		if(game.getBoard().getMoves(game.getBoard().getActualPlayer().getActualBox(),
				game.getDiceValue()).contains(boxPressed))
			{
				game.throwDice();
				game.getBoard().getActualPlayer().setActualBox(boxPressed);
			
				/*Question question = game.getRandomQuestion(game.getBoard()
						.getActualPlayer().getActualBox());*/
				boxPressed.execute();
		}
		
		return this;
	}

}

package state;

import question.Question;
import box.Box;
import game.Game;

public class TraversingBoardState implements State {

	@Override
	public State onGamePressed(Game game, Box boxPressed) {
		if(game.getBoard().getMoves(game.getBoard().getActualPlayer().getActualBox(), game.getDiceValue())
				.contains(boxPressed))
					{
			game.setDiceValue((int) (Math.random()*6+1));
			game.getBoard().getActualPlayer().setActualBox(boxPressed);
			
			Question question = game.getRandomQuestion(game.getBoard()
					.getActualPlayer().getActualBox());
			
			question.answer(game);

					}
		
		return this;
	}

}

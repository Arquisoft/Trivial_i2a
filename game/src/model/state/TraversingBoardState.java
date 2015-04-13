package model.state;

import questions.Question;
import model.Box;
import model.Game;

public class TraversingBoardState implements State {

	@Override
	public State onGamePressed(Game game, Box boxPressed) {
		if(game.getBoard().getMoves(game.getBoard().getActualPlayer().getActualBox(), game.getDiceValue())
				.contains(boxPressed))
					{
			game.getBoard().getActualPlayer().setActualBox(boxPressed);
			
			Question question = game.getRandomQuestion();
			
			question.answer(game);

					}
		
		return this;
	}

}

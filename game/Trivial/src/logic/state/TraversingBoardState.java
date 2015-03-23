package logic.state;

import logic.Box;
import logic.Game;
import logic.Question;

public class TraversingBoardState implements State {

	@Override
	public State onGamePressed(Game game, Box boxPressed) {
		if(game.getBoard().getMoves(game.getBoard().getActualPlayer().getActualBox(), game.getDiceValue())
				.contains(boxPressed))
					{
			game.getBoard().getActualPlayer().setActualBox(boxPressed);
			
			Question question = game.getBoard().getActualPlayer().getActualBox().getQuestion();
			
			question.answer();
			
			if(question.isCorrectlyAnswered())
				return this;
			else
				game.nextTurn();
		
			
					}
		
		return this;
	}

}

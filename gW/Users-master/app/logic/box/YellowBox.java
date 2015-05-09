package box;

import question.HistoryQuestion;
import question.QuestionFactory;
import game.Game;

public class YellowBox extends AbstractBox {
	
	private HistoryQuestion question = new HistoryQuestion();
	
	@Override
	public boolean execute (Game game) {
		this.question = (HistoryQuestion) QuestionFactory
				.getRandomQuestion(question.getClass());
				setActualQuestion(question, game);
		return false;
	}

    @Override
	public String getClassHTML(){
	    return "yellowBox";
	}
}

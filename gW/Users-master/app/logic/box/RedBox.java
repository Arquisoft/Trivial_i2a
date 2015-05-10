package box;

import question.EntertainmentQuestion;
import question.QuestionFactory;
import game.Game;

public class RedBox extends AbstractBox {
	
	private EntertainmentQuestion question = new EntertainmentQuestion();
	
	@Override
	public boolean execute (Game game) {
		this.question = (EntertainmentQuestion) QuestionFactory
				.getRandomQuestion(question.getClass());
		setActualQuestion(question, game);
		return false;
	}

    @Override
	public String getClassHTML(){
	    if(getCheese()!=null)
	        return "redBoxCheese";
	    return "redBox";
	}
}

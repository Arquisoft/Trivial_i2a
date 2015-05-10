package box;

import question.QuestionFactory;
import question.ScienceQuestion;
import game.Game;

public class GreenBox extends AbstractBox {
	
	private ScienceQuestion question = new ScienceQuestion();
	
	@Override
	public boolean execute (Game game) {
		this.question = (ScienceQuestion) QuestionFactory
				.getRandomQuestion(question.getClass());
		setActualQuestion(question, game);
		return false;
	}

	
	@Override
	public String getClassHTML(){
	    if(getCheese()!=null)
	        return "greenBoxCheese";
	    return "greenBox";
	}

}

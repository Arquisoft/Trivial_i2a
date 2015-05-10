package box;

import question.GeographyQuestion;
import question.QuestionFactory;
import game.Game;

public class BlueBox extends AbstractBox {
	
	
	private GeographyQuestion question = new GeographyQuestion();
	
	@Override
	public boolean execute (Game game) {
		this.question = (GeographyQuestion) QuestionFactory
				.getRandomQuestion(question.getClass());
		//TODO deploy a dialog to answer the question
		setActualQuestion(question, game);
		return true;
	}
	
	@Override
	public String getClassHTML(){
	    if(getCheese()!=null)
	        return "blueBoxCheese";
	    return "blueBox";
	}

}

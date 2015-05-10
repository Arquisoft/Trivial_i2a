package box;

import question.GeographyQuestion;
import question.QuestionFactory;

public class BlueBox extends AbstractBox {
	
	private GeographyQuestion question = new GeographyQuestion();
	
	@Override
	public boolean execute () {
		this.question = (GeographyQuestion) QuestionFactory
				.getRandomQuestion(question.getClass());
		//TODO deploy a dialog to answer the question
		return true;
	}

}

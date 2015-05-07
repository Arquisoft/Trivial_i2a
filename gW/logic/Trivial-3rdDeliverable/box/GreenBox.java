package box;

import question.QuestionFactory;
import question.ScienceQuestion;

public class GreenBox extends AbstractBox {
	
	private ScienceQuestion question = new ScienceQuestion();
	
	@Override
	public boolean execute () {
		this.question = (ScienceQuestion) QuestionFactory
				.getRandomQuestion(question.getClass());
		return false;
	}

}

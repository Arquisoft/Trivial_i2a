package box;

import question.EntertainmentQuestion;
import question.QuestionFactory;

public class RedBox extends AbstractBox {
	
	private EntertainmentQuestion question = new EntertainmentQuestion();
	
	@Override
	public boolean execute () {
		this.question = (EntertainmentQuestion) QuestionFactory
				.getRandomQuestion(question.getClass());
		return false;
	}

}

package box;

import question.HistoryQuestion;
import question.QuestionFactory;

public class YellowBox extends AbstractBox {
	
	private HistoryQuestion question = new HistoryQuestion();
	
	@Override
	public boolean execute () {
		this.question = (HistoryQuestion) QuestionFactory
				.getRandomQuestion(question.getClass());
		return false;
	}

}

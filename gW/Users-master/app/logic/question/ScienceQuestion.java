package question;

import java.util.Map;

import question.AbstractQuestion;

public class ScienceQuestion extends AbstractQuestion {

	public ScienceQuestion(String question, Map<String, Boolean> answers) {
		super(question, answers, "science");
	}
	
	public ScienceQuestion () {}

}
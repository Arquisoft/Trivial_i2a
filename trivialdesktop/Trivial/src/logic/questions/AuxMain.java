package logic.questions;

import java.io.File;
import java.util.List;

public class AuxMain {
	
	public static void main (String [] args) {
		List<Question> questions = QuestionFactory.createQuestions(new File("output.json"));
	}

}

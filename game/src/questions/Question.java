package questions;

import java.util.Map;

import model.Game;

public interface Question {

	Map<String, Boolean> getAnswers();

	String getRightAnswer();

	String getQuestion();  

	void answer(Game game);

}

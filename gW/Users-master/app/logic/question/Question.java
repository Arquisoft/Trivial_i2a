package question;

import java.util.Map;

import game.Game;

public interface Question {

	Map<String, Boolean> getAnswers();

	String getRightAnswer();

	String getQuestion();  

	void answer(Game game);

    public boolean checkAnswer (String ans);
}

package question;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
 
import game.Game;

public class AbstractQuestion implements Question {

	private String question;
	private Map<String, Boolean> answers;
	private String category = ""; 
	
	public AbstractQuestion (String question, Map<String, Boolean> answers, String category) {
		this.question = question;
		this.answers = answers;
		this.category = category;
	}
	
	public AbstractQuestion() {	}

	public String getCategory () {
		return this.category;
	}
	
	@Override
	public String getQuestion () {
		return this.question;
	}
	
	@Override
	public Map<String, Boolean> getAnswers () {
		return Collections.unmodifiableMap(this.answers);
	}
	
	@Override
	public String getRightAnswer () {
		for (Entry<String, Boolean> entry : answers.entrySet())
			if(entry.getValue().booleanValue() == true)
				return entry.getKey();
		
		return null;
	}

	@Override
	public void answer(Game game) {
		//TODO
	} 

}

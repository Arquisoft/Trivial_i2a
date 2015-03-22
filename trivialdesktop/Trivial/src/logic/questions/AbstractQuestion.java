package logic.questions;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

public class AbstractQuestion implements Question {

	private String question;
	private Map<String, Boolean> answers;
	private String category = "";
	
	public AbstractQuestion (String question, Map<String, Boolean> answers, String category) {
		this.question = question;
		this.answers = answers;
		this.category = category;
	}
	
	public String getCategory () {
		return this.category;
	}
	
	public String getQuestion () {
		return this.question;
	}
	
	public Map<String, Boolean> getAnswers () {
		return Collections.unmodifiableMap(this.answers);
	}
	
	public String getRightAnswer () {
		for (Entry<String, Boolean> entry : answers.entrySet())
			if(entry.getValue().booleanValue() == true)
				return entry.getKey();
		
		return null;
	}

}

package questions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class QuestionFactory {
	
	public static List<Question> createQuestions (File f) {
		
		List<Question> ret = new ArrayList<Question>();
		String file = "[";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String s;
			while((s = br.readLine()) != null)
				file += s;
			br.close();
		}
		catch (Exception e) {}
		file += "]";
		
		try {
			JSONParser parser = new JSONParser();
			int total = 0;
			
			JSONArray array = (JSONArray) parser.parse(new StringReader(file));
			
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> iterator = array.iterator();
			while (iterator.hasNext()) {
				JSONObject i = iterator.next();
				
				try {
					String category = (String) i.get("category");
					String question = (String) i.get("wording");
					
					@SuppressWarnings("unchecked")
					Iterator<JSONObject> opt = ((JSONArray) i.get("options")).iterator();
					
					Map<String, Boolean> answers = new HashMap<>();
					while (opt.hasNext()) {
						JSONObject o = opt.next();
						String text = (String) o.get("wording");
						boolean correct = (boolean) o.get("correct");
						answers.put(text, correct);
					}
					
					ret.add(createQuestion(category, question, answers));
				
					System.out.println(total + " ----> " + category + ": " + question + " ||| " + answers.toString());
					total++;
				}
				catch (Exception e) {e.printStackTrace();}
			}
			return ret;
		}
		catch (IOException ioe) {ioe.printStackTrace();}
		catch (ParseException pe) {pe.printStackTrace();}
		
		return null;
	}
	
	private static Question createQuestion (String category, String question, Map<String, Boolean> answers) {
		if(category.equals("geography"))
			return new GeographyQuestion(question, answers);
		else if(category.equals("entertainment"))
			return new EntertainmentQuestion(question, answers);
		else if(category.equals("history"))
			return new HistoryQuestion(question, answers);
		else if(category.equals("artliterature"))
			return new ArtLiteratureQuestion(question, answers);
		else if(category.equals("science"))
			return new ScienceQuestion(question, answers);
		else if(category.equals("sports"))
			return new SportsQuestion(question, answers);
		else
			throw new RuntimeException("The category is not recognised.");
	}
	
	
	/**
	 * Returns a list of Questions that are from a given category. 
	 * @param quests, the list of questions
	 * @param cat, the category of the questions to get
	 * @return a list with the questions of the category.
	 */
	public static <T> List<Question> getCategoryQuestions (List<Question> quests, Class<T> cat) {
		List<Question> ret = new ArrayList<>();
		
		if(cat==null)
			return quests;
		
		if(!Question.class.isAssignableFrom(cat) || cat.equals(Question.class))
			throw new RuntimeException("The class that you are passing as an "
					+ "argument is NOT an implementation of Question interface.");
		
		for(Question q : quests)
			if(q.getClass().equals(cat))
				ret.add(q);
		
		return ret;
	}

}

package model;

import java.awt.Color;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import questions.Question;
import questions.QuestionFactory;
import model.Box;
import model.state.State;
import model.state.TraversingBoardState;

public class Game {

	private Board board;
	
	private State state;
	
	int diceValue = 3;
	
	private Random generator= new Random();

	private File f;
	
	List<Question> questions = QuestionFactory.createQuestions(f);
	

	public Game(Map<String, Box> list ) {
		board = new Board(list);
		
	}
	
	public Game(Board board) { 
		this.board = board;
		state = new TraversingBoardState();
		
		onNewTurn();
		playerCanMove(board.getActualPlayer().getActualBox());
		
	} 
	
	private void onNewTurn() {
		System.out.println("Turn of "+board.getActualPlayer().getName());
		
	}

	public void pressed(Box boxPressed) {
		if(boxPressed!=null)
		{
			//cleanPossibleMovements(board.getBoxes());
			state = state.onGamePressed(this, boxPressed);
			//Draws where the player  can move
			playerCanMove(board.getActualPlayer().getActualBox());
		}
	}
	
	public void nextTurn() {
		diceValue = new Random().nextInt(3)+1;
		
		board.nextPlayer();
		
		onNewTurn();
	}

	public void playerCanMove(Box boxOfPlayer) {
		
		//Draws in black the different possible movements
		colourPossibleMovements(boxOfPlayer );
		
	}
	
	private void colourPossibleMovements(Box boxPressed ) {
		List<Box> list = board.getMoves(boxPressed, diceValue);
		for (Box b : list) 
		{
			b.setPossibleMove(true);
			b.setBackground(Color.BLACK);
		}
	}





	
	private void cleanPossibleMovements(Map<String, Box> map){
		Iterator<Entry<String, Box>> it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String,Box> pair = (Map.Entry<String,Box>)it.next();
	        pair.getValue().setPossibleMove(false);
	    }
	}

	public Board getBoard() {
		return this.board;
	}

	public int getDiceValue() {
		return diceValue;
	}

	public Question getRandomQuestion() {
		int randomPosition = generator.nextInt(questions.size());
		
		return questions.get(randomPosition);
	}
	
	
	

}
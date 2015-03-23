package logic;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import logic.state.State;
import logic.state.TraversingBoardState;

public class Game {

	private Board board;
	
	private State state;
	
	int diceValue = 3;
	

	public Game(Map<String, Box> list,Player... player) {
		board = new Board(list);
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
			cleanPossibleMovements(board.getBoxes());
			state = state.onGamePressed(this, boxPressed);
			//Draws where the player  can move
			playerCanMove(board.getActualPlayer().getActualBox());
		}
	}
	
	public void nextTurn() {
		
		
		board.nextPlayer();
		
		onNewTurn();
	}

	public void playerCanMove(Box boxOfPlayer) {
		
		//Draws in black the different possible movements
		colourPossibleMovements(boxOfPlayer );
		
	}
	
	private void colourPossibleMovements(Box box) {
		List<Box> list = board.getMoves(box, diceValue);
		for (Box b : list) {
			b.setPossibleMove(true);
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

}

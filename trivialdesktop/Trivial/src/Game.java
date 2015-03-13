import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Game {

	private Board board;

	public Game(Map<String, Box> list,Player... player) {
		board = new Board(list);
		
	}

	public void pressed(Box boxPressed) {
		int diceValue = 3;
		//Draws in black the different possible movements
		colourPossibleMovements(boxPressed,diceValue);
		
		//Cleans the black drawings
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cleanPossibleMovements(board.getBoxes());
		
	}
	
	private void colourPossibleMovements(Box boxPressed,int diceValue) {
		List<Box> list = board.getMoves(boxPressed, diceValue);
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

}

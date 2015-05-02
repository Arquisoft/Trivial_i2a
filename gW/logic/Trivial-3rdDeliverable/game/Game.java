package game;

import java.util.List;
import java.util.Map;

import player.Player;
import state.State;
import state.TraversingBoardState;
import board.Board;
import board.Dice;
import box.Box;

public class Game {

	private Board board;
	private State state;
	private Dice dice = Dice.getInstance();
	
	private boolean finish = false;

	private Game(Map<String, Box> list ) {
		board = new Board(list);
	}
	
	private Game(Board board) { 
		this.board = board;
		state = new TraversingBoardState();
		playerCanMove(board.getActualPlayer().getActualBox());
		
	}
	
	private void finishGame() {
		this.finish = false;
	}
	
	public boolean isGameFinished() {
		return this.finish;
	}

	public void pressed(Box boxPressed) {
		if(boxPressed!=null) {
			cleanPossibleMovements();
			state = state.onGamePressed(this, boxPressed);
			playerCanMove(board.getActualPlayer().getActualBox());
		}
	}
	
	public void nextTurn()  {
		if(!hasSomeoneWon()) {
			board.nextPlayer();
			dice.throwDice();
			cleanPossibleMovements();
			playerCanMove(board.getActualPlayer().getActualBox());
		}
	}

	private boolean hasSomeoneWon() {
		for(Player player: board.getPlayers())
			if(player.hasWon())
				finishGame();
		return finish;
	}
	
	private void playerCanMove(Box boxPressed) {
		List<Box> list = board.getMoves(boxPressed, dice.getValue());
		for (Box b : list)
			b.setPossibleMove(true);
	}
	
	private void cleanPossibleMovements(){
		Box[][] list = board.getBoard();
		
		for(int i=0; i<board.getBoard().length; i++)
			for(int j=0; j<board.getBoard()[0].length; j++) {
				Box b = list[i][j];
				b.setPossibleMove(true);
			}
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public int throwDice () {
		return this.dice.throwDice();
	}
	
	public int getDiceValue () {
		return this.dice.getValue();
	}
}

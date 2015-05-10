package game;

import java.util.List;
import java.util.Map;

import javax.persistence.*;

import player.Player;
import state.State;
import state.TraversingBoardState;
import board.Board;
import board.Dice;
import box.Box;
import question.Question;

@Entity
public class Game {
    
    private Question actualQuestion;
    
    public Question getActualQuestion(){
        return this.actualQuestion;
    }
    
    public void setActualQuestion(Question question){
        this.actualQuestion = question;
    }

	private Board board;
	private State state;
	private Dice dice = Dice.getInstance();
	
	public boolean finish = false;

	private Game(Map<String, Box> list ) {
		board = new Board(list);
	}
	
	public Game(Board board) { 
		this.board = board;
		state = new TraversingBoardState();
		dice.throwDice();
		playerCanMove(board.getActualPlayer().getActualBox());
		
	}
	
	public void finishGame() {
	    System.out.print("NAVAAAAAAAAAAAAAAAAAAAAAAAAAAAASss");
		this.finish = true;
	}
	
	public boolean isGameFinished() {
		return this.finish;
	}

	public boolean pressed(Box boxPressed) {
		if(boxPressed!=null) {
			cleanPossibleMovements();
			boolean b = state.onGamePressed(this, boxPressed);
			playerCanMove(board.getActualPlayer().getActualBox());
			return b;
		}
		
		return false;
	}
	
	public boolean pressed(int i,int j){
	        return  pressed(board.getBoard()[i][j]);
	}
	
	public void nextTurn()  {
		if(!hasSomeoneWon()) {
			board.nextPlayer();
			dice.throwDice();
			cleanPossibleMovements();
			playerCanMove(board.getActualPlayer().getActualBox());
		}
	}

	public boolean hasSomeoneWon() {
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
				if(b!=null){
				    b.setPossibleMove(true);
				    b.setMovable(false);
				}
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

package box;

import cheese.Cheese;
import game.Game;
import question.Question;

public abstract class AbstractBox implements Box {

	private boolean possibleMove = false;
	private boolean movable = false;
	private int row;
	private int column;
	private Cheese cheese;
	
	public boolean isPossibleMove() {
		return possibleMove;
	}

	public void setPossibleMove(boolean possibleMove) {
		this.possibleMove = possibleMove;
	}
	
	public void setMovable(boolean b){
	    this.movable = b;
	}
	
	
	public boolean isMovable(){
	    return movable;
	}
	
	public void setActualQuestion(Question question, Game game){
	    game.setActualQuestion(question);
	}


	@Override
	public int getRow() {
		return this.row;
	}

	@Override
	public int getColumn() {
		return this.column;
	}

	@Override
	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public Cheese getCheese() {
		return this.cheese;
	}

	@Override
	public void setCheese(Cheese cheese) {
		this.cheese = cheese;
	}
}

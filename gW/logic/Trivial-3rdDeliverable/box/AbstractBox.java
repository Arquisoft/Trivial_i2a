package box;

import cheese.Cheese;

public class AbstractBox implements Box {

	private boolean possibleMove = false;
	private int row;
	private int column;
	private Cheese cheese;
	
	public boolean isPossibleMove() {
		return possibleMove;
	}

	public void setPossibleMove(boolean possibleMove) {
		this.possibleMove = possibleMove;
	}

	@Override
	public boolean execute() {
		//Default implementation for a 
		//throw-again box
		return true;
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

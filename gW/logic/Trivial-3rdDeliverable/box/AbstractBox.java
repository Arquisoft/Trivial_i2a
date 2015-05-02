package box;

public class AbstractBox implements Box {

	private boolean possibleMove = false;
	private int row;
	private int column;
	
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
}

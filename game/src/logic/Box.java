package logic;

import java.util.List;

public interface Box {

	/* (non-Javadoc)
	 * @see logic.Box#getQuestion()
	 */
	public abstract Question getQuestion();

	public abstract void setAdjacents(Box... args);

	public abstract List<Box> getAdjacentBoxes();

	public abstract void setAdjacentBoxes(List<Box> adjacentBoxes);

	public abstract String getColour();

	public abstract void setColour(String colour);

	public abstract void setPossibleMove(boolean b);

	public abstract boolean getPossibleMove();

	public abstract boolean isMarked();

	public abstract void mark(boolean marked); 

}
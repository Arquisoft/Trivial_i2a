package model;
import java.util.ArrayList;
import model.Board.BoxType;

public class Player 
{
	private Box currentBox;
	private ArrayList<BoxType> prizes = new ArrayList<BoxType>();
	
	public Box getCurrentBox() {
		return currentBox;
	}
	public void setCurrentBox(Box currentBox) {
		this.currentBox = currentBox;
	}
	public ArrayList<BoxType> getPrizes() {
		return prizes;
	}
	public void setPrizes(ArrayList<BoxType> prizes) {
		this.prizes = prizes;
	}
	
	
	public void move(Box box)
	{
		setCurrentBox(box);
	}
}

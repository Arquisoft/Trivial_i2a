package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;

import model.Board.BoxType;

public class Box extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6017704654638528564L;
	
	private int column;
	private int row;
	
	private BoxType type;

	private List<Box> adjacentBoxes;

	private boolean possibleMove;

	private boolean marked;

	public void setColumn(int j) {
		this.column = j;
	}

	public void setRow(int i) {
		this.row = i;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public BoxType getType() {
		return type;
	}

	public void setType(BoxType type) {
		this.type = type;
	}
	
	public Collection<? extends Box> getAdjacentBoxes() {
		return adjacentBoxes;
	}

	public void setAdjacentBoxes(List<Box> adjacentBoxes) {
		this.adjacentBoxes = adjacentBoxes;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Box other = (Box) obj;
		if (getType() == null) {
			if (other.getType() != null)
				return false;
		} else if (!getType().equals(other.getType()))
			return false;
		return true;
	}

	public void setPossibleMove(boolean b) {
		this.possibleMove = b;
	}
	
	public boolean getPossibleMove(){
		return this.possibleMove;
	}

	public boolean isMarked() {
		return marked;
	}
	
	public void mark(boolean marked){
		this.marked = marked;
	}
	
	public void setAdjacents(Box ... args){
		adjacentBoxes = new ArrayList<>();
		for(Box b :args){
			this.adjacentBoxes.add(b);
		}
	}
}

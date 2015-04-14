package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;

import questions.Question;
import questions.QuestionFactory;
import model.Board.BoxType;

public class Box extends JButton{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6017704654638528564L;

	private static final File f = null;
	
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
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
		if (column != other.column)
			return false;
		if (row != other.row)
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
	
	public boolean tieneEstrella()
	{
	   if(getIcon()!=null)
	      return true;
	   return false;
	}

	public Question getQuestion() {
		return QuestionFactory.createQuestions(f).get(0);
	}
}

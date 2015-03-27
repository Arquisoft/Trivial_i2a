package model;

import javax.swing.JButton;

public class Box extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6017704654638528564L;
	
	private int column;
	private int row;

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
}

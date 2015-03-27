package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import main.Principal;

public class Board {

	private ArrayList<Box> board;

	public ArrayList<Box> getBoard() {
		return board;
	}

	public Board(int number) {
		this.board = new ArrayList<Box>();
		for(int i = 0;i<number;i++){
			for(int j = 0;j<number;j++){
				final Box box = new Box();
				box.setVisible(false);
				if(checkPrintable(i,j,number)){
					box.setVisible(true);
				}
				box.setColumn(j);
				box.setRow(i);
				box.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Principal.getGame().pressed(box);
					}
				});
				this.board.add(box);
			}
		}
	}
	
	private boolean checkPrintable(int i, int j,int length) {
		if(i==0 || j == 0 || i == length-1 || j == length-1 || i == length/2 || j == length/2 ){
			return true;
		}
		return false;
	}

}

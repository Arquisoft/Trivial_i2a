package model;

import gui.BoardGui;

import java.awt.Color;
import java.util.List;

public class Game {

	private Board board;

	public Game(Board board) {
		this.board= board;
	}

	public Board getBoard() {
		return this.board;
	}
	
	public void pressed(Box boxPressed) {
		int diceValue = 3;
		//Draws in black the different possible movements
		colourPossibleMovements(boxPressed,diceValue);
	}
	
	private synchronized void colourPossibleMovements(Box boxPressed,int diceValue) {
		List<Box> list = board.getMoves(boxPressed, diceValue);
		for (Box b : list) 
		{
			b.setPossibleMove(true);
			PaintingThread paint = new PaintingThread(b);
			paint.start();
		}
	}
}



class PaintingThread extends Thread
{
	private Box b;
	
	public PaintingThread(Box b) 
	{
		this.b=b;
	}

	@Override
	public void run() 
	{
		Color original = b.getBackground();
		while(true)
		{
			try {
				b.setBackground(Color.BLACK);
				Thread.sleep(300);
				b.setBackground(original);
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

package model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Principal;

public class Board {

	private ArrayList<Box> finalMoves;
	
	public enum BoxType
	{
		blue_normal, 
		red_normal,
		yellow_normal,
		green_normal,
		blue_prize, 
		red_prize,
		yellow_prize,
		green_prize,
		throwAgain,
		center;
	}
	
	private Box[][] board;
	private ArrayList<BoxType> boxTypes = new ArrayList<BoxType>();
	BoxType[] prizeTypes;
	int prizeTypesIterator = 0;

	public Box[][] getBoard() {
		return board;
	}

	public Board(int number) {
		this.board = new Box[number][number];
		initializeTypes(number);
		for(int i = 0;i<number;i++){
			for(int j = 0;j<number;j++){
				final Box box = new Box();
				box.setVisible(false);
				box.setColumn(j);
				box.setRow(i);

				this.board[i][j] = box;
				
				if(checkPrintable(i,j,number))
				{
					box.setVisible(true);
					assignType(number, box);
				}
				box.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Principal.getGame().pressed(box);
					}
				});
			}
		}
	}
	
	private void assignType(int number, Box box) 
	{
		if(isThrowAgainType(number, box))
		{
			box.setType(BoxType.throwAgain);
			box.setBackground(Color.GRAY);
		}
		else if(isCenter(number, box))
		{
			box.setType(BoxType.center);
			box.setBackground(Color.MAGENTA);
		}
		else if(isPrizeType(number, box))
		{
			box.setType(prizeTypes[prizeTypesIterator]);
			prizeTypesIterator++;
			switch(box.getType())
			{
				case blue_prize:
					box.setBackground(Color.BLUE);
					box.setText("1");
					break;
				case red_prize:
					box.setBackground(Color.RED);
					box.setText("2");
					break;
				case yellow_prize:
					box.setBackground(Color.YELLOW);
					box.setText("3");
					break;
				case green_prize:
					box.setBackground(Color.GREEN);
					box.setText("4");
					break;
			default:
				break;
			}
		}
		else
		{
			int repetitionController = 10;
			Random random = new Random();
			int index = 0;
			if(boxTypes.size() > 1)
				while(repetitionController>0)
				{
					index = random.nextInt(boxTypes.size());
					box.setType(boxTypes.get(index));
					if(!box.getType().equals(getBoxTypeAtLeft(box, number)) &&
					   !box.getType().equals(getBoxTypeAbove(box, number)))
					{
						break;
					}
					repetitionController--;
				}
			box.setType(boxTypes.get(index));
			boxTypes.remove(index);
			
			switch(box.getType())
			{
			case blue_normal:
				box.setBackground(Color.BLUE);
				break;
			case red_normal:
				box.setBackground(Color.RED);
				break;
			case yellow_normal:
				box.setBackground(Color.YELLOW);
				break;
			case green_normal:
				box.setBackground(Color.GREEN);
				break;
			default:
				break;
			}
		}
	}


	private BoxType getBoxTypeAbove(Box box, int length) 
	{
		if(box.getRow()==0 || box.getRow()==length/2 || box.getRow()==length-1)
			return null;
		return board[box.getRow()-1][box.getColumn()].getType();
	}

	private BoxType getBoxTypeAtLeft(Box box, int length) 
	{
		if(box.getColumn()==0 || box.getColumn()==length/2 || box.getColumn()==length-1)
			return null;
		return board[box.getRow()][box.getColumn()-1].getType();
	}

	private void initializeTypes(int number)
	{
		int numberOfNormalBoxes = (number*number) - ((number/2 - 1) * (number/2 - 1) * 4) - 13;
		
		for(int i=0; i<numberOfNormalBoxes/4; i++)
			boxTypes.add(BoxType.blue_normal);
		for(int i=0; i<numberOfNormalBoxes/4; i++)
			boxTypes.add(BoxType.green_normal);
		for(int i=0; i<numberOfNormalBoxes/4; i++)
			boxTypes.add(BoxType.red_normal);
		for(int i=0; i<numberOfNormalBoxes/4; i++)
			boxTypes.add(BoxType.yellow_normal);
		
		prizeTypes = new BoxType[]{
				BoxType.blue_prize, 
				BoxType.green_prize, 
				BoxType.red_prize, 
				BoxType.yellow_prize};
	}
	
	
	private boolean isCenter(int number, Box box) 
	{
		if(box.getRow()==number/2 && box.getColumn()==number/2)
			return true;
		return false;
	}

	private boolean isPrizeType(int number, Box box) {
		if(box.getRow()==0 && box.getColumn()==number/2 ||
		   box.getRow()==number/2 && box.getColumn()==0 ||
		   box.getRow()==number/2 && box.getColumn()==number-1 ||	
		   box.getRow()==number-1 && box.getColumn()==number/2
		  )
			return true;
		return false;
	}

	private boolean isThrowAgainType(int number, Box box) {
		if(box.getRow()==0 && box.getColumn()==0 || 
		   box.getRow()==0 && box.getColumn()==number-1 ||
	  	   box.getRow()==number-1 && box.getColumn()==0 || 
	  	   box.getRow()==number-1 && box.getColumn()==number-1 ||
		   box.getRow()==number/4 && box.getColumn()==number/2 ||
		   box.getRow()==number/2 && box.getColumn()==number/4 ||
		   box.getRow()==number/2 && box.getColumn()==(number*3)/4 ||
		   box.getRow()==(number*3)/4 && box.getColumn()==number/2
		   )
			return true;
		return false;
	}

	private boolean checkPrintable(int i, int j,int length) {
		if(i==0 || j == 0 || i == length-1 || j == length-1 || i == length/2 || j == length/2 ){
			return true;
		}
		return false;
	}
	
	
	
	private List<Box> possibleMoves(Box current,Box box,int dice) 
	{	
		List<Box> adBoxes = getAdjacentBoxes(box);
		for (Box bo : adBoxes) {
			if(dice>0){
				if (!bo.equals(current)) {
					possibleMoves(box,bo,dice-1);
				}
			}
			else{
				if (!bo.equals(current)) 
					finalMoves.add(box);
			}
		}
		return finalMoves;
	}
	
	
	private List<Box> getAdjacentBoxes(Box box) 
	{
		int row = box.getRow();
		int column = box.getColumn();
		ArrayList<Box> array = new ArrayList<Box>();
		
		// UP
		try {
			addToArray(array, this.board[row - 1][column]);
		} catch (Exception e) {}
		// DOWN
		try {
			addToArray(array, this.board[row + 1][column]);
		} catch (Exception e) {}
		// LEFT
		try {
			addToArray(array, this.board[row][column - 1]);
		} catch (Exception e) {}
		// RIGHT
		try {
			addToArray(array, this.board[row][column + 1]);
		} catch (Exception e) {}
		
		return array;
	}

	private void addToArray(ArrayList<Box> array, Box box) 
	{
		if(box.isVisible())
			array.add(box);
	}

	public List<Box> getMoves(Box box,int dice)
	{
		finalMoves = new ArrayList<Box>();
		finalMoves = (ArrayList<Box>) possibleMoves(box,box, dice);
//		for(Box finalBox : finalMoves)
//		{
//			finalBox.setBackground(Color.BLACK);
//		}
		return finalMoves;
	}
}

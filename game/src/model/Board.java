package model;
import gui.BoardGui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import main.Principal;
import model.Box;

public class Board {
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
	
	public int numberOfPlayers;
	private Map<String, Box> boxes;
	int counter;
	private ArrayList<Box> finalMoves;
	private Player[] players;
	private Box startingBox;
	
	private int indexOfActualPlayer;
	private Player actualPlayer;
	private Box[][] board;
	private ArrayList<BoxType> boxTypes = new ArrayList<BoxType>();
	BoxType[] prizeTypes;
	int prizeTypesIterator = 0;

	
	public Board(int number, int numberOfPlayers) {
		this.board = new Box[number][number];
		this.numberOfPlayers = numberOfPlayers;
		players = new Player[numberOfPlayers];
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
		
			startingBox =  board[board.length/2][board.length/2];
			
			prepareBoard();
		}
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public Map<String, Box> getBoxes() {
		return boxes;
	}

	public void setBoxes(Map<String, Box> boxes) {
		this.boxes = boxes;
	}

	public Board(Map<String, Box> list) {
		this.boxes = list;
		
		startingBox = list.get("00FFFF");
		
		prepareBoard();
	}
	
	private void prepareBoard() {
		generatePlayers();
	}

	private void generatePlayers() {
		for(int i=0; i<players.length; i++)
		{
			players[i] = new Player("Player "+String.valueOf(i+1));
			players[i].setActualBox(startingBox);
		}
		
		indexOfActualPlayer = 0;
		actualPlayer = players[indexOfActualPlayer];
	}
	
	public void nextPlayer()
	{
		if(indexOfActualPlayer==players.length-1)
			indexOfActualPlayer = 0;
		else
			indexOfActualPlayer++;
		
		actualPlayer = players[indexOfActualPlayer];
	}

	public List<Box> getMovesOfPlayer(int playerIndex,int dice){
		finalMoves = new ArrayList<Box>();
		
		Box boxOfThePlayer = players[playerIndex].getActualBox();
		
		return possibleMoves(boxOfThePlayer,boxOfThePlayer, dice);
	}

	public List<Box> getMoves(Box box,int dice){
		finalMoves = new ArrayList<Box>();
		return possibleMoves(box,box, dice);
	}
	
	private List<Box> possibleMoves(Box current,Box box,int dice) {
			
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

	public Player getActualPlayer() {
		return actualPlayer;
	}

	public void setActualPlayer(Player actualPlayer) {
		this.actualPlayer = actualPlayer;
	}

	public Box[][] getBoard() {
		return board;
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
					box.setIcon(new ImageIcon(Board.class.getResource("/img/star.png")));
					break;
				case red_prize:
					box.setBackground(Color.RED);
					box.setIcon(new ImageIcon(Board.class.getResource("/img/star.png")));
					break;
				case yellow_prize:
					box.setBackground(Color.YELLOW);
					box.setIcon(new ImageIcon(Board.class.getResource("/img/star.png")));
					break;
				case green_prize:
					box.setBackground(Color.GREEN);
					box.setIcon(new ImageIcon(Board.class.getResource("/img/star.png")));
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
	}


package board;
 

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import player.Player;
import box.BlueBox;
import box.Box;
import box.GreenBox;
import box.MultiCheeseBox;
import box.RedBox;
import box.ThrowAgainBox;
import box.YellowBox;

public class Board { 
	
	public int numberOfPlayers;
	private Map<String, Box> boxes;
	int counter;
	private ArrayList<Box> finalMoves;
	private Player[] players;
	private Box startingBox;
	
	private int indexOfActualPlayer;
	private Player actualPlayer;
	private Box[][] board;  
	int prizeTypesIterator = 0;
	private ArrayList<Box> normalBoxes;

	
	public Board(int number, int numberOfPlayers) {
		this.board = new Box[number][number];
		this.numberOfPlayers = numberOfPlayers;
		players = new Player[numberOfPlayers];
		fillNormalAndPrizesBoxesArray(number);
		for(int i = 0;i<number;i++){
			for(int j = 0;j<number;j++){
				
				Box box = null;
				// TODO: MAYBE JUST ERASE IT, AS WE WILL DO THIS IN THE -GUI- PART
				//Dibujando la caja (PRESENTACIÓN)   
//				box.setVisible(true);
//				box.setColumn(j);
//				box.setRow(i);

				if(checkPrintable(i,j))
				{
					//assignType(number, box); Aquí le asignabamos un valor,
					// como ese método era feo, habrá que hacer otro
					//que nos devuelve una instancia cualquiera
					box = assignNewBox(i, j); //TODO (THIS TO_DO IS JUST TO MARK THIS PART TO REVISE)
					//Debe mantener la estructura (en este sitio van los quesitos, en
					//este otro van los tira otra vez... todo eso
				}
				
				if(box!=null)
					this.board[i][j] = box;
				// TODO: MAYBE JUST ERASE IT, AS WE WILL DO THIS IN THE -GUI- PART
				// (PRESENTACIÓN) Construíamos el botón
//				box.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						Principal.getGame().pressed(box);
//					}
//				});
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

	public List<Box> getMovesOfPlayer(int playerIndex, int dice) {
		finalMoves = new ArrayList<Box>();
		
		Box boxOfThePlayer = players[playerIndex].getActualBox();
		
		return possibleMoves(boxOfThePlayer,boxOfThePlayer, dice);
	}

	public List<Box> getMoves(Box box, int dice) {
		finalMoves = new ArrayList<Box>();
		return possibleMoves(box, box, dice);
	}
	
	private List<Box> possibleMoves(Box current, Box box, int dice) {
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
	
//	private Box assignType(int number, Box box) 
//	{
//		if(isThrowAgainType(number, box))
//		{
//			box.setType(BoxType.throwAgain);
//			box.setBackground(Color.GRAY);
//		}
//		else if(isCenter(number, box))
//		{
//			box.setType(BoxType.center);
//			box.setBackground(Color.MAGENTA);
//		}
//		else if(isPrizeType(number, box))
//		{
//			box.setType(prizeTypes[prizeTypesIterator]);
//			prizeTypesIterator++;
//			switch(box.getType())
//			{
//				case blue_prize:
//					box.setBackground(Color.BLUE);
//					box.setIcon(new ImageIcon(Board.class.getResource("/img/star.png")));
//					break;
//				case red_prize:
//					box.setBackground(Color.RED);
//					box.setIcon(new ImageIcon(Board.class.getResource("/img/star.png")));
//					break;
//				case yellow_prize:
//					box.setBackground(Color.YELLOW);
//					box.setIcon(new ImageIcon(Board.class.getResource("/img/star.png")));
//					break;
//				case green_prize:
//					box.setBackground(Color.GREEN);
//					box.setIcon(new ImageIcon(Board.class.getResource("/img/star.png")));
//					break;
//			default:
//				break;
//			}
//		}
//		else
//		{
//			int repetitionController = 10;
//			Random random = new Random();
//			int index = 0;
//			if(boxTypes.size() > 1)
//				while(repetitionController>0)
//				{
//					index = random.nextInt(boxTypes.size());
//					box.setType(boxTypes.get(index));
//					if(!box.getType().equals(getBoxTypeAtLeft(box, number)) &&
//					   !box.getType().equals(getBoxTypeAbove(box, number)))
//					{
//						break;
//					}
//					repetitionController--;
//				}
//			box.setType(boxTypes.get(index));
//			boxTypes.remove(index);
//			
//			switch(box.getType())
//			{
//			case blue_normal:
//				box.setBackground(Color.BLUE);
//				break;
//			case red_normal:
//				box.setBackground(Color.RED);
//				break;
//			case yellow_normal:
//				box.setBackground(Color.YELLOW);
//				break;
//			case green_normal:
//				box.setBackground(Color.GREEN);
//				break;
//			default:
//				break;
//			}
//		}
//	}

	
	private Box assignNewBox(int i, int j) 
	{
		if(isThrowAgainType(i, j))
			return new ThrowAgainBox();
		else if(isCenter(i, j))
			return new MultiCheeseBox();
		//TODO else if(isPrizeType(i, j))   WHAT?
		//	       return ????  WE CAN'T RETURN A CHEESE...	
		
		return getRandomNormalBox();
	}


	private Box getRandomNormalBox() {
		Random random = new Random();
		int index = 0;
		if(normalBoxes.size() > 1)
			index = random.nextInt(normalBoxes.size());
		normalBoxes.remove(index);
		return normalBoxes.get(index);
}


	//TODO: MAYBE ERASE? THEY ARE DIFFICULT TO IMPLEMENT NOW...
//	private Box getBoxAbove(int i, int j) 
//	{
//		if(i==0 || i==this.board.length/2 || i==this.board.length-1)
//			return null;
//		return this.board[i-1][j];
//	}
//
//	private Box getBoxAtLeft(int i, int j) 
//	{
//		if(j==0 || j==this.board.length/2 || j==this.board.length-1)
//			return null;
//		return board[i][j-1];
//	}

	private void fillNormalAndPrizesBoxesArray(int number)
	{
		int numberOfNormalBoxes = (number*number) - ((number/2 - 1) * (number/2 - 1) * 4) - 13;
		
		for(int i=0; i<numberOfNormalBoxes/4; i++)
			normalBoxes.add(new BlueBox());
		for(int i=0; i<numberOfNormalBoxes/4; i++)
			normalBoxes.add(new GreenBox());
		for(int i=0; i<numberOfNormalBoxes/4; i++)
			normalBoxes.add(new RedBox());
		for(int i=0; i<numberOfNormalBoxes/4; i++)
			normalBoxes.add(new YellowBox());
		
		//TODO WHAT DO WE DO WITH THE CHEESE TYPES?? THEY DON'T IMPLEMENT BOX
//		prizeTypes = new BoxType[]{
//				BoxType.blue_prize, 
//				BoxType.green_prize, 
//				BoxType.red_prize, 
//				BoxType.yellow_prize};
	}
	
	
	private boolean isCenter(int i, int j) 
	{
		if(i==this.board.length/2 && j==this.board.length/2)
			return true;
		return false;
	}

	private boolean isPrizeType(int i, int j) {
		if(i==0 && j==this.board.length/2 ||
		   i==this.board.length/2 && j==0 ||
		   i==this.board.length/2 && j==this.board.length-1 ||	
		   i==this.board.length-1 && j==this.board.length/2
		  )
			return true;
		return false;
	}

	private boolean isThrowAgainType(int i, int j) {
		if(i==0 && j==0 || 
		   i==0 && j==this.board.length-1 ||
	  	   i==this.board.length-1 && j==0 || 
	  	   i==this.board.length-1 && j==this.board.length-1 ||
		   i==this.board.length/4 && j==this.board.length/2 ||
		   i==this.board.length/2 && j==this.board.length/4 ||
		   i==this.board.length/2 && j==(this.board.length*3)/4 ||
		   i==(this.board.length*3)/4 && j==this.board.length/2
		   )
			return true;
		return false;
	}

	private boolean checkPrintable(int i, int j) {
		if(i==0 || j == 0 || 
				i == this.board.length-1 || 
				j == this.board.length-1 || 
				i == this.board.length/2 || 
				j == this.board.length/2 ) {
			return true;
		}
		return false;
	}
	
	
	private List<Box> getAdjacentBoxes(Box box) 
	{
		ArrayList<Box> array = new ArrayList<Box>();
		int i = box.getRow();
		int j = box.getColumn();
		
		// UP
		try {
			addToArray(array, this.board[i - 1][j]);
		} catch (Exception e) {}
		// DOWN
		try {
			addToArray(array, this.board[i + 1][j]);
		} catch (Exception e) {}
		// LEFT
		try {
			addToArray(array, this.board[i][j - 1]);
		} catch (Exception e) {}
		// RIGHT
		try {
			addToArray(array, this.board[i][j + 1]);
		} catch (Exception e) {}
		
		return array;
	}

	private void addToArray(ArrayList<Box> array, Box box) 
	{
		if(checkPrintable(box.getRow(), box.getColumn()))
			array.add(box);
	}
}

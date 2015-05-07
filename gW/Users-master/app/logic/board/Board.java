package board;
 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cheese.BlueCheese;
import cheese.Cheese;
import cheese.GreenCheese;
import cheese.RedCheese;
import cheese.YellowCheese;
import player.Player;
import box.AbstractBox;
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
	private ArrayList<Box> finalMoves;
	private Player[] players;
	private Box startingBox;
	
	private int indexOfActualPlayer;
	private Player actualPlayer;
	private Box[][] board;  
	int cheeseTypesIterator = 0;
	private ArrayList<Box> normalBoxes;
	private Cheese[] cheeseTypes;

	//TODO: Erase this after testing process
	public static void main(String[] args)
	{
		Board board = new Board(13, 4);
	}
	
	public Board(int number, int numberOfPlayers) {
		this.board = new Box[number][number];
		this.numberOfPlayers = numberOfPlayers;
		players = new Player[numberOfPlayers];
		fillNormalAndPrizesBoxesArray(number);
		for(int i = 0;i<number;i++){
			System.out.println();
			for(int j = 0;j<number;j++){
				
				Box box = null;
				if(checkPrintable(i,j))
				{
					box = assignNewBox(i, j);
				}
				
					this.board[i][j] = box;
				// TODO: MAYBE JUST ERASE THIS, AS WE WILL DO THIS IN THE -GUI- PART
				// (PRESENTACI�N) Constru�amos el bot�n
//				box.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						Principal.getGame().pressed(box);
//					}
//				});
				//TODO: ERASE THIS 2 LINES AFTER TESTING PROCESS
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
					{
						finalMoves.add(box);
						bo.setMovable(true);
					}
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
	
	//TODO: Erase the Sysouts
	private Box assignNewBox(int i, int j) {
		if(isThrowAgainType(i, j))
		{
			System.out.print("T");
			return new ThrowAgainBox();
		}
		else if(isCenter(i, j))
		{
			System.out.print("C");
			return new MultiCheeseBox();
		}
		//TODO 
		else if(isCheeseType(i, j))
			return assignCheeseType();
		
		System.out.print("N");
		return getRandomNormalBox();
	}

	//TODO: Erase the Sysouts
	private Box assignCheeseType() 
	{
		Box box = null;
		if(cheeseTypes[cheeseTypesIterator] instanceof BlueCheese)
		{
			box = new BlueBox();
			box.setCheese(new BlueCheese());
			cheeseTypesIterator++;
			System.out.print("b");
		}
		else if(cheeseTypes[cheeseTypesIterator] instanceof GreenCheese)
		{
			box = new GreenBox();
			box.setCheese(new BlueCheese());
			cheeseTypesIterator++;
			System.out.print("g");
		}
		else if(cheeseTypes[cheeseTypesIterator] instanceof RedCheese)
		{
			box = new RedBox();
			box.setCheese(new BlueCheese());
			cheeseTypesIterator++;
			System.out.print("r");
		}
		else if(cheeseTypes[cheeseTypesIterator] instanceof YellowCheese)
		{
			box = new YellowBox();
			box.setCheese(new BlueCheese());
			cheeseTypesIterator++;
			System.out.print("y");
		}
		return box;
	}

	private Box getRandomNormalBox() {
		Random random = new Random();
		int index = 0;
		if(normalBoxes.size() > 1)
			index = random.nextInt(normalBoxes.size());
		Box chosenOne = normalBoxes.get(index);
		normalBoxes.remove(index);
		return chosenOne;
}


	//TODO: MAYBE ERASE? Or re-factor with Box.getRow() etc.?
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
		normalBoxes = new ArrayList<Box>();
		
		for(int i=0; i<numberOfNormalBoxes/4; i++)
			normalBoxes.add(new BlueBox());
		for(int i=0; i<numberOfNormalBoxes/4; i++)
			normalBoxes.add(new GreenBox());
		for(int i=0; i<numberOfNormalBoxes/4; i++)
			normalBoxes.add(new RedBox());
		for(int i=0; i<numberOfNormalBoxes/4; i++)
			normalBoxes.add(new YellowBox());
		
		
		cheeseTypes = new Cheese[]{
				new BlueCheese(), 
				new GreenCheese(), 
				new RedCheese(), 
				new YellowCheese()};
	}
	
	
	private boolean isCenter(int i, int j) 
	{
		if(i==this.board.length/2 && j==this.board.length/2)
			return true;
		return false;
	}

	private boolean isCheeseType(int i, int j) {
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
				j == this.board.length/2 )
			return true;
		return false;
	}
	
	
	private List<Box> getAdjacentBoxes(Box box) {
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

	private void addToArray(ArrayList<Box> array, Box box) {
		if(checkPrintable(box.getRow(), box.getColumn()))
			array.add(box);
	}
}

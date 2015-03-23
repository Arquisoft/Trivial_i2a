package logic;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
	private Map<String, Box> boxes;
	int counter;
	private ArrayList<Box> finalMoves;
	private Player[] players = new Player[4];
	private Box startingBox;
	
	private int indexOfActualPlayer;
	private Player actualPlayer;

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
			
			List<Box> adBoxes = new ArrayList<Box>(box.getAdjacentBoxes());
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
	}


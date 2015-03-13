import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
	private Map<String, Box> boxes;
	int counter;
	private ArrayList<Box> finalMoves;

	public Map<String, Box> getBoxes() {
		return boxes;
	}

	public void setBoxes(Map<String, Box> boxes) {
		this.boxes = boxes;
	}

	public Board(Map<String, Box> list) {
		this.boxes = list;
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
	}


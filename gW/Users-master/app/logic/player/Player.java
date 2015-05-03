package player;

import java.util.ArrayList;
import java.util.List;

import box.Box;
import cheese.Cheese;

public class Player {
	
	private Box actualBox;
	private String name;
	private List<Cheese> quesitos = new ArrayList<>();
	
	public boolean addQuesito (Cheese quesito) {
		for(Cheese c : quesitos)
			if(c.getClass().equals(quesito.getClass()))
					return false;
		quesitos.add(quesito);
		return true;
	}
	
	public boolean hasWon() {
		return quesitos.size() == 4;
	}

	public Player(String name) {
		this.name = name;
	}

	public Box getActualBox() {
		return actualBox;
	}

	public void setActualBox(Box actualBox) {
		this.actualBox = actualBox;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
 
		
}

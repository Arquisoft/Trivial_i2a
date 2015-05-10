package player;

import java.util.ArrayList;
import java.util.List;

import box.Box;
import cheese.Cheese;

public class Player {
	
	private Box actualBox;
	private String name;
	public List<Cheese> quesitos = new ArrayList<>();
	
	public boolean addQuesito (Cheese quesito) {
	    System.out.println("freshhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		for(Cheese c : quesitos)
			if(c.getClass().equals(quesito.getClass()))
					return false;
		quesitos.add(quesito);
		return true;
	}
	
	public boolean hasWon() {
	    System.out.println("has won " + quesitos.size());
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

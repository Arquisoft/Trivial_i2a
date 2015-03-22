package logic;

public class Player {
	
	private Box actualBox;
	private String name;

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

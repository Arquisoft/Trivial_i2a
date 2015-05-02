package player;

import box.Box;

public class Player {
	
	private Box actualBox;
	private String name;
	private int score = 0;
	private static final int SCORE_TO_WIN = 4;
	private boolean won = false;

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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
		if(score==SCORE_TO_WIN)
			this.setWon(true);;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}
 
		
}

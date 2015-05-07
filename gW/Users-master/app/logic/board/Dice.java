package board;

import java.util.Random;

public class Dice {
	
	private final static int MAX_VALUE = 6;
	private static Dice instance = new Dice();
	private Random rand = new Random();
	private int value;
	
	private Dice () {}
	
	public int throwDice () {
		this.value = rand.nextInt(MAX_VALUE)+1;
		return value;
	}
	
	public static Dice getInstance() {
		return instance;
	}
	
	public int getValue () {
		return this.value;
	}

}

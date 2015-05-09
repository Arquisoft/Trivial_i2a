package board;

import java.util.Random;

public class Dice {
	
	private final static int MAX_VALUE = 6;
	private static Dice instance = new Dice();
	private Random rand = new Random();
	
	private Dice () {}
	
	public int throwDice () {
		return rand.nextInt(MAX_VALUE)+1;
	}
	
	public static Dice getInstance() {
		return instance;
	}

}

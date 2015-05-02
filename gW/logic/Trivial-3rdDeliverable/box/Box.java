package box;

public interface Box {
	
	public int getRow();
	public int getColumn();
	
	public boolean execute(); //true if the player plays again

	public void setPossibleMove(boolean b);

}

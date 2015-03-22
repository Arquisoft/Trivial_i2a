package logic;

public interface Question {

	public abstract boolean isCorrectlyAnswered();

	public abstract void setCorrectlyAnswered(boolean correctlyAnswered);

	public abstract void answer();

}
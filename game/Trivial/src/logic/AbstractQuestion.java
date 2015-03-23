package logic;

public abstract class AbstractQuestion implements Question {
	
	protected boolean correctlyAnswered;

	/* (non-Javadoc)
	 * @see logic.Question#isCorrectlyAnswered()
	 */
	@Override
	public boolean isCorrectlyAnswered() {
		return correctlyAnswered;
	}

	/* (non-Javadoc)
	 * @see logic.Question#setCorrectlyAnswered(boolean)
	 */
	@Override
	public void setCorrectlyAnswered(boolean correctlyAnswered) {
		this.correctlyAnswered = correctlyAnswered;
	}

	/* (non-Javadoc)
	 * @see logic.Question#answer()
	 */
	@Override
	public abstract void answer() ;
	
	
}

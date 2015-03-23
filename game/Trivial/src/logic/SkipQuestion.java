package logic;

public class SkipQuestion extends AbstractQuestion {

	@Override
	public void answer() {
		correctlyAnswered = true;
	}

}

package logic;

public class SkipBox extends AbstractBox {

	public SkipBox(String colour) {
		super(colour); 
	}

	@Override
	public Question getQuestion() {
		return new SkipQuestion();
	}

	

}

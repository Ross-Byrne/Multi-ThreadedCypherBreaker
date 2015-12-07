package ie.gmit.sw;

public class PoisonResult implements Resultable{
	
	private Resultable result = null;
	
	PoisonResult(){
		
		this.result = new Result("", 0, 0);
	}

	public String getPlainText() {
		
		return result.getPlainText();
	}

	public void setPlainText(String plainText) {
		result.setPlainText(plainText);
	}

	public int getKey() {
		
		return result.getKey();
	}

	public void setKey(int key) {
		
		result.setKey(key);
	}

	public double getScore() {
		
		return result.getScore();
	}
	
	public void setScore(double score) {
		
		result.setScore(score);
	}

} // class
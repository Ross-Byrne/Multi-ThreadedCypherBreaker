package ie.gmit.sw;

// a Poison Result that is used to poison the result queue
// Poison Result implements resultable and contains an instance of Result
// It then delegates results methods

public class PoisonResult implements Resultable {
	
	// holds an instance of resultable
	private Resultable result = null;
	
	// Constructor
	PoisonResult(){
		
		// poisonResult has an instance of result
		this.result = new Result("", 0, 0);
	} // PoisonResult()

	public String getPlainText() {
		
		return result.getPlainText();
	} // getPlainText()

	public void setPlainText(String plainText) {
		
		result.setPlainText(plainText);
	} // setPlainText()

	public int getKey() {
		
		return result.getKey();
	} // getKey()

	public void setKey(int key) {
		
		result.setKey(key);
	} // setKey()

	public double getScore() {
		
		return result.getScore();
	} // getScore()
	
	public void setScore(double score) {
		
		result.setScore(score);
	} // setScore()

} // class

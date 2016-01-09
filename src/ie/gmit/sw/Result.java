package ie.gmit.sw;

// Class to store the result after an encrypted message is attempted to be broken

public class Result implements Resultable {

	private String plainText;
	private int key;
	private double score;
	
	// Constructor
	public Result(String plainText, int key, double score){
		
		this.plainText = plainText;
		this.key = key;
		this.score = score;
		
	} // Result()
	
	public String getPlainText() {
		
		return plainText;
	} // getPlainText()

	public void setPlainText(String plainText) {
		
		this.plainText = plainText;
	} // setPlainText()

	public int getKey() {
		
		return key;
	} // getKey()

	public void setKey(int key) {
		
		this.key = key;
	} // setKey()

	public double getScore() {
		
		return score;
	} // getScore()

	public void setScore(double score) {
		
		this.score = score;
	} // setScore()

} // class

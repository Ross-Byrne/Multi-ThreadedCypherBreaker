package ie.gmit.sw;

// Class to store the result after an encrypted message is attempted to be broken

/**
 * Stores the result after an encrypted message is attempted to be broken.
 * 
 * @author Ross Byrne
 *
 */

public class Result implements Resultable {

	/*============================= Member Variables =============================*/
	
	private String plainText;
	private int key;
	private double score;
	
	
	/*============================= Constructors =============================*/
	/**
	 * Constructor for Result
	 * 
	 * @param plainText The text that has been decrypted.
	 * @param key The key used to decrypt the Cypher Text
	 * @param score The score given to the plainText, scoring how English it is.
	 */
	
	public Result(String plainText, int key, double score){
		
		this.plainText = plainText;
		this.key = key;
		this.score = score;
		
	} // Result()
	
	
	/*============================= Methods =============================*/
	// interface's JavaDocs explain methods
	
	/*============================= getPlainText() =============================*/
	
	public String getPlainText() {
		
		return plainText;
	} // getPlainText()

	
	/*============================= setPlainText() =============================*/
	
	public void setPlainText(String plainText) {
		
		this.plainText = plainText;
	} // setPlainText()

	
	/*============================= getKey() =============================*/
	
	public int getKey() {
		
		return key;
	} // getKey()

	
	/*============================= setKey() =============================*/
	
	public void setKey(int key) {
		
		this.key = key;
	} // setKey()

	
	/*============================= getScore() =============================*/
	
	public double getScore() {
		
		return score;
	} // getScore()

	
	/*============================= setScore() =============================*/
	
	public void setScore(double score) {
		
		this.score = score;
	} // setScore()

} // class

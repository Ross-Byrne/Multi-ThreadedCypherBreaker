package ie.gmit.sw;

// a Poison Result that is used to poison the result queue
// Poison Result implements resultable and contains an instance of Result
// It then delegates results methods

/**
 * A Poison Result that is used to poison the result blocking queue.
 * Holds an instance of Result as a Member Variable and delegates Results methods.
 * 
 * @author Ross Byrne
 *
 */

public class PoisonResult implements Resultable {
	
	/*============================= Member Variables =============================*/
	
	// holds an instance of resultable
	private Resultable result = null;
	
	
	/*============================= Constructors =============================*/
	/**
	 * Constructor for PoisonResult, creates a new instance of Result.
	 */
	
	PoisonResult(){
		
		// poisonResult has an instance of result
		this.result = new Result("", 0, 0);
	} // PoisonResult()

	
	/*============================= Methods =============================*/
	// interface's JavaDocs explain methods
	
	/*============================= getPlainText() =============================*/

	public String getPlainText() {
		
		return result.getPlainText();
	} // getPlainText()
	
	
	/*============================= setPlainText() =============================*/

	public void setPlainText(String plainText) {
		
		result.setPlainText(plainText);
	} // setPlainText()

	
	/*============================= getKey() =============================*/
	
	public int getKey() {
		
		return result.getKey();
	} // getKey()

	
	/*============================= setKey() =============================*/
	
	public void setKey(int key) {
		
		result.setKey(key);
	} // setKey()

	
	/*============================= getScore() =============================*/
	
	public double getScore() {
		
		return result.getScore();
	} // getScore()
	
	
	/*============================= setScore() =============================*/
	
	public void setScore(double score) {
		
		result.setScore(score);
	} // setScore()

} // class

 package ie.gmit.sw;

// An Interface for a result after an encrypted message is attempted to be broken

/**
 * An Interface for a result after an encrypted message is attempted to be broken.
 * 
 * @author Ross Byrne
 *
 */

public interface Resultable {

	/*============================= Abstract Interface Methods =============================*/
	
	/*============================= getPlainText() =============================*/
	/**
	 * Gets the PlainText from the result.
	 * 
	 * @return The Plain Text from the Result as a String.
	 */
	
	public abstract String getPlainText();

	
	/*============================= setPlainText() =============================*/
	/**
	 * Sets the plainText for the result.
	 * 
	 * @param plainText The plainText you wish to add to the result.
	 */
	
	public abstract void setPlainText(String plainText);

	
	/*============================= getKey() =============================*/
	/**
	 * Gets the Key used to decrypt the Cypher Text.
	 * 
	 * @return The key used to decrypt the cypher text.
	 */
	
	public abstract int getKey();

	
	/*============================= setKey() =============================*/
	/**
	 * Sets the key that was used to decrypt the Cypher Text
	 * 
	 * @param key The key used to decrypt the message.
	 */
	
	public abstract void setKey(int key);

	
	/*============================= getScore() =============================*/
	/**
	 * Gets the score given to the result.
	 * 
	 * @return The score given to the result.
	 */
	
	public abstract double getScore();

	
	/*============================= setScore() =============================*/
	/**
	 * Sets the score given to the result.
	 * 
	 * @param score The score given to the result.
	 */
	
	public abstract void setScore(double score);

} // interface
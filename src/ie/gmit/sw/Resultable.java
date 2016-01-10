package ie.gmit.sw;

// An Interface for a result after an encrypted message is attempted to be broken

/**
 * An Interface for a result after an encrypted message is attempted to be broken.
 * 
 * @author Ross Byrne
 *
 */

public interface Resultable {

	public abstract String getPlainText();

	public abstract void setPlainText(String plainText);

	public abstract int getKey();

	public abstract void setKey(int key);

	public abstract double getScore();

	public abstract void setScore(double score);

} // interface
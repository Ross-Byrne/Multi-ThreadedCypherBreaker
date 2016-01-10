package ie.gmit.sw;

import java.util.concurrent.*;
import java.util.*;

// A Runnable thats single job is to decrypt the encrypted text with the key given to it

/**
 * A Runnable thats single job is to decrypt encrypted text with the key given to it.
 * 
 * @author Ross Byrne
 *
 */

public class Decryptor implements Runnable { // producer
	
	/*============================= Member Variables =============================*/
	
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private int key;
	private Map<String, Double> quadGramMap;
	
	
	/*============================= Constructors =============================*/
	/**
	 * Constructor for Decryptor
	 * 
	 * @param queue The Blocking Queue that the results are put on.
	 * @param cypherText The encrypted Text that is being decrypted.
	 * @param key The key given to the Decryptor to decrypt the cypher text.
	 * @param quadGramMap A Quad Gram Map used to score how English the decrypted text is.
	 */
	
	public Decryptor(BlockingQueue<Resultable> queue, String cypherText, int key, Map<String, Double> quadGramMap) {
		
		super();
		this.queue = queue;
		this.cypherText = cypherText;
		this.key = key;
		this.quadGramMap = quadGramMap;
		
	} // Decryptor()

	
	/*============================= Methods =============================*/
	
	/*============================= run() =============================*/
	/**
	 * Run Method for the Decryptor Thread.
	 * Thread Decrypts the cypher text with the key given to it.
	 * It then scores the decrypted text with a QuadGram Map to see how English it is.
	 * It the  puts the result on the blocking queue to be sorted.
	 */
	
	public void run(){
		
		// create a RailFence Object to decrypt cypherText
		RailFence rf = new RailFence();
		
		// create a TextScorer to score the Englishness of the decrypted cypher text
		TextScorer textScorer = new TextScorer(quadGramMap);
		
		// decrypt the cypher text with the key given
		String plainText = rf.decrypt(cypherText, key);
		
		// score the decrypted plaintext
		double score = textScorer.getScore(plainText);
		
		// create a result
		Resultable r = new Result(plainText, key, score); 
		
		try {
			//System.out.println("Putting result on the queue");
			
			// put the result on the queue to be sorted
			queue.put(r);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // try catch
	
	} // run()
	
} // class

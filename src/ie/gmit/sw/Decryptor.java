package ie.gmit.sw;

import java.util.concurrent.*;
import java.util.*;

// A Runnable thats single job is to decrypt the encrypted text with the key given to it

public class Decryptor implements Runnable { // producer
	
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private int key;
	private Map<String, Double> quadGramMap;
	
	// Constructor
	public Decryptor(BlockingQueue<Resultable> queue, String cypherText, int key, Map<String, Double> quadGramMap) {
		
		super();
		this.queue = queue;
		this.cypherText = cypherText;
		this.key = key;
		this.quadGramMap = quadGramMap;
		
	} // Decryptor()

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

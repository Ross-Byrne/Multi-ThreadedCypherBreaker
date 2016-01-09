package ie.gmit.sw;

import java.util.concurrent.*;
import java.util.*;

public class Decryptor implements Runnable { // producer
	
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private int key;
	private Map<String, Double> quadGramMap;
	
	public Decryptor(BlockingQueue<Resultable> queue, String cypherText, int key, Map<String, Double> quadGramMap) {
		
		super();
		this.queue = queue;
		this.cypherText = cypherText;
		this.key = key;
		this.quadGramMap = quadGramMap;
		
	} // Decryptor()

	public void run(){
		
		RailFence rf = new RailFence();
		TextScorer textScorer = new TextScorer(quadGramMap);
		
		// decrypt the plaintext with the key given
		String plainText = rf.decrypt(cypherText, key);
		
		// score the decrypted plaintext
		double score = textScorer.getScore(plainText);
		
		// create a result
		Resultable r = new Result(plainText, key, score); 
		
		try {
			//System.out.println("Putting result on the queue");
			
			// put the result on the queue
			queue.put(r);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // try catch
	
	} // run()
	
} // class

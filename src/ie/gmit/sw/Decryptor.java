package ie.gmit.sw;

import java.util.concurrent.*;

public class Decryptor implements Runnable { // producer
	
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private int key;
	
	public Decryptor(BlockingQueue<Resultable> queue, String cypherText, int key) {
		
		super();
		this.queue = queue;
		this.cypherText = cypherText;
		this.key = key;
		
	} // Decryptor()

	public void run(){
		
		RailFence rf = new RailFence();
		//TextScorer textScorer = new TextScorer();
		
		String plainText = rf.decrypt(cypherText, key);
		// get source
		
		Resultable r =  null; // new Result(plainText, key, score); // create a result
		
		try {
			
			queue.put(r);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // try catch
	
	} // run()
	
} // class

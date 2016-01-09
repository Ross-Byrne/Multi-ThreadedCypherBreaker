package ie.gmit.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.*;

public class CypherBreaker {
	
	private static final int MAX_QUEUE_SIZE = 100;
	
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private int maxKeyLength;
	private volatile boolean isRunning = true;
	
	// create QuadGramMap
	// parses 4grams.txt into a HashMap
	QuadGramMap quadGramMap = new QuadGramMap();
	
	public CypherBreaker(String cypherText){
		
		this.cypherText = cypherText;
		
		queue = new ArrayBlockingQueue<Resultable>(MAX_QUEUE_SIZE);
		
		init();
	}
	
	public void init(){
		
		// start at load of producers
		
		// calculate max key length, and round it up
		maxKeyLength = (int)Math.ceil(cypherText.length() / 2.0);
		
		System.out.println("\nMaxKeyLength: " + maxKeyLength);
		
		// run a decryptor for every possible key
		for(int i = 2; i <= maxKeyLength; i++){
			
			new Thread(new Decryptor(queue, cypherText, i, quadGramMap.getQuadGramMap())).start();
			
		} // for
		
		new Thread(new Runnable(){
			
			volatile int counter = 0;
			Object lock = new Object();
			
			// variable to store the best - most english - result
			Resultable bestResult;
			
			public void increment(){
				
				synchronized(lock){
					
					//System.out.println("Counter: " + counter);

					// increment counter
					counter++;
					
					// if all threads have been processed
					if(counter == maxKeyLength - 2){ // -2 because key length starts at 2
					
						try {
							
							// poison the blocking queue to stop it
							queue.put(new PoisonResult());
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // try
						
					} // if
					
				} // synchronized()
	
			} // increment()
			
			public void run(){
				
				while(isRunning){
					
					try {
						
						Resultable r = queue.take();
						
						/*System.out.println("Item taken");
						
						System.out.println("\n\nResult Score: " + r.getScore() + 
								"\nKey: " + r.getKey() +
								"\nPlainText: " + r.getPlainText() + "\n\n");*/
						
						// check if the result taken from the queue is a poison result or not
						if(r instanceof PoisonResult){
							
							// result is poison
							
							//System.out.println("Poison!");
							
							// print out the best result recorded
							System.out.println("\nThe Best Result:\n" +
									"\nScore: " + bestResult.getScore() + 
									"\nKey: " + bestResult.getKey() +
									"\nPlainText: " + bestResult.getPlainText() + "\n");
							
							// write broken encryption plainText to a file
							try(PrintWriter writer = new PrintWriter((new BufferedWriter(new FileWriter("brokenEncryptedText.txt"))))){
								
								// write the encrypted text to a file
								writer.write(bestResult.getPlainText());
								
							} catch (IOException e) {
								
								System.out.println("An Error Occured!");
							} // try
							
							// queue is poisoned, loop can end
							isRunning = false;
							
							// break to exit the loop
							break;
						} // if
						
						// check if the result is better then current one
						// if it is, replace current result with it
						
						if(counter == 0){ // if the first result
							
							// no best result yet, create it
							bestResult = new Result(r.getPlainText(), r.getKey(), r.getScore());
							
						} else { // otherwise
							
							// check if result has better score then the bestResult score
							
							if(r.getScore() > bestResult.getScore()){ // if result score is better then bestResult score
								
								// Update the bestResult with the better result
								bestResult.setPlainText(r.getPlainText());
								bestResult.setKey(r.getKey());
								bestResult.setScore(r.getScore());
								
							} // if
						} // if
						
						// increment the number of results taken from the queue
						this.increment();
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					} // try catch
					
				} // while	
				
			} // run()
			
		}).start();
		
	} // init()
	
	public boolean getIsRunning(){
		
		return isRunning;
	} // getIsRunning()
} // class

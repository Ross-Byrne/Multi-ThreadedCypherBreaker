package ie.gmit.sw;

import java.io.*;
import java.util.concurrent.*;

// A Runnable thats single job is to sort the queue of results and find the best scoring one.

/**
 * A Runnable thats single job is to sort the queue of results and find the best scoring result.
 * 
 * @author Ross Byrne
 *
 */

public class ResultSorter implements Runnable{
	
	/*============================= Member Variables =============================*/
	
	private volatile int counter = 0;
	private volatile static boolean isRunning = true;
	private Object lock = new Object();
	private static final String BROKEN_ENCRYPTED_TEXT_FILENAME = "brokenEncryptedText.txt";
	
	private BlockingQueue<Resultable> queue;
	private int maxKeyLength;
	
	// variable to store the best - most English - result
	private Resultable bestResult;
	
	
	/*============================= Constructors =============================*/
	/**
	 * 
	 * @param queue The blocking Queue that holds the results.
	 * @param maxKeyLength The max length the cypher key can be for the RailFence cypher to work correctly.
	 */
	
	ResultSorter(BlockingQueue<Resultable> queue, int maxKeyLength){
		
		this.queue = queue;
		this.maxKeyLength = maxKeyLength;
		
	} // ResultSorter()
	
	
	/*============================= Methods =============================*/
	
	/*============================= increment() =============================*/
	/**
	 * Counts the number of results taken off the queue, uses maxKeyLength to calculate number of results.
	 * When the last result is taken off the queue, a poison result is added to poison the blocking queue.
	 */
	
	public void increment(){
		
		synchronized(lock){
			
			//System.out.println("Counter: " + counter);

			// increment counter
			counter++;
			
			// if all results from the threads have been processed
			if(counter == maxKeyLength - 2){ // -2 because key length starts at 2
			
				try {
					
					// poison the blocking queue to stop it
					queue.put(new PoisonResult());
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				} // try
				
			} // if
			
		} // synchronized()

	} // increment()
	
	
	/*============================= run() =============================*/
	/**
	 * Run method to run the thread.
	 * Takes a result off the queue, checks if its a poisonResult.
	 * If it's a poisonResult, no results are left,  print out the best result and end.
	 * If it's a normal result, check if the results score is better then the current best result.
	 * If the result is better then current best, replace current best with it otherwise do nothing and repeat process.
	 */
	
	public void run(){
		
		// isRunning is true while ResultSorter takes results off the queue and sorts them
		while(isRunning){
			
			try {
				
				// take a result off the queue
				Resultable r = queue.take();
				
				/*System.out.println("Item taken");
				
				System.out.println("\n\nResult Score: " + r.getScore() + 
						"\nKey: " + r.getKey() +
						"\nPlainText: " + r.getPlainText() + "\n\n");*/
				
				// check if the result taken from the queue is a poison result or not
				if(r instanceof PoisonResult){
					
					// result is poisoned
					
					//System.out.println("Poison!");
					
					// print out the best result recorded
					System.out.println("\nThe Best Result:\n" +
							"\nScore: " + bestResult.getScore() + 
							"\nKey: " + bestResult.getKey() +
							"\nPlainText: " + bestResult.getPlainText() + "\n");
					
					// write broken encrypted plainText to a file called brokenEncryptedText.txt
					try(PrintWriter writer = new PrintWriter((new BufferedWriter(new FileWriter(BROKEN_ENCRYPTED_TEXT_FILENAME))))){
						
						// write the encrypted text to a file
						writer.write(bestResult.getPlainText());
						
					} catch (IOException e) {
						
						System.out.println("An Error Occured!");
					} // try
					
					// tell user broken decrypted text is saved to a file
					System.out.println("Decrypted PlainText printed to File: " + BROKEN_ENCRYPTED_TEXT_FILENAME);
					
					// queue is poisoned, loop can end
					isRunning = false;
					
					// break to exit the loop
					break;
				} // if
				
				// check if the result is better then current one
				// if it is, replace current result with it
				
				// if the first result
				if(counter == 0){ 
					
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
	
	
	/*============================= getIsRunning() =============================*/
	/**
	 * Gets the isRunning variable.
	 * The thread keeps sorting results until it's finished and isRunning is set to false.
	 * The thread ResultSorter will then end when isRunning is set to false.
	 * 
	 * @return boolean value of isRunning.
	 */
	
	public static boolean getIsRunning(){
		
		return isRunning;
	} // getIsRunning()

} // class

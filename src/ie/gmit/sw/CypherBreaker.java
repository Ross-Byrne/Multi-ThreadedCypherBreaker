package ie.gmit.sw;

import java.util.concurrent.*;

// class that handles the breaking of the cypher

/**
 * Handles the breaking of the Cypher Text.
 * 
 * @author Ross Byrne
 *
 */

public class CypherBreaker {
	
	/*============================= Member Variables =============================*/
	
	private static final int MAX_QUEUE_SIZE = 100;
	
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private int maxKeyLength;
	
	// create QuadGramMap
	// parses 4grams.txt into a HashMap
	QuadGramMap quadGramMap = new QuadGramMap();
	
	
	/*============================= Constructors =============================*/
	/**
	 * Constructor for CypherBreaker.
	 * Creates the queue that the results will be added to.
	 * Runs the init() method to start the cypher breaking
	 * 
	 * @param cypherText The encrypted text that is going to have its encryption broken.
	 */
	
	public CypherBreaker(String cypherText){
		
		this.cypherText = cypherText;
		
		// create the queue that will hold all of the results
		queue = new ArrayBlockingQueue<Resultable>(MAX_QUEUE_SIZE);
		
		// initialise the CypherBreaker
		init();
		
	} // CypherBreaker()
	
	
	/*============================= Methods =============================*/
	
	/*============================= init() =============================*/
	/**
	 * Initialises the cypherBreaker.
	 * 
	 * Creates a thread for every possible cypher key and gets each thread to decrypt the cypher text with its given key.
	 * Then creates the result sorter thread to sort the results of the decryptions.
	 */
	
	public void init(){
		
		// calculate max key length, and round it up
		maxKeyLength = (int)Math.ceil(cypherText.length() / 2.0);
		
		System.out.println("\nMaxKeyLength: " + maxKeyLength);
		
		// run a decryptor for every possible key
		for(int i = 2; i <= maxKeyLength; i++){
			
			// create a new Decryptor Thread
			new Thread(new Decryptor(queue, cypherText, i, quadGramMap.getQuadGramMap())).start();
			
		} // for
		
		// create the ResultSorter Thread
		Thread resultSorter = new Thread(new ResultSorter(queue, maxKeyLength));
		
		// start resultSorter thread
		resultSorter.start();
	
	} // init()
	
	
	/*============================= getIsRunning() =============================*/
	/**
	 * Gets the value of isRunning from the ResultSorter Thread.
	 * If ResultSorter isn't running, then it has a best result from the broken encryption attempts.
	 * 
	 * It gets the value from ResultSorter Thread because if the resultSorter is finished, the CypherBreaker is finished too.
	 * 
	 * @return the boolean variable isRunning from the resultSorter Thread.
	 */
	
	public boolean getIsRunning(){
		
		return ResultSorter.getIsRunning();
	} // getIsRunning
	
} // class

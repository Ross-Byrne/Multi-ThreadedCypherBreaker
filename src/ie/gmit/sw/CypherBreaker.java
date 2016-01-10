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
	
	private static final int MAX_QUEUE_SIZE = 100;
	
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private int maxKeyLength;
	
	// create QuadGramMap
	// parses 4grams.txt into a HashMap
	QuadGramMap quadGramMap = new QuadGramMap();
	
	// Constructor
	public CypherBreaker(String cypherText){
		
		this.cypherText = cypherText;
		
		// create the queue that will hold all of the results
		queue = new ArrayBlockingQueue<Resultable>(MAX_QUEUE_SIZE);
		
		// initialise the CypherBreaker
		init();
		
	} // CypherBreaker()
	
	// initialises the cypherBreaker
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
	
	// returns the value of IsRunning from the resultSorter thread
	// because if the result sorter isn't running, the cypherBreaker isn't running as far a the user knows
	public boolean getIsRunning(){
		
		return ResultSorter.getIsRunning();
	} // getIsRunning
	
} // class

package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;

public class CypherBreaker {
	
	private static final int MAX_QUEUE_SIZE = 100;
	
	private PriorityBlockingQueue<Resultable> queue;
	private String cypherText;
	private int maxKeyLength;
	private boolean isRunning = true;
	
	// create QuadGramMap
	// parses 4gram.txt into a HashMap
	QuadGramMap quadGramMap = new QuadGramMap();
	
	public CypherBreaker(String cypherText){
		
		this.cypherText = cypherText;
		
		queue = new PriorityBlockingQueue<Resultable>(MAX_QUEUE_SIZE);
		
		init();
	}
	
	public void init(){
		
		// start at load of producers
		
		maxKeyLength = cypherText.length() / 2;
		
		System.out.println("MaxKeyLength: " + maxKeyLength);
		
		for(int i = 2; i < maxKeyLength; i++){
			
			new Thread(new Decryptor(queue, cypherText, i, quadGramMap.getQuadGramMap())).start();
		}
		
		new Thread(new Runnable(){
			
			volatile int counter = 0;
			Object lock = new Object();
			
			public void increment(){
				
				synchronized(lock){
					
					System.out.println("Counter: " + counter);

					counter++;
					
					// if all threads have been processed
					if(counter == maxKeyLength - 2){ // -2 because key length starts at 2
					
						// poison the blocking queue to stop it
						queue.put(new PoisonResult());
						
					} // if
					
				} // synchronized()
	
			} // increment()
			
			public void run(){
				
				while(isRunning){
					
					System.out.println("In while");
					
					try {
						
						Resultable r = queue.take();
						
						System.out.println("Item taken");
						
						System.out.println("\n\nResult Score: " + r.getScore() + 
								"\nKey: " + r.getKey() +
								"\nPlainText: " + r.getPlainText() + "\n\n");
						
						if(r instanceof PoisonResult){
							
							System.out.println("Poison!");
							
							return;
						} // if
						
						// increment the number of results taken from the queue
						this.increment();
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // try catch
					
				} // while	
				
			} // run()
			
		}).start();
		
	} // init()
} // class

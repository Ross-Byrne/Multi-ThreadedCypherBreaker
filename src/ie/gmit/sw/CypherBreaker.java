package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;

public class CypherBreaker {
	
	private static final int MAX_QUEUE_SIZE = 100;
	
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	
	// create QuadGramMap
	// parses 4gram.txt into a HashMap
	QuadGramMap quadGramMap = new QuadGramMap();
	
	public CypherBreaker(String cypherText){
		
		this.cypherText = cypherText;
		
		queue = new ArrayBlockingQueue<Resultable>(MAX_QUEUE_SIZE);
		
		init();
	}
	
	public void init(){
		
		// start at load of producers
		
		for(int i = 2; i < cypherText.length() / 2; i++){
			
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
					if(counter == MAX_QUEUE_SIZE){
						
						try {
							
							// poison the blocking queue to stop it
							queue.put(new PoisonResult());
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						} 
					} // if
					
				} // synchronized()
	
			} // increment()
			
			public void run(){
				
				while(!queue.isEmpty()){
					
					System.out.println("In while");
					
					try {
						
						Resultable r = queue.take();
						
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
				
				System.out.println("Finished!");
				
			} // run()
			
		}).start();
		
	} // init()
} // class

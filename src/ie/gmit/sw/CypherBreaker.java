package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;

public class CypherBreaker {
	
	private static final int MAX_QUEUE_SIZE = 100;
	
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	
	public CypherBreaker(String cypherText){
		
		this.cypherText = cypherText;
		
		queue = new ArrayBlockingQueue<Resultable>(MAX_QUEUE_SIZE);
		
		init();
	}
	
	public void init(){
		
		// start at load of producers
		
		for(int i = 2; i < cypherText.length() / 2; i++){
			
			new Thread(new Decryptor(queue, cypherText, i)).start();
		}
		
		new Thread(new Runnable(){
			
			volatile int counter = 0;
			Object lock = new Object();
			
			public void increment(){
				
				synchronized(lock){

					counter++;
					
					if(counter == MAX_QUEUE_SIZE){
						
						//queue.put(new PoisonResult); // make a poisonResult Object
					} // if
					
				} // synchronized()
	
			} // increment()
			
			public void run(){
				
				while(!queue.isEmpty()){
					
					try {
						
						Resultable r = queue.take();
						
						if(r instanceof Result){
							
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

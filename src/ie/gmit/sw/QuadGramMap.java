package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;

public class QuadGramMap {
	
	public static final int GRAM_SIZE = 4;
	private static final String FILE_NAME = "4gram.txt";
	private Map<String, Double> map; 
	
	// constructor that creates an instance of FileParser, reads the 4grams.txt file into the hashmap
	public QuadGramMap(){
		
		FileParser parser = new FileParser();
		
		try {
			
			this.map = new ConcurrentHashMap<String, Double>(parser.parse(FILE_NAME));
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			System.out.println("Could Not Read QuadGram File!");
		} // try catch
		
	} // QuadGramMap()
	
} // class

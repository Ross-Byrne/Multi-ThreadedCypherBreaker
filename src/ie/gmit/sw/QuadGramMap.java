package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;

// Class that holds the Quad Gram map used to score how Engish a message is

public class QuadGramMap {
	
	public static final int GRAM_SIZE = 4;
	private static final String FILE_NAME = "4grams.txt";
	private Map<String, Double> map; 
	
	// constructor that creates an instance of FileParser, reads the 4grams.txt file into the hashmap
	public QuadGramMap(){
		
		// create a parser to parse the quad grams from a file into a hashmap
		FileParser parser = new FileParser();
		
		try {
			
			// this map is ConcurrentHashMap made with map returned by parser
			this.map = new ConcurrentHashMap<String, Double>(parser.parse(FILE_NAME));
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			System.out.println("Could Not Read QuadGram File!");
		} // try catch
		
	} // QuadGramMap()
	
	// returns the quadGramMap
	public Map<String, Double> getQuadGramMap(){
		
		return this.map;
	} // getQuadGramMap()
	
} // class

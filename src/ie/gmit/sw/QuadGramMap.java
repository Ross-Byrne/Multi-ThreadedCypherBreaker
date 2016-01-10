package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;

// Class that holds the Quad Gram map used to score how English a message is

/**
 * Holds the Quad Gram Map that is used to score how English a message is.
 * 
 * @author Ross Byrne
 *
 */

public class QuadGramMap {
	
	/*============================= Member Variables =============================*/
	
	public static final int GRAM_SIZE = 4;
	private static final String FILE_NAME = "4grams.txt";
	private Map<String, Double> map; 
	
	
	/*============================= Constructors =============================*/
	/**
	 * Constructor for QuadGramMap, creates a map and parses the Quad Gram File into it.
	 */
	
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
	
	
	/*============================= Methods =============================*/
	
	/*============================= getQuadGramMap() =============================*/
	/**
	 * Gets the Quad Gram Map that contains the Quad Grams.
	 * 
	 * @return The Map that contains the Quad Grams as String / Double - Key / Value.
	 */
	
	public Map<String, Double> getQuadGramMap(){
		
		return this.map;
	} // getQuadGramMap()
	
} // class

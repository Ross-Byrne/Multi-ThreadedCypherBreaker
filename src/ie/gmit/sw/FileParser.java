package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;

/**
 * Parses the file containing Quad Grams into A Map.
 * 
 * @author Ross Byrne
 *
 */

public class FileParser {
	
	/*============================= Methods =============================*/
	
	/*============================= parse() =============================*/
	/**
	 * Parses the contents of a file into a map.
	 * Lines of text in the file must contain a String and a Double/Integer.
	 * The two values are split at the space and added to the map as a key and value
	 * 
	 * @param file The name of the file that is going to be parsed.
	 * @return A map containing a String / Double as a key / value .
	 * @throws Exception Will throw an Exception if the file does not exist.
	 */
	
	public Map<String, Double> parse(String file) throws Exception{
		
		Map<String, Double> map = new ConcurrentHashMap<String, Double>();
		
		// create a file to represent the file that is going to be parsed
		File fileToParse = new File(file);
		
		// check if file exists
		if(!fileToParse.exists()){
			
			// file does not exist, create it so program does not crash!
			fileToParse.createNewFile();
			
			// tell user file was not found
			System.out.println("Error! " + fileToParse.getName() + " does not exist! Empty file created to avoid crash!");
		} // if
		
		BufferedReader br= new BufferedReader(new InputStreamReader(new FileInputStream(fileToParse)));
		String next= null;
		
		// read a line at a time
		while((next = br.readLine()) != null){
			
			// ADD EACH LINE TO THE PARSE
			
			// split the line at the space
			String [] stuff = next.split(" ");
			
			// add the quadgram string and the double value to the map
			map.put(stuff[0].toString(), new Double(stuff[1]));
		
		} // while
		
		// close the buffered line reader
		br.close();
		
		// return the quadgram map
		return map;
		
	} // parse()
	
} // class

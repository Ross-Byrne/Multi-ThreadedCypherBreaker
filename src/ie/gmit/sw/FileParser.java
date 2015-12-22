package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;

public class FileParser {
	
	//put into its own class
	public Map<String, Double> parse(String file) throws Exception{
		
		Map<String, Double> map = new ConcurrentHashMap<String, Double>();
		
		BufferedReader br= new BufferedReader(new InputStreamReader(new FileInputStream(file)));
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

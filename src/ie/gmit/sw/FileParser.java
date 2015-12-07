package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;

public class FileParser {
	
	//put into its own class
	public Map<String, Double> parse(String file) throws Exception{
		
		Map<String, Double> map = new HashMap<String, Double>();
		
		BufferedReader br= new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String next= null;
		
		while((next=br.readLine())!=null){
			
			// ADD EACH LINE TO THE PARSE
			String [] stuff = next.split(" ");
			//map.put(stuff[0]),stuff[1]);
				
		} // while
		
		return map;
	} // parse()
} // class

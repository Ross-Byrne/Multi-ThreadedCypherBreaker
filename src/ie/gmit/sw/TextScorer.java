package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;

// Class for scoring how English a message is

/**
 * 
 * Scores how English a message is using a Quad Gram.
 *
 */

public class TextScorer {
	
	private Map<String, Double> map = new ConcurrentHashMap<String, Double>();
	
	// Constructor
	public TextScorer(Map<String, Double> m){
		
		this.map = m;
	} // TextScorer()
	
	// gets the score
	public double getScore(String text){
		
		double score = 0f;

		for (int i = 0; i < text.length(); i++){
			
			if (i + QuadGramMap.GRAM_SIZE <= text.length() -1){
				
				score += computeLogScore(text.substring(i, i + 4).toUpperCase());	
				
			} // if
		} // for
		
		return score;
		
	} // getScore()
	
	public double computeLogScore(String quadgram){
		
		if (map.containsKey(quadgram)){
			
			double frequency = map.get(quadgram);
			double total = (double) map.size();
			double probability = (double) (frequency/total);
			
			return Math.log10(probability);
			
		}else{
			
			return 0f;
		} // if
		
	} // computeLogScore()
	
} // class

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
	
	/*============================= Member Variables =============================*/
	
	private Map<String, Double> map = new ConcurrentHashMap<String, Double>();
	
	
	/*============================= Constructors =============================*/
	/**
	 * TextScorer Constructor.
	 * 
	 * @param m A map containing QuadGrams that will be used to score text.
	 */
	
	public TextScorer(Map<String, Double> m){
		
		this.map = m;
	} // TextScorer()
	
	
	/*============================= Methods =============================*/
	
	/*============================= getScore() =============================*/
	/**
	 * Scores Text using a Quad Gram Map to see how English the text is.
	 * 
	 * @param text The String of text you want to score.
	 * @return The score the TextScorer gives to the text.
	 */
	
	public double getScore(String text){
		
		double score = 0f;

		for (int i = 0; i < text.length(); i++){
			
			if (i + QuadGramMap.GRAM_SIZE <= text.length() -1){
				
				score += computeLogScore(text.substring(i, i + 4).toUpperCase());	
				
			} // if
		} // for
		
		return score;
		
	} // getScore()
	
	
	/*============================= computeLogScore() =============================*/
	/**
	 * Computes the score by getting the log of the frequency of a Quad Gram divided by the total number of Quad Grams in the Map.
	 * 
	 * @param quadgram Quad Gram from the Quad Gram Map.
	 * @return The log10 of the frequency of the Quad Gram divided by the total number of QuadGrams.
	 */
	
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

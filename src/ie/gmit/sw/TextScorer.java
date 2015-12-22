package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;

public class TextScorer {
	
	private Map<String, Double> map = new ConcurrentHashMap<String, Double>();
	
	public TextScorer(Map<String, Double> m){
		
		this.map = m;
	} // TextScorer()
	
	public double getScore(String text){
		
		double score = 0f;

		for (int i = 0; i < text.length(); i++){
			
			if (i + QuadGramMap.GRAM_SIZE <= text.length() -1){
				
				score += computeLogScore(text.substring(i, i + 4));
				
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

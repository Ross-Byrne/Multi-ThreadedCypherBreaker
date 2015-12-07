package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;

public class TextScorer {
	private Map<String, Double> map = new ConcurrentHashMap<String, Double>();
	
	//put into its own class
	/*public  Map<String, Double> parse(String dile){
		BufferedReader br= new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String next= null;
		
		while((next=br.readLine())!=null){
				// ADD EACH LINE TO THE PARSE
				String [] stuff = next.split(" ");
				map.put(Stuff[0]),stuff[1]);
		}
		
	}*/
	
	public TextScorer(Map<String, Double> m){
		this.map = m;
	}
	
	public double getScore(String text){
		double score = 0f;

		for (int i = 0; i < text.length(); i++){
			if (i + QuadGramMap.GRAM_SIZE <= text.length() -1){
				score += computeLogScore(text.substring(i, i + 4));
			}
		}
		return score;
	}
	
	public double computeLogScore(String quadgram){
		if (map.containsKey(quadgram)){
			double frequency = map.get(quadgram);
			double total = (double) map.size();
			double probability = (double) (frequency/total);
			
			return Math.log10(probability);
		}else{
			return 0f;
		}
	}
}

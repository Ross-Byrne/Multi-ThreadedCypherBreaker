package ie.gmit.sw;

public class Result implements Resultable, Comparable<Resultable>{

	private String plainText;
	private int key;
	private double score;
	
	
	public Result(String plainText, int key, double score){
		this.plainText = plainText;
		this.key = key;
		this.score = score;
		
	}
	
	
	public String getPlainText() {
		return plainText;
	}


	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}


	public int getKey() {
		return key;
	}


	public void setKey(int key) {
		this.key = key;
	}


	public double getScore() {
		return score;
	}


	public void setScore(double score) {
		this.score = score;
	}

	
	// compares results based on their score
	// this is for the PriorityBlockingQueue
	
	public int compareTo(Resultable result) {
		
		return Double.compare(this.getScore(), result.getScore());
		
	} // compareTo()

} // class

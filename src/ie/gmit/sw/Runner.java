package ie.gmit.sw;

// Main Runner Class With Main()

public class Runner {
	
	private static CypherBreaker cypherBreaker;

	public static void main(String[] args) {
		
		// get user to input plain text to encrypt
		// or encrypted text to break
		
		// create the Cypher breaker which will start the program off
		cypherBreaker = new CypherBreaker(new RailFence().encrypt("HelloHowareyoudoing?", 5));
		
		
		

	} // main()

} // class

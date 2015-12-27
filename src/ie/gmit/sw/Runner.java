package ie.gmit.sw;

import java.util.*;

// Main Runner Class With Main()

public class Runner {
	
	public static void main(String[] args) {
		
		CypherBreaker cypherBreaker;
		Scanner scanner = new Scanner(System.in);
		
		String usersEncryptedText = "";
		String enteredPlainText = "";
	
		int menuChoice = 0;
		
		// main program loop
		
		while(menuChoice != 99){
			
			do{
				
				// Print out Menu Options
				System.out.println("\n1.) Encrypt Text.");
				System.out.println("2.) Break Encryped Text.");
				System.out.println("3.) Exit.");
				
				System.out.print("\nEnter Option:");
				
				while(!scanner.hasNextInt()){
					
					System.out.print("Enter Option:");
					scanner.next(); // to advance Scanner past input
					
				} // while
				
				menuChoice = scanner.nextInt();
			
			}while(menuChoice < 1 || menuChoice > 3);
			
			switch(menuChoice){
				case 1: // encrypt Text
					
					scanner.nextLine(); // flush buffer
					
					System.out.print("\nEnter Text You Want To Encrypt: ");
					enteredPlainText = scanner.nextLine();
					
					// remove all spaces
					enteredPlainText = new String(enteredPlainText.replaceAll(" ", ""));
					
					// calculate max key length
					int maxKeyLength = (int)Math.ceil(enteredPlainText.length() / 2.0); // round up
					
					System.out.print("\nEnter Encryption Key. Must be between 2 and " + maxKeyLength + ": ");
					int key = scanner.nextInt();
					
					// encrypt the users plainText
					usersEncryptedText = new RailFence().encrypt(enteredPlainText, key);
					
					System.out.println("PlainText Encrypted.");
					System.out.println("Encryped Text: " + usersEncryptedText);
					
					break;
				case 2: // Break Encrypted Text
					
					if(usersEncryptedText.length() > 2){
						
						// create the Cypher breaker which will start the program off
						cypherBreaker = new CypherBreaker(usersEncryptedText);
						
					} else {
						
						System.out.println("\nYou must have encrypted text to try break!");
						System.out.println("Encrypt some text!");
						
					} // if
					
					break;
				case 3: // exit
					
					// set menuChoice to 99 to end program loop
					menuChoice = 99;
					
					break;
			} // switch
			
		} // while
		
		System.out.println("\n\nProgram Ended.\n\n");
		
	} // main()

} // class

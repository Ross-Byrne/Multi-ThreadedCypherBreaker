package ie.gmit.sw;

import java.util.*;

// Main Runner Class With Main()

public class Runner {
	
	public static void main(String[] args) {
		
		CypherBreaker cypherBreaker;
		Scanner scanner = new Scanner(System.in);
		
		String usersEncryptedText = "";
		String enteredPlainText = "";
		boolean encryptedTextLoaded = false;
	
		int menuChoice = 0;
		
		// main program loop
		
		while(menuChoice != 99){
			
			do{
				// print status
				if(encryptedTextLoaded){ // if encrypted text is loaded
					
					System.out.println("\nStatus: Encrypted Text Loaded. Ready To Break Encryption!");
					
				} else { // if no encrypted text loaded
					
					System.out.println("\nStatus: Encrypted Text Not Loaded. Not Ready!");
				} // if
				
				// Print out Menu Options
				System.out.println("\n1.) Break Encrypted Text.");
				System.out.println("2.) Enter PlainText & Encrypt it.");
				System.out.println("3.) Enter Encrypted Text.");
				System.out.println("4.) Read PlainText From a File & Encrypt it.");
				System.out.println("5.) Read Encrypted Text From a File.");
				System.out.println("6.) Exit.");
				
				System.out.print("\nEnter Option: ");
				
				while(!scanner.hasNextInt()){
					
					System.out.print("Enter Option:");
					scanner.next(); // to advance Scanner past input
					
				} // while
				
				menuChoice = scanner.nextInt();
			
			}while(menuChoice < 1 || menuChoice > 6);
			
			switch(menuChoice){
			case 1: // Break Encrypted Text
				
				// check if the encrypted text is greater then 2 characters long
				if(usersEncryptedText.length() > 2){
					
					// create the Cypher breaker which will start the program off
					cypherBreaker = new CypherBreaker(usersEncryptedText);
					
					// block until the cypherBreaker is finished
					while(cypherBreaker.getIsRunning());
					
				} else { // if not
					
					// tell the user they need encrypted text atleast 3 characters long
					System.out.println("\nYou must have encrypted text atleast 3 characters long!");
					
				} // if
				
				break;
			case 2: // encrypt entered Plain Text
				
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
				
				// encrypted text loaded
				encryptedTextLoaded = true;
				
				break;
			case 3: // enter encrypted text
				
				scanner.nextLine(); // flush buffer
				
				System.out.print("\nEnter Encrypted Text: ");
				usersEncryptedText = scanner.nextLine();
				
				// remove all spaces
				usersEncryptedText = new String(usersEncryptedText.replaceAll(" ", ""));
				
				// output the entered text
				System.out.println("Encrypted Text Entered.");
				System.out.println("Encryped Text: " + usersEncryptedText);
				
				// encrypted text loaded
				encryptedTextLoaded = true;
				
				break;
			case 4: // read plainText from a file and encrypt it
				
				break;
			case 5: // read encrypted text from a file
				
				break;
			case 6: // exit
					
				// set menuChoice to 99 to end program loop
				menuChoice = 99;
					
				break;
			} // switch
			
		} // while
		
		System.out.println("\nProgram Ended.");
		
		// close scanner
		scanner.close();
		
	} // main()

} // class

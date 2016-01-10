package ie.gmit.sw;

import java.io.*;
import java.util.*;

// Main Runner Class With Main()

/**
 * Main class that runs the program.
 * 
 * @author Ross Byrne
 *
 */

public class Runner {
	
	/*============================= Methods =============================*/
	
	/*============================= main() =============================*/
	/**
	 * The Programs main method.
	 * 
	 * @param args An array of String Arguments.
	 */
	
	public static void main(String[] args) {
		
		CypherBreaker cypherBreaker;
		Scanner scanner = new Scanner(System.in);
		
		StringBuilder usersEncryptedText = new StringBuilder();
		StringBuilder enteredPlainText = new StringBuilder();
		boolean encryptedTextLoaded = false;
	
		int menuChoice = 0;
		int key = 0;
		int maxKeyLength = 0;
		String fileName = "";
		
		// message to user
		System.out.println("Multi-Threaded Cypher Breaker.\n");
		System.out.println("PlainText or Encrypted Text Entered ** Cannot ** Contain Puncuation Marks!");
		System.out.println("The broken encryption will not be accurate if Puncuation is included!");
		System.out.println("Spaces can be entered but they will be removed automatically.");
		System.out.println("The longer the encrypted message being broken is, the more accurate it will be.\n");
		
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
					cypherBreaker = new CypherBreaker(usersEncryptedText.toString());
					
					// block until the cypherBreaker is finished
					while(cypherBreaker.getIsRunning());
					
				} else { // if not
					
					// tell the user they need encrypted text atleast 3 characters long
					System.out.println("\nYou must have encrypted text atleast 3 characters long!");
					
				} // if
				
				break;
			case 2: // encrypt entered Plain Text
				
				// clear string builders
				enteredPlainText.setLength(0);
				usersEncryptedText.setLength(0);
				
				scanner.nextLine(); // flush buffer
				
				// save user input
				System.out.print("\nEnter Text You Want To Encrypt: ");
				enteredPlainText.append(scanner.nextLine());
				
				// remove all spaces
				enteredPlainText.replace(0, enteredPlainText.length(), ((enteredPlainText.toString().replaceAll(" ", ""))));
				
				// calculate max key length
				maxKeyLength = (int)Math.ceil(enteredPlainText.length() / 2.0); // round up
				
				// save users input
				System.out.print("\nEnter Encryption Key. Must be between 2 and " + maxKeyLength + ": ");
				key = scanner.nextInt();
				
				// encrypt the users plainText
				usersEncryptedText.append(new RailFence().encrypt(enteredPlainText.toString(), key));
				
				System.out.println("PlainText Encrypted.");
				System.out.println("Encryped Text: " + usersEncryptedText.toString());
				
				// encrypted text loaded
				encryptedTextLoaded = true;
				
				break;
			case 3: // enter encrypted text
				
				// clear string builder
				usersEncryptedText.setLength(0);
				
				// flush buffer
				scanner.nextLine(); 
				
				// save user input
				System.out.print("\nEnter Encrypted Text: ");
				usersEncryptedText.append(scanner.nextLine());
				
				// remove all spaces
				usersEncryptedText.replace(0, usersEncryptedText.length(), (usersEncryptedText.toString().replaceAll(" ", "")));
				
				// output the entered text
				System.out.println("Encrypted Text Entered.");
				System.out.println("Encryped Text: " + usersEncryptedText.toString());
				
				// encrypted text loaded
				encryptedTextLoaded = true;
				
				break;
			case 4: // read plainText from a file and encrypt it
				
				fileName = "";
				
				// clear string builder
				enteredPlainText.setLength(0);
				usersEncryptedText.setLength(0);
				
				// flush buffer
				scanner.nextLine(); 
				
				// save user input
				System.out.print("\nEnter name of text file (including .txt): ");
				fileName = scanner.nextLine();
				
				// read file and save its contents to enteredPlainText
				enteredPlainText.append(readFile(fileName));
				
				// check if file read failed
				if(enteredPlainText.length() != 0){ // if the file read did not fail
					
					// calculate max key length
					maxKeyLength = (int)Math.ceil(enteredPlainText.length() / 2.0); // round up
					
					// save users input
					System.out.print("\nEnter Encryption Key. Must be between 2 and " + maxKeyLength + ": ");
					key = scanner.nextInt();
					
					// encrypt plainText
					usersEncryptedText.append(new RailFence().encrypt(enteredPlainText.toString(), key));
					
					System.out.println("\nPlainText from file " + fileName + " has been Encrypted.");
					System.out.println("Encryped Text: " + usersEncryptedText.toString());
					
					// encrypted text loaded
					encryptedTextLoaded = true;
					
					// write encrypted text to file
					
					/*try(PrintWriter writer = new PrintWriter((new BufferedWriter(new FileWriter("encryptedText.txt"))))){
						
						// write the encrypted text to a file
						writer.write(usersEncryptedText.toString());
						
					} catch (IOException e) {
						
						System.out.println("An Error Occured!");
					} // try*/
					
				} // if
			
				break;
			case 5: // read encrypted text from a file
				
				fileName = "";
				
				// clear string builder
				usersEncryptedText.setLength(0);
				
				// flush buffer
				scanner.nextLine(); 
				
				// save user input
				System.out.print("\nEnter name of text file (including .txt): ");
				fileName = scanner.nextLine();
				
				// read file and save its contents to enteredPlainText
				usersEncryptedText.append(readFile(fileName));
				
				// check if file read failed
				if(usersEncryptedText.length() != 0){ // if file was read successfully
					
					System.out.println("\nEncrypted Text from file " + fileName + " has been Read.");
					System.out.println("Encryped Text: " + usersEncryptedText.toString());
					
					// encrypted text loaded
					encryptedTextLoaded = true;
					
				} // if
				
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
	
	
	/*============================= readFile() =============================*/
	/**
	 * Reads a text file line by line and removes all spaces.
	 * 
	 * @param fileName The name of the file to read.
	 * @return The text read from the file as a String.
	 */
	
	public static String readFile(String fileName){
		
		StringBuilder sb = new StringBuilder();
		
		// gets reference to file
 		File file = new File(fileName);
 		
 		// checks if the file exists
 		if (!file.exists() || !file.isFile()){
 			
 			// print out warning message
 			System.out.println("\nError, " + fileName + " is not a file!");
 			System.out.println("Make sure file is in same directory as railfence.jar!");
 			
 			// file doesn't exist, so exit
 			return "";
 		} // if
 		
 		String line = "";
 		
 		// create a buffered reader to read the file
 		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
 			
 			// loop through the file, reading it line by line
		    while((line = br.readLine()) != null) {
		  
		    	// remove all spaces from the line of text
		    	line = line.replaceAll(" ", "");
		    	
		    	// add the line of text to string builder
		    	sb.append(line);
		    	
		    } // while
		    
		} catch(Exception e){
			
			System.out.println("Could Not load text from file!");
		} // try catch
		
 		// return the contents of the file as a string
		return sb.toString();
	} // readFile()

} // class

# Multi-ThreadedCypherBreaker

A Third Year College Project For Object Oriented Programming Module coded in Java in Eclipse 4.5.1 (Mars.1) 

The program attempts to break a rail fence cypher by trying every possible key and scoring the decrypted text using Quad Grams to see how English the text is. The highest scoring result most likely used the right key to decrypt.

### Note
Punctuation cannot be entered into the program. The program will not be able to accurately break the encryption if punctuation marks are in either the plain text or cypher text. Spaces can be included but they will be removed automatically when the program runs.

The longer the encryption being broken is, the more accurate the program will be. 
With my testing, unless the message is only a few words, it always successfully gets the right key that was used to encrypt the text.

### Using The Program
To use the program, the text file 4grams.txt must be in the same directory as railfence.jar. Otherwise the program will not be able to score the decrypted text.

The program gives the user many options. 

The user can enter plain text and have the program encrypt it for you, with a key you select. The user can then get the program to try and break the encrypted text.

The user can enter already encrypted text and the program will try and break it.

The user can also get the program to read a text file with either plain text or encrypted text, and try an break that.
Files must be in the same directory as railfence.jar for the program to see them.

The highest scoring result will be displayed once all the results are sorted. This will include the key used, the score and the decrypted text. The decrypted text will also be printed to a text file called brokenEncryptedText.txt.

### Design
Runner, the class with main() creates an instance of CypherBreaker.

CypherBreaker handles breaking the cypher, so it creates enough Decryptor threads to try every possible key (length of the message / 2). The Decryptor threads use the key they were given to decrypt the cypher text, score it with the textScorer and adds the key used, the score and decrypted text to a Result object and puts it on a blocking queue.

CypherBreaker also makes a ResultSorter thread which handles sorting the results that are on the queue and keeps a record of the highest scoring result. Once all the results are processed, the highest scoring result is printed out, showing the score, key used and the decrypted text. To make it easier to read large decrypted text files, the decrypted text is also saved to a text file called brokenEncryptedText.txt.

CypherBreaker blocks and waits until the ResultSorter thread is finished and then main can know that the cypher breaking is finished and prepare to break a new encryption if the user wants.



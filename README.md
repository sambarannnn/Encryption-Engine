# Encryption Engine
 Provides Encryption and Decryption of data from input file to output file based on Caesar's Algorithm
 
The program parses CLI : -mode, -key and -data -inputFile -OutputFile. 
The first argument should determine the program’s mode (enc for encryption, dec for decryption). The second argument is an integer key to modify the message, and the third argument is a text or ciphertext to encrypt or decrypt.

All the arguments are guaranteed to be passed to the program. If for some reason it turns out to be wrong:
If there is no -mode, the program works in enc mode.
If there is no -key, the program considers that key = 0.
If there is no -data, and there is no -in the program assumes that the data is an empty string.
If there is no -out argument, the program prints data to the standard output.
If there are both -data and -in arguments, your program prefers -data over -in.


Project UML :
![uml](https://user-images.githubusercontent.com/63100608/120111515-cedce800-c18f-11eb-8f9b-1bdc578c113e.png)

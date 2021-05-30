package encryptdecrypt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.System.exit;

public class EncryptionEngine {
    // contains the mode
    private String mode;

    // contains the key
    private int key;

    // contains the cypher text
    private String data;

    // contains the encrypted/decrypted text
    private String messageText;

    Path fileInput;

    private String inputFileName;

    private String outputFileName;


    public EncryptionEngine(String mode, int key, String data, String inputFileName, String outputFileName) {
        this.mode = mode;
        this.key = key;
        this.data = data;
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;

        if (data == null && inputFileName != null) {
            // if the input file doesn't exist, generate an error message
            if (!(inputFileName == null) || inputFileName.length() == 0) {
                fileInput = Paths.get(inputFileName);
                if (!Files.exists(fileInput)) {
                    System.out.println("Error! Input file is not existent!");
                    exit(1);
                }
            }

            // if inputFileName is valid, read data into String
            if (!(inputFileName == null) || inputFileName.length() == 0) {
                try {
                    data = new String(Files.readAllBytes(Paths.get(inputFileName)));
                } catch (IOException e) {
                    System.out.println("Error! Cannot read file: " + e.getMessage());
                }
            }
        }

        switch (mode) {
            case "enc":
                encryptText(data, key);
                if (outputFileName != null) {
                    printMessageText(outputFileName);
                } else {
                    printMessageText();
                }
                break;
            case "dec":
                decryptText(data, key);
                if (outputFileName != null) {
                    printMessageText(outputFileName);
                } else {
                    printMessageText();
                }
                break;
        }
    }

    public void encryptText(String cypherText, int key) {
        // declare an array of chars, it will contain the encrypted characters
        char[] arrayOfChars = new char[cypherText.length()];

        int result = 0;
        int letter;

        // encrypt function
        // loop to convert chars
        for (int i = 0; i < cypherText.length(); i++) {

            // convert char to int (set 0-65535)
            letter = (int) cypherText.charAt(i);

            // calculate the rotation
            result = (letter % 65536) + key;

            // if result is bigger than 65535
            // reduce it to a number inside 0-65535
            // always using modulus operation
            while (result > 65536) {
                result = (result % 65536);
            }

            // the resulting character, "reconverted" to 0-65535 set
            arrayOfChars[i] = (char) (result + 65536);

        }
        // set encrypted text
        messageText = String.valueOf(arrayOfChars);
    }

    public void printMessageText() {
        System.out.println(messageText);
    }

    public void printMessageText(String outputFileName) {
        File file = new File(outputFileName);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(messageText); // prints a string
        } catch (IOException e) {
            System.out.printf("Error! An exception occurs %s", e.getMessage());
        }
    }

    public void decryptText(String cypherText, int key) {
        // declare an array of chars, it will contain the encrypted characters
        char[] arrayOfChars = new char[cypherText.length()];

        int result = 0;
        int letter;

        // encrypt function
        // loop to convert chars
        for (int i = 0; i < cypherText.length(); i++) {

            // convert char to int (set 0-65535)
            letter = (int) cypherText.charAt(i);

            // calculate the rotation
            result = (letter % 65536) - key;

            // if result is bigger than 65535
            // reduce it to a number inside 0-65535
            // always using modulus operation
            while (result > 65536) {
                result = (result % 65536);
            }

            // the resulting character, "reconverted" to 0-65535 set
            arrayOfChars[i] = (char) (result - 65536);

        }
        // set encrypted text
        messageText = String.valueOf(arrayOfChars);
    }
}

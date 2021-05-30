package encryptdecrypt;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        // Operation: enc for encryption (default), dec for decryption
        String mode = "enc";

        // it contains the key, default = 0
        int key = 0;

        // it contains the plaintext, default is null
        String data = null;

        // it contains the input file, default is null
        String inputFileName = null;


        // it contains the output file, default is null
        String outputFileName = null;

        // if arguments are not in pair, then one of the arguments is missing so show an error message.
        if (args.length % 2 != 0) {
            System.out.println("Error! At least one of the arguments is missing the value");
            exit(1);
        }

        // get command-line arguments
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    data = args[i + 1];
                    break;
                case "-in":
                    inputFileName = args[i + 1];
                    break;
                case "-out":
                    outputFileName = args[i + 1];
                    break;
            }
        }

        EncryptionEngine encryptionEngine = new EncryptionEngine(mode, key, data, inputFileName, outputFileName);
    }
}

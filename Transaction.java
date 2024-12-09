//Melissa Louise Bangloy 1468444
//The Transaction class handles logging transactions to a file.
//It has a method to log transaction details to a file called "transaction_history.txt".
// This class ensures that all transactions are saved for future reference.

import java.io.*;

public class Transaction {
    private static final String TRANSACTION_FILE = "transaction_history.txt";

    public static void logTransaction(String transactionDetails) {
        try (FileWriter fw = new FileWriter(TRANSACTION_FILE, true);
            PrintWriter writer = new PrintWriter(fw)) {
            writer.println(transactionDetails);
        } catch (IOException e) {
            System.out.println("Error writing to transaction file.");
        }
    }
}

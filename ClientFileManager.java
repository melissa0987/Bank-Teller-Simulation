//Melissa Louise Bangloy 1468444
//The ClientFileManager class handles loading and saving client data to a file called "clients.txt".
//It reads client information from the file, creates Client objects, and adds them to a list.
// It also saves the list of clients back to the file, including details like name, balance, account number, and last activity date.

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientFileManager {
    private static final String CLIENT_FILE = "clients.txt";

    public static List<Client> loadClients() {
        List<Client> clients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CLIENT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {  // Expecting 7 fields now (name, dob, address, phone, balance, accountNumber, lastActivityDate)
                    String name = data[0];
                    String dob = data[1];
                    String address = data[2];
                    String phone = data[3];
                    double balance = Double.parseDouble(data[4]);
                    String accountNumber = data[5];
                    String lastActivityDate = data[6];  // New field for last activity date
                    Client client = new Client(name, dob, address, phone, balance);
                    client.updateLastActivityDate();  // Correcting this to update the last activity date from the file
                    clients.add(client);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading client file: " + e.getMessage());
        }
        return clients;
    }
    //save info to the clients.txt
    public static void saveClients(List<Client> clients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENT_FILE))) {
            for (Client client : clients) {
                writer.write(client.getName() + "," + client.getDateOfBirth() + "," + client.getAddress() + "," +
                             client.getPhoneNumber() + "," + client.getBalance() + "," + client.getAccountNumber() + "," +
                             client.getLastActivityDate());  // Write last activity date as well
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to client file: " + e.getMessage());
        }
    }
}

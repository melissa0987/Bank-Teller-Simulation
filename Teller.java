//Melissa Louise Bangloy 1468444
// The Teller class is responsible for managing clients at the bank.
//It allows adding new clients, finding existing clients by name and date of birth, and performing actions like deposits, withdrawals, and closing accounts.
// It also logs transactions and saves client data to a file.
import java.util.List;

public class Teller {
    private final int id;
    private final String name;

    public Teller(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //to add new client method
    public void addNewClient(List<Client> clients, String name, String dob, String address, String phone, double initialDeposit) {
        Client newClient = new Client(name, dob, address, phone, initialDeposit);
        clients.add(newClient);
        Transaction.logTransaction("New client added: " + newClient); //log the activity to the transaction log file
        ClientFileManager.saveClients(clients); //saves to client file
    }
    //looks for an existing client by client name, and date of birth
    public Client findClient(List<Client> clients, String name, String dateOfBirth) {
        for (Client client : clients) {
            if (client.getName().equalsIgnoreCase(name) &&
                client.getDateOfBirth().equalsIgnoreCase(dateOfBirth)) {
                return client;
            }
        }
        return null; // if Client not found
    }
    //teller action
    public void serve(Client client, String action, double amount) {
        switch (action.toLowerCase()) {
            case "deposit":
                client.deposit(amount);
                break;
            case "withdraw":
                client.withdraw(amount);
                break;
            case "close":
                System.out.println("Account closed: " + client.getAccountNumber());
                break;
            default:
                System.out.println("Invalid action.");
        }
    }
}

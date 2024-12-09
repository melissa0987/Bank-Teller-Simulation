
//Melissa Louise Bangloy 1468444
// The Bank class is where all the main actions of the bank happen.
// It lets the teller interact with clients, like adding new clients,
// managing their accounts (depositing, withdrawing, updating info, closing accounts),
// and showing client details. Any changes made to client data are saved to the file.
// The program runs in a loop, letting the teller choose different options from a menu
// and work with the clients.

import java.util.List;
import java.util.Scanner;

public class Bank {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        //load existing clients from the file
        List<Client> clientList = ClientFileManager.loadClients();

        Teller teller = new Teller(1, "Teller");
        //ideally there would be a lot of teller, to simplify the program, we currently have one teller
        while (true) {
            printTellerMenu(); //print menu option

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // new line for userinput

            switch (choice) {
                case 1:
                    handleNewClient(clientList, teller);
                    break;
                case 2:
                    handleExistingClient(clientList, teller);
                    break;
                case 3:
                    viewAllClients(clientList);
                    break;
                case 4:
                    //saves all changes
                    ClientFileManager.saveClients(clientList);
                    System.out.println("Exiting... Goodbye!");
                    return; // Exit the program
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println();
        }
    }
    //prints teller menu options
    public static void printTellerMenu(){
        System.out.println("1. New Client" + 
                            "\n2. Existing Client"+
                            "\n3. View All Clients"+
                            "\n4. Exit");
    }

    //method to handle new client 
    public static void handleNewClient(List<Client> clients, Teller teller) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date of birth (DD/MM/YYYY): ");
        String dob = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter initial deposit: ");
        double deposit = scanner.nextDouble();
        while (deposit <= 0) {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
            deposit = scanner.nextDouble();

            if (deposit <= 0) {
                System.out.println("Invalid deposit amount. Please enter a positive value.");
            }
        }
        scanner.nextLine();  // new line for userinput

        //adds new client via teller
        Client newClient = new Client(name, dob, address, phone, deposit);
        teller.addNewClient(clients, name, dob, address, phone, deposit); //adding to the list 
        System.out.println("New client created:\n" + newClient);
    }
    //method to handle existing client 
    public static void handleExistingClient(List<Client> clients, Teller teller) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date of birth (DD/MM/YYYY): ");
        String dob = scanner.nextLine();

        Client client = teller.findClient(clients, name, dob); //checks if client already exits, 
        if (client != null) {
            System.out.println("==========");
            System.out.println("Client found:\n" + client);
            System.out.println("Choose: 1. Deposit \n2. Withdraw \n3. Close Account \n4. Update Info \n5. Exit");
            System.out.print("Enter choice: ");
            int action = scanner.nextInt();
            scanner.nextLine(); // new line for userinput

            switch (action) {
                case 1:
                    depositMoney(teller, client);
                    break;
                case 2:
                    withdrawMoney(teller, client);
                    break;
                case 3:
                    closeAccount(clients, client);
                    break;
                case 4:
                    updateClientInfo(client);
                    break;
                case 5:
                    return; // Exit the program
                default:
                    System.out.println("Invalid option.");
            }
        } else {
            System.out.println("Client not found.");
        }
    }
    //clientAccount Menu
    public static void clientAccountMenu(){
        System.out.println("1. Deposit Money" + 
                            "\n2. Withdraw Money"+
                            "\n3. Close Account"+
                            "\n4. Update Client Info");
    }


    //method to deposit money
    public static void depositMoney(Teller teller, Client client) {
        Scanner scanner = new Scanner(System.in);
        double amount = -1;  // Initial invalid value to enter the loop

        //input validation, no negative value
        while (amount <= 0) {
            System.out.print("Enter deposit amount: ");
            amount = scanner.nextDouble();

            if (amount <= 0) {
                System.out.println("Invalid deposit amount. Please enter a positive value.");
            }
        }

        teller.serve(client, "deposit", amount);
        //log activity to log Transaction file
        Transaction.logTransaction("Deposit of $" + amount + " for account: " + client.getAccountNumber());
    }

    //method to withdraw money
    public static void withdrawMoney(Teller teller, Client client) {
        Scanner scanner = new Scanner(System.in);
        double amount = -1; 

        //input validation, withdrawn moenu
        while (amount <= 0 || amount > client.getBalance()) {
            System.out.print("Enter withdrawal amount: ");
            amount = scanner.nextDouble();

            if (amount <= 0) {
                System.out.println("Invalid withdrawal amount. Please enter a positive value.");
            } else if (amount > client.getBalance()) {
                System.out.println("Insufficient funds. Your balance is $" + client.getBalance() + ". Please enter a valid withdrawal amount.");
            }
        }
        teller.serve(client, "withdraw", amount);
        Transaction.logTransaction("Withdrawal of $" + amount + " for account: " + client.getAccountNumber());
    }
    //method to close client account, removes it from client list and file. will appear on the transaction log file
    public static void closeAccount(List<Client> clients, Client client) {
        clients.remove(client);
        System.out.println("Account closed: " + client.getAccountNumber());
        Transaction.logTransaction("Account closed: " + client.getAccountNumber());
    }

    //to view client details
    public static void updateClientInfo(Client client) {
        Scanner scanner = new Scanner(System.in);
        //menu for updating client info
        System.out.println("Choose information to update: \n"+
                            "1. Update Address \n"+ 
                            "2. Update Phone Number \n" +
                            "3. Cancel");

        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();//newline for user input

        //switch/case for the client info menu
        switch (choice) {
            case 1:
                updateAddress(client);//update address
                break;
            case 2:
                updatePhoneNumber(client); //update phone number
                break;
            case 3:
                System.out.println("Update canceled.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
    //helper method for updateClientInfo, update client's address
    private static void updateAddress(Client client) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new address: ");
        String newAddress = scanner.nextLine();

        client.setAddress(newAddress);  // change the address
        client.updateLastActivityDate();  // update last activity date
        System.out.println("Client address updated.");
        Transaction.logTransaction("Client address updated for account: " + client.getAccountNumber());
        ClientFileManager.saveClients(ClientFileManager.loadClients());  //save changes to file
    }
    //helper method for updateClientInfo, update client's PhoneNumber
    private static void updatePhoneNumber(Client client) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new phone number: ");
        String newPhone = scanner.nextLine();

        client.setPhoneNumber(newPhone);  // change the phone number
        client.updateLastActivityDate();  // cpdate last activity date
        System.out.println("Client phone number updated.");
        Transaction.logTransaction("Client phone number updated for account: " + client.getAccountNumber());
        ClientFileManager.saveClients(ClientFileManager.loadClients());  //save changes to file
    }
    
    //prints all client from client list
    public static void viewAllClients(List<Client> clients) {
        System.out.println("--- Client List ---");
        System.out.println("Acc. num \tName \t\tDate of Birth \tAddress \tPhone Number \tDeposit \tLast Activity");
        for (Client client : clients) {
            System.out.println(client);
        }
        System.out.println();
    }
}

import java.util.Random;

public class BankAccount {
    private Client customerInfo;
    //set randomly, then it'll be unchangeable(no setter)
    private final String ACCOUNT_NUMS;
    private double balance;


    private static final int ACCOUNT_NUMBER_LENGTH = 10;  //length of account number
    private static final Random random = new Random();
    
    //constructors
    //new bank account
    public BankAccount(){
        this.customerInfo = new Client();
        this.ACCOUNT_NUMS = generateUniqueAccountNumber();
        this.balance = 0.0;
    }
    //new bank account with customer info
    //need to do validation in main
    public BankAccount(Client customerInfo){
        this.customerInfo = customerInfo;
        this.ACCOUNT_NUMS = generateUniqueAccountNumber();
        this.balance = 0.0;
    }

    //to generate a random unique account number
    private String generateUniqueAccountNumber() {
        String accountNumber = "";
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            accountNumber += random.nextInt(10);
        }
        return accountNumber;
    }

    //getters
    public String getAccountNumber() {
        return ACCOUNT_NUMS;
    }

    public double getBalance() {
        return balance;
    }

    //method to widthdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }
    // Methods for deposit and withdrawal
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to display account information
    public void displayAccountInfo() {
        System.out.println("Account Number: " + ACCOUNT_NUMS);
        System.out.println("Balance: $" + balance);
        if (customerInfo != null) {
            customerInfo.toString();
        }
    }


}

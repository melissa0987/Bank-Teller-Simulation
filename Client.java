//Melissa Louise Bangloy 1468444
// The Client class represents a bank client with information like name, date of birth, address, phone number, balance, and account number.
// The account number is generated randomly and can't be changed. 
// The class also tracks the last activity date and has methods for depositing and withdrawing money.
// It includes getters and setters for all the client details, and a toString() method to display the client’s information.
 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Client {
    private String name;
    private String dateOfBirth;
    private String address;
    private String phoneNumber;
    private double balance;
    private String lastActivityDate;  // New field to store the last activity date
    private final String accountNumber; //account number not changeable
    private static final int ACCOUNT_NUMBER_LENGTH = 10;
    private static final Random random = new Random();

    //constructor
    //default
    public Client() {
        this.name = "";
        this.dateOfBirth = "";
        this.address = "";
        this.phoneNumber = "";
        this.balance = 0.0;
        this.accountNumber = generateAccountNumber(); //generate unique account number
    }
    //constructor with name, dob, address and phone number and initial deposit
    public Client(String name, String dateOfBirth, String address, String phoneNumber, double initialDeposit) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        this.lastActivityDate = getCurrentDate();  //set last activity date to current date on creation
    }

    //method to generate a random account number
    private String generateAccountNumber() {
        StringBuilder accountNumberBuilder = new StringBuilder();
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            accountNumberBuilder.append(random.nextInt(10)); // Append a random digit
        }
        return accountNumberBuilder.toString();
    }

    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    //updates the last activity
    public void updateLastActivityDate() {
        this.lastActivityDate = getCurrentDate();
    }

    //getters
    public String getName(){
        return this.name;
    }
    public String getAccountNumber() {
        return this.accountNumber;
    }
    public String getDateOfBirth(){
        return this.dateOfBirth;
    }
    public String getAddress(){
        return this.address;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public double getBalance() {
        return balance;
    }
    //gets last activity date
    public String getLastActivityDate() {
        return lastActivityDate;
    }
    //gets current time
    private String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    ///method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
    //method to widthdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    //overriidin toString() method
    @Override
    public String toString() {
        return this.accountNumber +
               ", \t" + this.name + 
               ", \t" + this.dateOfBirth + 
               ", \t" + this.address + 
               ", \t" + this.phoneNumber + 
               ", \t" + this.balance +
               ", \t" + this.lastActivityDate;  // Display last activity date
    }
    
}

    

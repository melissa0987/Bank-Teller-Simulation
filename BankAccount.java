import java.util.Random;

public class BankAccount {
    private Client customerInfo;
    //set randomly, then it'll be unchangeable(no setter)
    private final String ACCOUNT_NUMString;
    private double balance;
    private String transactionType;


    private static final int ACCOUNT_NUMBER_LENGTH = 10;  // Length of account number
    private static Random random = new Random();
    
    //constructors
    //new bank account
    public BankAccount(){
        this.customerInfo = new Client();
        this.ACCOUNT_NUMString = generateUniqueAccountNumber();
        this.balance = 0.0;
        this.transactionType = "";
    }
    //new bank account with customer info
    //need to do validation in main
    public BankAccount(Client customerInfo){
        this.customerInfo = customerInfo;
        this.ACCOUNT_NUMString = generateUniqueAccountNumber();
        this.balance = 0.0;
        this.transactionType = "";
    }




    //to generate a random unique account number
    private String generateUniqueAccountNumber() {
        String accountNumber = "";
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            accountNumber += random.nextInt(10);
        }
        return accountNumber;
    }

    //method to retrieved account (by account number)


}

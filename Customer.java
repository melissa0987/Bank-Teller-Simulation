public class Customer {
    private String name;
    private String dateOfBirth; //to differentiate clients with the same name
    private String address;
    private String phoneNumber;

    //constructor
    public Customer(){
        this.name = "";
        this.dateOfBirth = "";
        this.address = "";
        this.phoneNumber = "";
    }
    public Customer(String name, String dateOfBirth, String address, String phoneNumber ){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    //getters
    public String getName(){
        return this.name;
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

    //checks if the client already exist
    @Override
    public boolean equals(Object o){
        if( !(o instanceof Customer)){
            return false;
        }

        Customer d = (Customer) o;
        return this.name.equals(d.getName()) && 
                this.dateOfBirth.equals(d.getDateOfBirth()) && 
                this.address.equals(d.getAddress()) &&
                this.phoneNumber.equals(d.getPhoneNumber());
    }
    
}

    

package Classes;

import java.util.Date;

public class Customer extends User {
    private double balance;
    private String address;
    private Gender gender;
    private Category[] interests;

    private Customer(String username, String password, Date dateOfBirth, Gender gender){
        super(username,password,dateOfBirth);
        this.gender = gender;
    }

    public User Signup(String username, String password, Date dateOfBirth, Gender gender){
        return new Customer(username, password, dateOfBirth, gender);
    }
    @Override
    public boolean IsAdmin(){
        return false;
    }
    @Override
    public String toString(){
        return "customer: "+super.toString();
    }
}
enum Gender{
    Man, Woman
}

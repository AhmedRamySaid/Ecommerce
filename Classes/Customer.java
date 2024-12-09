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

    public void Signup(String username, String password, Date dateOfBirth, Gender gender){
        new Customer(username, password, dateOfBirth, gender);
    }
    @Override
    public boolean IsAdmin(){
        return false;
    }
}
enum Gender{
    Man, Woman
}

package Classes;

import java.util.Date;

abstract public class User{
    String username;
    String password;
    Date dateOfBirth;

    User(){

    }
    User(String username, String password, Date dateOfBirth){
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        Database.addUser(this);
    }
    User Login(String username, String password){
        if (this.username.equals(username) && this.password.equals(password)){
            return this;
        }
        System.out.println("Invalid Username or Password");
        return null;
    }
    abstract boolean IsAdmin();
}
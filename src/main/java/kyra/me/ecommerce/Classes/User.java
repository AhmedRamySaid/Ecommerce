package kyra.me.ecommerce.Classes;

import java.util.Date;

abstract public class User{
    private String username;
    private Date dateOfBirth;

    public User(){

    }
    public User(String username, Date dateOfBirth){
        this.username = username;
        this.dateOfBirth = dateOfBirth;
    }
    public static User Login(String username, String password){
        return NewDatabase.instance.authenticate(username, password);
    }

    public String getUsername(){
        return username;
    }
    @Override
    public String toString(){
        return "\nUsername: " + username + "\nDate of birth: " + dateOfBirth;
    }
    public boolean isAdmin(){
        return this instanceof Admin;
    }
}

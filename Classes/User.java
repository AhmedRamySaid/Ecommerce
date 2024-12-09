package Classes;

import java.util.Date;

abstract public class User{
    String username;
    private String password;
    Date dateOfBirth;

    User(){

    }
    User(String username, String password, Date dateOfBirth){
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        Database.addUser(this);
    }
    public static User Login(String username, String password){
        User user = Database.getUser(username);
        if (user == null){ System.out.println("Invalid username"); return null; }
        if (user.password.equals(password)){ return user; }
        System.out.println("Invalid password");
        return null;
    }
    abstract boolean IsAdmin();
}
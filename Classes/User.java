package Classes;

import java.util.Date;

abstract public class User {
    String username;
    String password;
    Date dateOfBirth;

    User(){
        Database.addUser(this);
    }
    abstract boolean IsAdmin();
}

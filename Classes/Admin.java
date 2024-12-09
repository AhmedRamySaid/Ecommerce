package Classes;

public class Admin extends User {
    double workingHours;
    Role role;

    enum Role{
        Manager,
        Customer_Support
    }
    public boolean IsAdmin(){
        return true;
    }

}

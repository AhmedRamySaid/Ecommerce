package Classes;

public class Admin extends User {
    double workingHours; //hours they work in a week
    Role role;

    enum Role{
        Manager,
        Customer_Support
    }

    public void CreateProduct(){
        Database.addProduct(new Product());
    }
    @Override
    public boolean IsAdmin(){
        return true;
    }
    @Override
    public String toString(){
        return "Admin " + super.toString();
    }
    public void ShowallUsers(){
        User[] userlist = Database.getUserList();
        for(int i = 0; i < Database.getUserCount(); i++){
            System.out.println(userlist[i].toString());
        }
    }
}
//Admin {username}
//Customer {username}
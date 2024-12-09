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

}

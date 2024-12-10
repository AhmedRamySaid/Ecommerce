package Classes;

import java.io.Serializable;
import java.util.Date;

public class Admin extends User implements AdminCRUD {
    private double workingHours; //hours they work in a week
    private String role;

    public Admin(String username, String password, Date dateOfBirth, String role, double workingHours) {
        super(username, password, dateOfBirth);
        this.role = role;
        this.workingHours = workingHours;
        Database.addUser(this);
    }
    @Override
    public String toString(){
        return "Admin " + super.toString();
    }

    @Override
    public boolean isAdmin(){
        return true;
    }
    @Override
    public String CreateProduct(String name, double price, String categoryID, String description){
        return new Product(name, price, categoryID, description).getProductID();
    }
    @Override
    public String CreateCategory(String name){
        return new Category(name).getID();
    }
    @Override
    public void EditProduct(String productID, double price){
        Product p = Database.getProduct(productID);
        if (p == null) { System.out.println("Product not found"); return; }
        p.setPrice(price);
    }
    @Override
    public void EditProduct (String productID, String description){
        Product p = Database.getProduct(productID);
        if (p == null) { System.out.println("Product not found"); return; }
        p.setDescription(description);
    }
    @Override
    public void EditProduct(String productID, Category category){
        Product p = Database.getProduct(productID);
        if (p == null) { System.out.println("Product not found"); return; }
        p.setCategory(category);
    }
    @Override
    public void DeleteProduct(String productID){
        Product p = Database.getProduct(productID);
        if (p == null) { System.out.println("Product not found"); return; }
        String st = p.getProductName();
        Database.removeProduct(productID);
        System.out.println("Deleted product: " + st);
    }
    @Override
    public void ShowAllOrders(){
        Order[] orderlist = Database.getOrderList();
        for(int i = 0; i < Database.getOrderCount(); i++){
            System.out.println(orderlist[i].toString());
        }
    }
    @Override
    public void ShowAllUsers(){
        User[] userlist = Database.getUserList();
        for(int i = 0; i < Database.getUserCount(); i++){
            System.out.println(userlist[i].toString());
        }
    }
    @Override
    public void DeleteCategory(String name){
        Database.removeCategory(name);
    }
}
interface AdminCRUD{
    String CreateProduct(String name, double price, String categoryID, String description);
    void EditProduct(String productID, double price);
    void EditProduct(String productID, String description);
    void EditProduct(String productID, Category category);
    void DeleteProduct(String ProductID);
    void ShowAllOrders();
    void ShowAllProduct();
    void ShowAllUsers();
    String CreateCategory(String name);
    void DeleteCategory(String name);
}
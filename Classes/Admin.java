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

    public void CreateProduct(){
        Database.addProduct(new Product());
    }
    @Override
    public boolean IsAdmin(){
        return true;
    }
    @Override
    public void CreateProduct(double price, String description, Category category){
        new Product(price, description, category);
    }
    @Override
    public void EditProduct(int productID, double price){
        Product p = Database.getProduct(productID);
        if (p == null) { System.out.println("Product not found"); return; }
        p.setPrice(price);
    }
    @Override
    public void EditProduct (int productID, String description){
        Product p = Database.getProduct(productID);
        if (p == null) { System.out.println("Product not found"); return; }
        p.setDescription(description);
    }
    @Override
    public void EditProduct(int productID, Category category){
        Product p = Database.getProduct(productID);
        if (p == null) { System.out.println("Product not found"); return; }
        p.setCategory(category);
    }
    @Override
    public void DeleteProduct(int productID){
        Database.removeProduct(productID);
    }
    public void ShowAllProduct(){
        Product[] productlist = Database.getProductList();
        for(int i=0;i<Database.getProductCount();i++){
            System.out.println(productlist[i].toString());
        }
    }
    public void ShowAllOrders(){
        Order[] orderlist = Database.getOrderList();
        for(int i = 0; i < Database.getOrderCount(); i++){
            System.out.println(orderlist[i].toString());
        }
    }
    public void ShowAllUsers(){
        User[] userlist = Database.getUserList();
        for(int i = 0; i < Database.getUserCount(); i++){
            System.out.println(userlist[i].toString());
        }
    }
    public void CreateCategory(String name){
        new Category(name);
    }
    public void DeleteCategory(String name){
        Database.removeCategory(name);
    }
}
interface AdminCRUD{
    void CreateProduct(double price, String description, Category category);
    void EditProduct(int productID, double price);
    void EditProduct(int productID, String description);
    void EditProduct(int productID, Category category);
    void DeleteProduct(int ProductID);
    void ShowAllOrders();
    void ShowAllProduct();
    void ShowAllUsers();
    void CreateCategory(String name);
    void DeleteCategory(String name);
}
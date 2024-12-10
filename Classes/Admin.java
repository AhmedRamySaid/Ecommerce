package Classes;

import java.io.Serializable;

public class Admin extends User implements AdminCRUD {
    private double workingHours; //hours they work in a week
    private String role;

    public Admin(String username, String password, String dateOfBirth, String role, double workingHours) {
        super(username, password, dateOfBirth);
        this.role = role;
        this.workingHours = workingHours;
    }
    @Override
    public String toString(){
        return "Account type: Admin\n" + super.toString();
    }

    @Override
    public boolean isAdmin(){
        return true;
    }
    @Override
    public void CreateProduct(String name, double price, String categoryID, String description){
        Category category = Database.getCategory(categoryID);
        if (category == null) {
            System.out.println("Error. Category not found");
            return;
        }
        Product p = new Product(name, price, category, description);
        System.out.println("Success! Product ID: " + p.getProductID());
    }
    @Override
    public void CreateCategory(String name){
        Category c = new Category(name);
        System.out.println("Success! Category ID: " + c.getID());
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
            orderlist[i].PrintOrder();
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
    void CreateProduct(String name, double price, String categoryID, String description);
    void EditProduct(String productID, double price);
    void EditProduct(String productID, String description);
    void EditProduct(String productID, Category category);
    void DeleteProduct(String ProductID);
    void ShowAllOrders();
    void ShowAllProducts();
    void ShowAllUsers();
    void CreateCategory(String name);
    void DeleteCategory(String name);
}
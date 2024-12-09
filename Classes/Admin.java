package Classes;

import java.io.Serializable;

public class Admin extends User implements AdminCRUD {
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
}
interface AdminCRUD{
    void CreateProduct(double price, String description, Category category);
    void EditProduct(int productID, double price);
    void EditProduct(int productID, String description);
    void EditProduct(int productID, Category category);
    void DeleteProduct(int ProductID);
    //void CreateCategory();
    //void EditCategory(Category category);
    //void DeleteCategory(Category category);
}
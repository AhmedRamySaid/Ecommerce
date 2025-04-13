package kyra.me.ecommerce.Classes;

import java.util.Date;

public class Admin extends User implements AdminCRUD {
    private String role;

    public Admin(String username, Date dateOfBirth, String role) {
        super(username, dateOfBirth);
        this.role = role;
    }
    @Override
    public String toString(){
        return "\nAccount type: Admin" + super.toString() + "\nRole: " + role;
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
    public void CreateProduct(String name, double price, Category category, String description){
        if (category == null) {
            System.out.println("Error. Category can't be null");
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
    public void EditProduct (String productID, String price_description_category, int choice){
        Product p = Database.getProduct(productID);
        if (p == null) { System.out.println("Product not found"); return; }
        switch (choice) {
            case 1:
                try {
                    p.setPrice(Double.parseDouble(price_description_category));
                    System.out.println("Price changed successfully");
                }
                catch (NumberFormatException e) {
                    System.out.println("Error. Price not valid");
                }
                break;
            case 2:
                p.setDescription(price_description_category);
                System.out.println("Description changed successfully");
                break;
            case 3:
                Category c = Database.getCategory(price_description_category);
                if (c == null) { System.out.println("Category not found"); return; }
                p.setCategory(c);
                System.out.println("Category changed successfully");
                break;
            default:
                System.out.println("Error. Choice not valid");
                break;
        }
    }
    @Override
    public void DeleteProduct(String productID){
        Product p = Database.getProduct(productID);
        if (p == null) { System.out.println("Product not found"); return; }
        Database.removeProduct(productID);
    }
    @Override
    public void DeleteCategory(String name){
        Database.removeCategory(name);
    }
}
interface AdminCRUD{
    void CreateProduct(String name, double price, String categoryID, String description);
    void CreateProduct(String name, double price, Category category, String description);
    void EditProduct(String productID, String price_description_category, int choice);
    void DeleteProduct(String ProductID);
    void CreateCategory(String name);
    void DeleteCategory(String name);
}
package kyra.me.ecommerce.Classes;

import java.util.Date;

public class Admin extends User implements AdminCRUD {
    private final String role;

    public Admin(String username, Date dateOfBirth, String role) {
        super(username, dateOfBirth);
        this.role = role;
    }
    @Override
    public String toString(){
        return "\nAccount type: Admin" + super.toString() + "\nRole: " + role;
    }

    @Override
    public void CreateCategory(String name){
        Category c = new Category(name);
        System.out.println("Success! Category ID: " + c.getID());
    }
    @Override
    public void EditProduct (String productID, String price_description_category, int choice){
        Product p = NewDatabase.instance.getProduct(productID);
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
                Category c = NewDatabase.instance.getCategory(price_description_category);
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
        Product p = NewDatabase.instance.getProduct(productID);
        if (p == null) { System.out.println("Product not found"); return; }
        Database.removeProduct(productID);
    }
    @Override
    public void DeleteCategory(String name){
        Database.removeCategory(name);
    }
}
interface AdminCRUD{
    void EditProduct(String productID, String price_description_category, int choice);
    void DeleteProduct(String ProductID);
    void CreateCategory(String name);
    void DeleteCategory(String name);
}
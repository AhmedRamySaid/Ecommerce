package Classes;

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
        return "\nAccount type: Admin" + super.toString() + "Role: " + role + "\nWorkingHours: " + workingHours;
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
    void EditProduct(String productID, String price_description_category, int choice);
    void DeleteProduct(String ProductID);
    void ShowAllOrders();
    void ShowAllUsers();
    void CreateCategory(String name);
    void DeleteCategory(String name);
    //void ShowAllProducts(); inside the parent class
}
package kyra.me.ecommerce.Classes;

public class Product {
    private static int IDCounter = 100;
    private final String productID;
    private final String productName;
    private double price;
    private String description;
    private Category category;

    //Constructor
    private Product(Product p){
        this.price = p.getPrice();
        this.productID = p.getID();
        this.description = p.getDescription();
        this.category = p.getCategory();
        this.productName = p.getProductName();
    }

    public Product(String name, double price, Category category, String description){
        productName = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.productID = "PR" + category.getID() +IDCounter++;
        Database.addProduct(this);
    }
    //Setters and Getters
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getID() {
        return productID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public String getProductName() {
        return productName;
    }
    public static Product[] CopyProductList(Product[] products){
        Product[] p = new Product[products.length];
        for(int i = 0; i < products.length; i++){
            p[i] = new Product(products[i]);
        }
        return p;
    }
    @Override
    public String toString(){
        return "\nProduct name: " + productName + "\nProduct ID: " + productID + "\nProduct cost: " + price +
                "$\nProduct description: " + description + "\nProduct category: " + category.getName();
    }
}

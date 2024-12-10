package Classes;

public class Product {
    private static int IDCounter = 11111;
    private double price;
    private final String productID;
    private String description;
    private Category category;
    private String productname;
    //Constructor
    public Product(){
        this(0,"NO_DESCRIPTION", null);
    }

    public Product(double price, String description, Category category){
        this.price = price;
        this.description = description;
        this.productID = "PR" + IDCounter++;
        this.category = category;
        Database.addProduct(this);
    }
    //Setters and Getters
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductCode() {
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

    @Override
    public String toString(){
        return "Product Name: " + productname+ "\nProduct ID: " + productID;
    }
}

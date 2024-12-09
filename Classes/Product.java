package Classes;

public class Product {
    private static int IDCounter = 11111;
    private double price;
    private final int productID;
    private String description;
    private Category category;
    //Constructor
    public Product(){
        this(0,"NO_DESCRIPTION", null);
    }

    public Product(double price, String description, Category category){
        this.price = price;
        this.description = description;
        this.productID = IDCounter++;
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

    public int getProductCode() {
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
    public int getID() {
        return productID;
    }
}

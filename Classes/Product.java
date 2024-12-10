package Classes;

public class Product {
    private static int IDCounter = 100;
    private double price;
    private final String productID;
    private String description;
    private Category category;
    private final String productName;
    //Constructor
    private Product(Product p){
        this.price = p.getPrice();
        this.productID = p.getProductID();
        this.description = p.getDescription();
        this.category = p.getCategory();
        this.productName = p.getProductName();
    }
    public Product(){
        this("", 0,new Category(""), null);
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

    public String getProductID() {
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
    public static Product[] CopyProductList(Product[] products, int size){
        Product[] p = new Product[size];
        for(int i = 0; i < size; i++){
            p[i] = new Product(products[i]);
        }
        return p;
    }
    @Override
    public String toString(){
        return "\nProduct name: " + productName + "\nProduct ID: " + productID + "\nProduct cost: " + price +
                "$\nProduct description: " + description + "\nProduct category: " + category.getName() + "\n";
    }
}

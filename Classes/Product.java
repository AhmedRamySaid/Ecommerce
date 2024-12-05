package Classes;

public class Product {
    private double price;
    private String productCode;
    private String description;
    private Category category;
    //Constructor
    public Product(){
        this(0,"NO_CODE","NO_DESCRIPTION");
    }

    public Product(double price,String productCode,String description){
        this.price = price;
        this.description = description;
        this.productCode = productCode;
    }
    //Setters and Getters
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String code) {
        this.productCode = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

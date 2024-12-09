package Classes;

public class Product {
    private double price;
    private String productID;
    private String description;
    private Category category;
    private String productname;
    //Constructor
    public Product(){
        this(0,"NO_CODE","NO_DESCRIPTION");
    }

    public Product(double price,String productID,String description){
        this.price = price;
        this.description = description;
        this.productID = productID;
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

    public void setProductCode(String code) {
        this.productID = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString(){
        return "Product Name: "+productname+"\nProduct ID: "+productID;
    }
    public void showallProduct(){
        Product[] productlist = Database.getProductList();
        for(int i=0;i<Database.getProductCount();i++){
            System.out.println(productlist[i].toString());
        }
    }
}

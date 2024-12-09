package Classes;

public class Category {
    private final String categoryName;
    private Product[] products;
    private int noOfProducts;

    public Category(String categoryName){
        this.categoryName=categoryName;
        noOfProducts=0;
        products=new Product[64];
        Database.addCategory(this);
    }
    public void addProductToList(Product product){
        products[noOfProducts++]=product;
    }
    public String getName(){
        return categoryName;
    }
}

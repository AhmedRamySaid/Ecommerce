package Classes;

import Classes.Database;
import Classes.Product;

public class Category {
    private static int IDCount = 100;
    private final String categoryName;
    private Product[] products;
    private int noOfProducts;
    private final String CategoryID;

    public Category(String categoryName){
        CategoryID = "CTG" + IDCount++ ;
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
    public String getID() {return CategoryID; }
}
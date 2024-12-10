package Classes;

import Classes.Database;
import Classes.Product;

public class Category {
    private static int IDCount = 100;
    private final String categoryName;
    private final String CategoryID;

    public Category(String categoryName){
        CategoryID = "CTG" + IDCount++ ;
        this.categoryName=categoryName;
        Database.addCategory(this);
    }
    public String getName(){
        return categoryName;
    }
    public String getID() {return CategoryID; }
}
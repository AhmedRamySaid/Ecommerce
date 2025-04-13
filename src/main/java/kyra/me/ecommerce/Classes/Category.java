package kyra.me.ecommerce.Classes;

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
    @Override
    public String toString() {
        return categoryName;
    }
}
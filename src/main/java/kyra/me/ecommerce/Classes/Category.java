package kyra.me.ecommerce.Classes;

public class Category {
    public static int IDCounter = 100;
    private final String categoryName;
    private final String CategoryID;

    public Category(String categoryName){
        this(categoryName, "CTG" + IDCounter++);
    }
    public Category(String categoryName, String categoryID){
        this.categoryName = categoryName;
        this.CategoryID = categoryID;
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
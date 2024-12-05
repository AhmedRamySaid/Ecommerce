package Classes;

public class Category {
    private String categoryName;
    private CategoryType categoryType;

    enum CategoryType{
        Electronics,
        Medical_Items,
        Clothes,
        Household_Items,
        Pet_Supplies
    }
    Category (){

    }
    public Category(String categoryName , CategoryType categoryType){
        this.categoryName=categoryName;
    }
}

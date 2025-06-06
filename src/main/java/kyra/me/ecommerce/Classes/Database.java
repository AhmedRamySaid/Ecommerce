package kyra.me.ecommerce.Classes;

public class Database {
    private static final Order[] orderList = new Order[1024]; //final here means that the variable cannot refer to another object
    private static final Product[] productList = new Product[1024];
    private static final Category[] categoryList = new Category[128];
    private static int productCount = 0;
    private static int orderCount = 0;
    private static int categoryCount = 0;

    public static void removeProduct(String productID){
        for (int i = 0; i < productCount; i++){
            if (productList[i].getID().equals(productID)) {
                for (int j = i; j < productCount-1; j++){
                    productList[j] = productList[j+1];
                }
                productList[--productCount] = null;
                break;
            }
        }
    }
    public static void removeCategory(String categoryName){
        for (int i = 0; i < categoryCount; i++){
            if (categoryList[i].getName().equals(categoryName)){
                for (int j = i; j < categoryCount-1; j++){
                    categoryList[j] = categoryList[j+1];
                }
                categoryList[--categoryCount] = null;
                break;
            }
        }
    }

    public static int getCategoryCount(){ return categoryCount; }
    public static Product[] getProductList(){ return productList;}
    public static Order[] getOrderList(){ return orderList; }
    public static Category[] getCategoryList(){ return categoryList; }
    public static void addProduct(Product product){ productList[productCount++] = product; }
    public static void addOrder(Order order) { orderList[orderCount++] = order; }
    public static void addCategory(Category category){ categoryList[categoryCount++] = category; }
}

package Classes;

public class Database {
    private static Order[] orderList = new Order[1024];
    private static Product[] productList = new Product[1024];
    private static User[] userList = new User[1048576];
    private static Category[] categoryList = new Category[128];
    private static int userCount = 0;
    private static int productCount = 0;
    private static int orderCount = 0;
    private static int categoryCount = 0;

    public static User getUser(String username){
        for (int i = 0; i < userCount; i++){
            if(userList[i].getUsername().equals(username)) { return userList[i]; }
        }
        return null;
    }

    public static Product getProduct(int productID){
        for (int i = 0; i < productCount; i++){
            if (productList[i].getID() == productID) return productList[i];
        }
        return null;
    }

    public static Category getCategory(String categoryname){
        for (int i = 0; i < categoryCount; i++){
            if (categoryList[i].getName().equals(categoryname)) return categoryList[i];
        }
        return null;
    }

    public static void removeOrder(String orderID){
        for (int i = 0; i < orderCount; i++){
            if (orderList[i].getOrderID().equals(orderID)) {
                for(int j = i; j < orderCount-1; j++){
                    productList[j] = productList[j+1];
                }
                productList[--orderCount] = null;
                break;
            }
        }
    }
    public static void removeProduct(int productID){
        for (int i = 0; i < productCount; i++){
            if (productList[i].getID() == productID){
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

    public static int getUserCount(){ return userCount; }
    public static int getProductCount(){ return productCount; }
    public static int getOrderCount(){ return orderCount; }
    public static User[] getUserList(){ return userList; }
    public static Product[] getProductList(){ return productList;}
    public static Order[] getOrderList(){ return orderList; }
    public static void addUser(User user){ userList[userCount++] = user; }
    public static void addProduct(Product product){ productList[productCount++] = product; }
    public static void addOrder(Order order) { orderList[orderCount++] = order; }
    public static void addCategory(Category category){ categoryList[categoryCount++] = category; }
}

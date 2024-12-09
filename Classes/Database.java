package Classes;

public class Database {
    private static Order[] orderList = new Order[1024];
    private static Product[] productList = new Product[1024];
    private static User[] userList = new User[1048576];
    private static int userCount = 0;
    private static int productCount = 0;
    public static User getUser(String username){
        for (int i = 0; i < userCount; i++){
            if(userList[i].username.equals(username)) { return userList[i]; }
        }
        return null;
    }

    public static Product getProduct(int productID){
        for (int i = 0; i < productCount; i++){
            if (productList[i].getID() == productID) return productList[i];
        }
        return null;
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
    public static int getUserCount(){ return userCount; }
    public static int getProductCount(){ return productCount; }
    public static User[] getUserList(){ return userList; }
    public static Product[] getProductList(){ return productList;}
    public static void addUser(User user){
        userList[userCount++] = user;
    }
    public static void addProduct(Product product){
        productList[productCount++] = product;
    }
}

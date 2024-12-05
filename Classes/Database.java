package Classes;

public class Database {
    private static Product[] productList = new Product[1024];
    private static User[] userList = new User[1048576];
    private static int userCount = 0;
    private static int productCount = 0;

    public static User getUser(User user, String username){
        if (!user.IsAdmin()){
            return null;
        }
        for (int i = 0; i < userCount; i++){
            if(userList[i].username.equals(username)) { return user; }
        }
        return null;
    }

    //will return whatever it finds
    public static Product getProduct(User user, String productNameOrID){
        if (!user.IsAdmin()){
            return null;
        }
        for (int i = 0; i < productCount; i++){

        }
        return null;
    }

    public static void addUser(User user){
        userList[userCount++] = user;
    }
    public static void addProduct(Product product){
        productList[productCount++] = product;
    }
}

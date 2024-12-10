package Classes;

abstract public class User{
    private String username;
    private String password;
    private String dateOfBirth;

    User(){

    }
    User(String username, String password, String dateOfBirth){
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        Database.addUser(this);
    }
    public static User Login(String username, String password){
        User user = Database.getUser(username);
        if (user == null){ System.out.println("Invalid username"); return null; }
        if (user.password.equals(password)){ return user; }
        System.out.println("Invalid password");
        return null;
    }
    public String getUsername(){
        return username;
    }
    @Override
    public String toString(){
        return "\nUsername: " + username + "\nDate of birth: " + dateOfBirth;
    }
    public void ShowAllProducts(){
        Product[] productlist = Database.getProductList();
        for(int i=0;i<Database.getProductCount();i++){
            System.out.println(productlist[i].toString());
        }
    }
    public abstract boolean isAdmin();
}
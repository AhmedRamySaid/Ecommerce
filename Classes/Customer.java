package Classes;

public class Customer extends User {
    private double balance;
    private String address;
    private Gender gender;
    private Category[] interests;
    private Cart cart;
    private Order[] orders;
    private int noOfOrders;
    public enum Gender {
        Male, Female
    }
    public Customer() {}

    private Customer(String username, String password, String dateOfBirth, Gender gender){
        super(username,password,dateOfBirth);
        this.gender = gender;
        address = null;
        cart = new Cart();
        noOfOrders = 0;
        orders = new Order[3];
    }
    public void MakeAnOrder(int paymentmethod, String shippingaddress){
        if (noOfOrders == 3){ System.out.println("Error. You can't have more than 3 orders active at a time");}
        orders[noOfOrders++] = new Order(paymentmethod, shippingaddress, this);
    }
    public void RemoveOrder(int orderNo, int status){
        for(int i = orderNo-1; i < noOfOrders-1; i++){
            orders[i].setOrderStatus(status);
            orders[i] = orders[i+1];
        }
    }
    public static Customer Register(String username, String password, String dateOfBirth, Gender gender){
        User user = Database.getUser(username);
        if (user != null) { System.out.println("This username is unavailable"); return null; }
        return new Customer(username, password, dateOfBirth, gender);
    }
    public static Customer Register(String username, String password, String dateOfBirth, String genderSt){
        Gender gender = null;
        if (genderSt.equals("Man")) {
            gender = Gender.Male;
        }
        else if (genderSt.equals("Woman")) {
            gender = Gender.Female;
        }
        return new Customer(username, password, dateOfBirth, gender);
    }
    public void ViewOrders(){
        for(int i = 0; i < noOfOrders; i++){
            orders[i].PrintOrder();
        }
    }
    @Override
    public boolean isAdmin(){
        return false;
    }
    @Override
    public String toString(){
        return "\nAccount type: Customer" + super.toString();
    }
    public String getAddress(){
        return address;
    }
    public Cart getCart() {
        return cart;
    }
    public void AddToCart(String str){
        Product product = Database.getProduct(str);
        if (product == null) { System.out.println("This product does not exist"); return; }
        cart.addProduct(product.getProductID());
    }
    public void ViewCart(){
        Product[] cartproducts = cart.getProducts();
        if(cartproducts[0] == null) { System.out.println("Your cart is empty"); return; }
        for(int i = 0; i < cart.getCount(); i++){
            System.out.println("\n" + (i+1) + ". Name: " + cartproducts[i].getProductName() + "ID: " + cartproducts[i].getProductID());
        }
        System.out.println("Total price: " + cart.getTotalPrice() + "\n");
    }
    public void Checkout(int paymentmethod, String address){
        Order order = new Order(paymentmethod, address, this);
        System.out.println("Order success! Your order ID is " + order.getOrderID());
    }
    public static Gender GetGender(String gender){
        if (gender.equals("1")){
            return Gender.Male;
        }
        else if (gender.equals("2")){
            return Gender.Female;
        }
        else return null;
    }
}

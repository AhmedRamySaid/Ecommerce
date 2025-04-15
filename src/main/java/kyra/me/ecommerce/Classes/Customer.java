package kyra.me.ecommerce.Classes;

import java.util.Date;

public class Customer extends User {
    private String address;
    private Gender gender;
    private Category[] interests;
    private Cart cart;
    private Order[] orders;
    private int noOfOrders;
    public enum Gender {
        Male, Female
    }

    public Customer(String username, Date dateOfBirth, Gender gender, String address){
        super(username, dateOfBirth);
        this.gender = gender;
        this.address = address;
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

    public static Gender StringToGender(String gender){
        if (gender.equals("Man")) {
            return Gender.Male;
        }
        else if (gender.equals("Woman")) {
            return Gender.Female;
        }
        return null;
    }

    @Override
    public String toString(){
        return "\nAccount type: Customer" + super.toString() + "\nGender: " + gender;
    }
    public String getAddress(){
        return address;
    }
    public Cart getCart() {
        return cart;
    }
    public void AddToCart(String str){
        Product product = NewDatabase.instance.getProduct(str);
        if (product == null) { System.out.println("This product does not exist"); return; }
        cart.addProduct(product.getID());
    }
    public void ViewCart(){
        Product[] cartproducts = cart.getProducts();
        if(cartproducts[0] == null) { System.out.println("Your cart is empty"); return; }
        for(int i = 0; i < cart.getCount(); i++){
            System.out.println("\n" + (i+1) + ". Name: " + cartproducts[i].getProductName() + "ID: " + cartproducts[i].getID());
        }
        System.out.println("Total price: " + cart.getTotalPrice() + "\n");
    }
    public void Checkout(int paymentmethod, String address){
        Order order = new Order(paymentmethod, address, this);
        System.out.println("Order success! Your order ID is " + order.getOrderID());
    }
}

package Classes;
import java.util.ArrayList;

public class Cart {
    //ArrayList is a dynamic array implemented by java :)
    private ArrayList<Product> products;
    private double totalPrice;

    //Constructor
    public Cart() {
        this.products =  new ArrayList<Product>();
        this.totalPrice = 0;
    }
    //Setters and Getter

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    //Methods

    //addItem
    void addItem(Product product){
        products.add(product);
        this.updateTotal();
    }
    //removeItem
    void removeItem(int  index){
        products.remove(index);
        this.updateTotal();
    }
    //updateTotal
    void updateTotal(){

        for (Product product : products) {
            totalPrice = 0;
            totalPrice += product.getPrice();
        }
    }
    //clearCart
    void clearCart(){
        products.clear();
        totalPrice = 0;
    }
}
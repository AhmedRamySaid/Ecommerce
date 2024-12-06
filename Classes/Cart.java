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
    //Methods
    //addItem
    //removeItem
    //calculateTotal
    //clearCart
}
package Classes;
import java.util.ArrayList;

public class Cart {
    //ArrayList is a dynamic array implemented by java :)
    private Product[] products = new Product[30];
    private int count = 0;
    private double totalPrice;

    //Constructor
    public Cart() {

    }

    public void addProduct(Product p) {
        products[count++] = p;
        this.updateTotal();
    }
    public void removeProduct(int index) {
        if (index-- > count) { System.out.println("Invalid index"); return; }
        products[index] = null;
        for (int i = index; i < count; i++) {
            products[i] = products[i + 1];
        }
    }
    public void updateTotal(){

        for (int i = 0; i < products.length; i++) {
            totalPrice = 0;
            totalPrice += products[i].getPrice();


        }
    }
    void clearCart(){
        products = new Product[30];
        count = 0;
        totalPrice = 0;
    }
}
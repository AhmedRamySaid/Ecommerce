package Classes;
import java.util.ArrayList;

public class Cart {
    //ArrayList is a dynamic array implemented by java :)
    private Product[] products;
    private int count;
    private double totalPrice;

    //Constructor
    protected Cart() {
        products = new Product[30];
        count = 0;
        totalPrice = 0;
    }
    public int getCount() {
        return count;
    }
    public void addProduct(String productID) {
        if(Database.getProduct(productID) == null) { return; }
        products[count++] = Database.getProduct(productID);
        totalPrice += Database.getProduct(productID).getPrice();
    }
    public void removeProduct(int index) {
        totalPrice -= products[index].getPrice();
        if (index-- > count) { System.out.println("Invalid index"); return; }
        products[index] = null;
        for (int i = index; i < count; i++) {
            products[i] = products[i + 1];
        }
    }
    public void clearCart(){
        products = new Product[30];
        count = 0;
        totalPrice = 0;
    }
    public Product[] getProducts() {
        return products;
    }
}
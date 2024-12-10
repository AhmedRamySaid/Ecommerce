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
    public double getTotalPrice() {
        return totalPrice;
    }
    public void addProduct(String productID) {
        Product product = Database.getProduct(productID);
        if(product == null) { System.out.println("Product does not exist"); return; }
        products[count++] = product;
        totalPrice += product.getPrice();
        System.out.println("Added " + product.getProductName() + " successfully");
    }
    public void removeProduct(int index) {
        if (index-- > count) { System.out.println("Invalid index"); return; }
        System.out.println("Removed " + products[index].getProductName() + " successfully");
        totalPrice -= products[index].getPrice();
        products[index] = null;
        for (int i = index; i < count; i++) {
            products[i] = products[i + 1];
        }
        count--;
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
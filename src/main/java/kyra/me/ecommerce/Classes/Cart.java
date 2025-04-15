package kyra.me.ecommerce.Classes;

public class Cart {
    private final Product[] products;
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
        Product product = NewDatabase.instance.getProduct(productID);
        if(product == null) { System.out.println("Product does not exist"); return; }
        products[count++] = product;
        totalPrice += product.getPrice();
//        System.out.println("Added " + product.getProductName() + " successfully");
    }
    public void removeProduct(int index) {
//        if (index-- > count) { System.out.println("Invalid index"); return; }
//        System.out.println("Removed " + products[index].getProductName() + " successfully");
        totalPrice -= products[index].getPrice();
        products[index] = null;
        for (int i = index; i < count; i++) {
            products[i] = products[i + 1];
        }
        count--;
    }
    public Product[] getProducts() {
        return products;
    }
}

package kyra.me.ecommerce.Classes;

public class Order {
    //Attributes
    private static int orderNo  = 1000;
    private final String orderID;
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private double shippingCost;
    private double totalCost;
    private String shippingAddress;
    private Product[] products;
    Customer customer;

    public enum OrderStatus {
        PENDING,
        CANCELLED,
        DELIVERED
    }

    public enum PaymentMethod {
        CREDIT_CARD,
        DEBIT_CARD,
        CASH_APP,
        CASH_ON_DELIVERY
    }
    //Constructors
    public Order(int paymentmethod, String shippingaddress, Customer customer){
        if (shippingaddress != null) shippingAddress = shippingaddress;
        else shippingAddress = customer.getAddress();
        setPaymentMethod(paymentmethod);
        SetShippingCost(10);
        totalCost = CalculateTotal(customer);
        this.customer = customer;
        products = Product.CopyProductList(customer.getCart().getProducts());
        setOrderStatus(1); //Pending

        Database.addOrder(this);
        orderID = "OR" + orderNo++;
    }

    public String getOrderID() {
        return orderID;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderstatus) {
        switch (orderstatus) {
            case 1:
                orderStatus = OrderStatus.PENDING;
                break;
            case 2:
                orderStatus = OrderStatus.CANCELLED;
                break;
            case 3:
                orderStatus = OrderStatus.DELIVERED;
                break;
        }
    }
    public void setPaymentMethod(int paymentmethod) {
        switch (paymentmethod) {
            case 1:
                paymentMethod = PaymentMethod.CREDIT_CARD;
                break;
            case 2:
                paymentMethod = PaymentMethod.DEBIT_CARD;
                break;
            case 3:
                paymentMethod = PaymentMethod.CASH_APP;
                break;
            case 4:
                paymentMethod = PaymentMethod.CASH_ON_DELIVERY;
                break;
            default:
                break;
        }
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void SetShippingCost(int shippingcost) {
        if(shippingcost < 0) { return; }
        shippingCost = shippingcost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double CalculateTotal(Customer customer) {
        double total = getShippingCost();
        Product[] products = customer.getCart().getProducts();
        for (Product product : products) {
            if (product != null){
                total += product.getPrice();
            }
        }
        return total;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    @Override
    public String toString(){
        return "Customer name: " + customer.getUsername() + "\nOrder ID: "+ orderID +"\nAddress: " + shippingAddress +
                "\nTotal Amount: " + totalCost + "\nPayment Method: "+ paymentMethod + "\nOrder Statues: " + orderStatus;
    }
    public void PrintOrder(){
        System.out.println(this.toString());
        for(int i = 0; i < products.length; i++){
            System.out.println("1. " + this.toString() + "products: " + products[i].toString() + "\n");
        }
    }
}
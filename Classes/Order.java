package Classes;

import java.util.ArrayList;

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
        SHIPPED,
        DELIVERED,
        CANCELLED
    }

    public enum PaymentMethod {
        CREDIT_CARD,
        CASH_APP,
        CASH_ON_DELIVERY
    }
    //Constructors
    public Order(PaymentMethod paymentmethod, String shippingaddress, Customer customer){
        if (shippingaddress != null) shippingAddress = shippingaddress;
        else shippingAddress = customer.getAddress();
        paymentMethod = paymentmethod;
        shippingCost = CalculateShipping();
        totalCost = CalculateTotal();
        this.customer = customer;
        products = customer.getCart().getProducts();
        orderStatus = OrderStatus.PENDING;

        orderID = "OR" + orderNo++;
    }

    public String getOrderID() {
        return orderID;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public int CalculateShipping() {
        return 10;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double CalculateTotal() {
        return 10;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString(){
        return "Customer name: " + customer.getUsername() + "\nOrder ID: "+ orderID +"\nAddress: " + shippingAddress +
                "\nTotal Amount: " + totalCost + "\nPayment Method: "+ paymentMethod + "Order Statues" + orderStatus;
    }
}
package Classes;

import java.util.ArrayList;

public class Order {
    //Attributes
    private String orderId;
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private double subtotal;
    private double tax;
    private double shippingCost;
    private String customerID;
    private double totalAmount;
    private  String shippingAddress;
    private ArrayList<Product> products;

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
    public Order(){
        this("No ID",null,0,0,"NO ID","NO ADDRESS",null);
    }
    public Order(String ID,PaymentMethod paymentMethod,double subtotal,double shippingCost,String customerID,String shippingAddress,ArrayList<Product> products){
        orderId = ID;
        this.paymentMethod = paymentMethod;
        this.subtotal = subtotal;
        this.shippingCost = shippingCost;
        this.customerID = customerID;
        this.shippingAddress = shippingAddress;
        this.copy(products);


    }
    //setters and getters

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    //methods
    void updateTotal() {
        this.totalAmount = subtotal + tax + shippingCost;
    }
    public void copy(ArrayList<Product> products){
        for(int i =0 ;i < products.size();){
            this.products.add(products.get(i));
        }
    }
    void deleteOrder(){
        orderId = null;
        orderStatus = null;
        paymentMethod = null;
        subtotal = 0;
        tax = 0;
        shippingCost = 0;
        customerID = null;
        totalAmount = 0;
        shippingAddress = null;
    }
    @Override
    public String toString(){
        return "Customer ID: "+customerID+"\nOrder ID: "+orderId+"\nAddress: "+shippingAddress+
                "\nTotal Amount: "+totalAmount+"\nPayment Method: "+paymentMethod+"Order Statues"+orderStatus;
    }
    public void Showallorders(){

    }
}
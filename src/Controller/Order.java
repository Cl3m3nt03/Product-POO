package Controller;
// Order Class (Inherits from Pharmacy)
public class Order{
    private String orderNumber;
    private String orderDate;

    // Constructor with parameters
    public Order(String orderNumber, String orderDate) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
    }

    // Method to display order details
    public void displayOrderDetails() {
        System.out.println("Order Number: " + orderNumber + "     Order Date: " + orderDate);
    }
}

package Controller;

import java.util.*;

public class OrderHistory {

    private List<Order> orders;

    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void displayOrderHistory() {
        if (orders.isEmpty()) {
            System.out.println("No orders have been placed yet.");
            return;
        }
        System.out.println("\n===== Order History =====");
        for (int i = orders.size() - 1; i >= 0; i--) {
            Order order = orders.get(i);
            System.out.println("Order " + (i + 1));
            System.out.println("Date and Time: " + order.getOrderDate());
            System.out.println("Order Items: ");
            for (OrderItem item : order.getItems()) {
                System.out.println("Product ID: " + item.getProductId() +
                        ", Product Name: " + item.getProductName() +
                        ", Quantity: " + item.getQuantity());
            }
            System.out.println("-----------------------------");
        }
    }
}

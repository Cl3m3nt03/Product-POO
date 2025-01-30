package Controller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class OrderHistory {

    private List<Order> orders;
    private static final String HISTORY_FILE = "order_history.json";

    public OrderHistory() {
        this.orders = new ArrayList<>();
        loadOrderHistory();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
        saveOrderHistory();
    }

    private void saveOrderHistory() {
        JSONArray ordersArray = new JSONArray();
        for (Order order : orders) {
            JSONObject orderJson = new JSONObject();
            orderJson.put("date", order.getOrderDate());

            JSONArray itemsArray = new JSONArray();
            for (OrderItem item : order.getItems()) {
                JSONObject itemJson = new JSONObject();
                itemJson.put("productId", item.getProductId());
                itemJson.put("productName", item.getProductName());
                itemJson.put("quantity", item.getQuantity());
                itemsArray.put(itemJson);
            }
            orderJson.put("items", itemsArray);
            ordersArray.put(orderJson);
        }

        try(FileWriter file = new FileWriter(HISTORY_FILE)) {
            file.write(ordersArray.toString(4));
        }
        catch (IOException e) {
            System.out.println("Error while saving order history");
        }
    }

    private void loadOrderHistory() {
        File file = new File(HISTORY_FILE);
        if (!file.exists()) return;

        try {
            String content = new String(Files.readAllBytes(Paths.get(HISTORY_FILE)));
            JSONArray ordersArray = new JSONArray(content);

            for(int i = 0; i < ordersArray.length(); i++) {
                JSONObject orderJson = ordersArray.getJSONObject(i);
                String date = orderJson.getString("date");

                List<OrderItem> orderItems = new ArrayList<>();
                JSONArray itemsArray = orderJson.getJSONArray("items");

                for(int j = 0; j < itemsArray.length(); j++) {
                    JSONObject itemJson = itemsArray.getJSONObject(j);
                    int productId = itemJson.getInt("productId");
                    String productName = itemJson.getString("productName");
                    int quantity = itemJson.getInt("quantity");

                    orderItems.add(new OrderItem(productId, quantity, productName));
                }

                Order order = new StandardOrder(orderItems);
                order.setOrderDate(date);
                this.orders.add(order);
            }
        }
        catch (IOException e) {
            System.out.println("Error while loading order history");

        }
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

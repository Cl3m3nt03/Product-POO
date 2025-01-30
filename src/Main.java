import Controller.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Controller.Statistics.saveStatisticsToFile;

public class Main {


    public static void main(String[] args) {
        OrderHistory orderHistory = new OrderHistory();

        // Create a Pharmacy object to manage the stock from the JSON file
        Pharmacy pharmacy = new Pharmacy("stocks_pharma.json");
        String textFilePath = "statistics.txt";
        String jsonFilePath = "stocks_pharma.json";
        saveStatisticsToFile(jsonFilePath, textFilePath);

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the menu
            System.out.println("\n===== Menu =====");
            System.out.println("1. Add a product");
            System.out.println("2. Remove a product");
            System.out.println("3. Place a customer order");
            System.out.println("4. Update statistics file");
            System.out.println("5. View order history");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1/2/3/4/5/6): ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the remaining newline

            if (choice == 1) {
                // Add a product
                System.out.println("\n--- Add a Product ---");

                // Generate a unique ID
                int id = pharmacy.getNextProductId();
                System.out.println("Generated product ID: " + id);

                System.out.print("Enter product name: ");
                String name = scanner.nextLine();

                System.out.print("Enter product price: ");
                double price = scanner.nextDouble();
                scanner.nextLine();  // Consume the newline

                System.out.print("Enter stock quantity: ");
                int stockQuantity = scanner.nextInt();
                scanner.nextLine();  // Consume the newline

                System.out.print("Enter product description: ");
                String description = scanner.nextLine();

                // Ask for category and subcategory
                System.out.print("Enter product category: ");
                String category = scanner.nextLine();

                System.out.print("Enter product subcategory: ");
                String subCategory = scanner.nextLine();

                // Create the product and add it to the pharmacy
                Product newProduct = new Product(id, name, price, stockQuantity, description);
                pharmacy.addProduct(newProduct, category, subCategory);

            } else if (choice == 2) {
                // Remove a product
                System.out.println("\n--- Remove a Product ---");

                System.out.print("Enter product ID to remove: ");
                int productId = scanner.nextInt();
                scanner.nextLine();  // Consume the newline

                System.out.print("Enter product category: ");
                String category = scanner.nextLine();

                System.out.print("Enter product subcategory: ");
                String subCategory = scanner.nextLine();

                pharmacy.deleteProduct(productId, category, subCategory);

            } else if (choice == 3) {
                // Place a customer order
                System.out.println("\n--- Place a Customer Order ---");
                List<OrderItem> orderItems = new ArrayList<>();

                while (true) {
                    System.out.print("Enter product ID (-1 to finish): ");
                    int productId = scanner.nextInt();
                    if (productId == -1) break;

                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline

                    String productName = pharmacy.getProductNameById(productId);
                    if (productName != null) {
                        orderItems.add(new OrderItem(productId, quantity, productName));
                    } else {
                        System.out.println("Product ID not found.");
                    }                }

                // Choose order type
                System.out.print("Order type (1: Standard, 2: Urgent): ");
                int orderType = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                Order order;
                if (orderType == 2) {
                    order = new UrgentOrder(orderItems);
                } else {
                    order = new StandardOrder(orderItems);
                }

                // Validate and process the order
                if (order.validateOrder(pharmacy)) {
                    order.processOrder(pharmacy);
                    // Ajouter la commande à l'historique
                    orderHistory.addOrder(order);
                    System.out.println("Order added to history.");
                } else {
                    System.out.println("Invalid order: insufficient stock.");
                }

            } else if (choice == 4) {
                // Update statistics file
                System.out.println("\n--- Update Statistics File ---");
                saveStatisticsToFile(jsonFilePath, textFilePath);

            } else if (choice == 5) {
                // View order history
                orderHistory.displayOrderHistory();

            } else if (choice == 6) {
                // Exit the application
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close();  // Close the scanner
    }
}

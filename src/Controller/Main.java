package Controller;


import java.util.Scanner;

import static Controller.Statistics.saveStatisticsToFile;

public class Main {
    public static void main(String[] args) {
        // Create a Pharmacy object to manage the stock from the JSON file
        Pharmacy pharmacy = new Pharmacy("stocks_pharma.json");
        String textFilePath = "statistics.txt";
        String jsonFilePath = "stocks_pharma.json";

        saveStatisticsToFile(jsonFilePath, textFilePath);


        // Scanner to take user inputs
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the menu
            System.out.println("===== Menu =====");
            System.out.println("1. Add a product");
            System.out.println("2. Delete a product");
            System.out.println("3. Update statistics file");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1/2/3/4): ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the remaining newline

            if (choice == 1) {
                // Add a product
                System.out.println("\n--- Add a product ---");

                // Generate a unique ID
                int id = pharmacy.getNextProductId();
                System.out.println("Generated product ID: " + id);

                System.out.print("Enter the product name: ");
                String name = scanner.nextLine();

                System.out.print("Enter the product price: ");
                double price = scanner.nextDouble();
                scanner.nextLine();  // Consume the remaining newline


                System.out.print("Enter the product stock quantity: ");
                int stockQuantity = scanner.nextInt();
                scanner.nextLine();  // Consume the remaining newline

                System.out.print("Enter the product description: ");
                String description = scanner.nextLine();

                // Create a new Product object with the user's input
                Product newProduct = new Product(id, name, price, stockQuantity, description);

                // Ask the user for the category and sub-category
                System.out.print("Enter the product category: ");
                String category = scanner.nextLine();

                System.out.print("Enter the product sub-category: ");
                String subCategory = scanner.nextLine();

                // Add the product to the pharmacy
                pharmacy.addProduct(newProduct, category, subCategory);

            } else if (choice == 2) {
                // Delete a product
                System.out.println("\n--- Delete a product ---");

                // Ask for the product ID to delete
                System.out.print("Enter the product id to delete: ");
                int productId = scanner.nextInt();
                scanner.nextLine();  // Consume the remaining newline

                // Ask for the category and sub-category
                System.out.print("Enter the product category: ");
                String category = scanner.nextLine();

                System.out.print("Enter the product sub-category: ");
                String subCategory = scanner.nextLine();

                // Delete the product from the pharmacy
                pharmacy.deleteProduct(productId, category, subCategory);

            } else if (choice == 3) {
                // Update statistics file
                System.out.println("\n--- Update Statistics File ---");

                // Call the method to regenerate statistics
                saveStatisticsToFile(jsonFilePath, textFilePath);
            } else if (choice == 4) {
                // Exit the application
                System.out.println("Goodbye!");
                break;
            }
            else {
                // If the user enters an invalid option
                System.out.println("Invalid option, please try again.");
            }
        }
        scanner.close();  // Close the scanner

    }
}

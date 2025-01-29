package Controller;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Pharmacy {
    private JSONObject stock;

    // Constructor to load the JSON file
    public Pharmacy(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            this.stock = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add a product
    public void addProduct(Product product, String category, String subCategory) {
        // Get the "pharmacy" object in the JSON
        JSONObject pharmacy = stock.getJSONObject("pharmacy");

        // Get the list of products in the pharmacy
        JSONArray products = pharmacy.getJSONArray("products");

        // Variables to check if the category and sub-category are found
        boolean categoryFound = false;
        boolean subCategoryFound = false;

        // Loop through all categories of products in the pharmacy
        for (int i = 0; i < products.length(); i++) {
            // Get each product object from the list
            JSONObject productJSON = products.getJSONObject(i);

            // Debug: Print the category and sub-category
            System.out.println("Checking category: " + productJSON.getString("category"));
            System.out.println("Checking sub-category: " + productJSON.getString("subCategory"));

            // Check if the category and sub-category match
            if (productJSON.getString("category").equals(category) &&
                    productJSON.getString("subCategory").equals(subCategory)) {

                // If the category and sub-category are found, add the product
                subCategoryFound = true;
                categoryFound = true;

                productJSON.getJSONArray("products").put(product.toJSON());  // Add the product
                System.out.println("Product added to the existing sub-category.");

                // Save the JSON file after modification
                saveStockToFile("stocks_pharma.json");

                return;  // Exit as soon as the product is added
            }
        }

        // If the category and sub-category are not found
        if (!subCategoryFound) {
            System.out.println("The sub-category does not exist in the category, no product added.");
        }

        if (!categoryFound) {
            System.out.println("The category does not exist in the pharmacy, no product added.");
        }

    }

    // Method to delete a product
    public void deleteProduct(int productId, String category, String subCategory) {
        // Get the "pharmacy" object in the JSON
        JSONObject pharmacy = stock.getJSONObject("pharmacy");

        // Get the list of products in the pharmacy
        JSONArray products = pharmacy.getJSONArray("products");

        // Variables to check if the category and sub-category are found
        boolean categoryFound = false;
        boolean subCategoryFound = false;
        boolean productDeleted = false;

        // Loop through all categories of products in the pharmacy
        for (int i = 0; i < products.length(); i++) {
            // Get each product object from the list
            JSONObject productJSON = products.getJSONObject(i);

            // Debug: Print the category and sub-category
            System.out.println("Checking category: " + productJSON.getString("category"));
            System.out.println("Checking sub-category: " + productJSON.getString("subCategory"));

            // Check if the category and sub-category match
            if (productJSON.getString("category").equals(category) &&
                    productJSON.getString("subCategory").equals(subCategory)) {

                // If the category and sub-category are found
                subCategoryFound = true;
                categoryFound = true;

                // Get the list of products in this sub-category
                JSONArray subCategoryProducts = productJSON.getJSONArray("products");

                // Loop through the products in the sub-category
                for (int j = 0; j < subCategoryProducts.length(); j++) {
                    JSONObject productInSubCategory = subCategoryProducts.getJSONObject(j);

                    // Check if the product ID matches the one to be deleted
                    if (productInSubCategory.getInt("id") == productId) {
                        // Delete the product
                        subCategoryProducts.remove(j);
                        productDeleted = true;
                        System.out.println("Product deleted from the sub-category.");

                        // Save the JSON file after modification
                        saveStockToFile("stocks_pharma.json");
                        return;  // Exit as soon as the product is deleted
                    }
                }
            }
        }

        // If the sub-category or category was not found
        if (!subCategoryFound) {
            System.out.println("The sub-category does not exist in the category, no product deleted.");
        }

        if (!categoryFound) {
            System.out.println("The category does not exist in the pharmacy, no product deleted.");
        }

        // If the product was not found
        if (!productDeleted) {
            System.out.println("No product found with the specified ID in the given category and sub-category.");
        }
    }

    // Method to save the JSON file after modification
    private void saveStockToFile(String filePath) {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(stock.toString(4)); // Formatting with indentation
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
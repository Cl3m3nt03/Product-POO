package Controller;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Statistics {

    private static final int STOCK_INITIAL = 500; // We assume each product had 500 units initially

    private static JSONObject loadStockFromFile(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Find the product with the lowest stock (most sold)
    private static JSONObject getMostSoldProduct(String filePath) {
        JSONObject stock = loadStockFromFile(filePath);
        if (stock == null) return null;

        JSONObject pharmacy = stock.getJSONObject("pharmacie");
        JSONArray categories = pharmacy.getJSONArray("produits");

        JSONObject mostSoldProduct = null;
        int lowestStock = Integer.MAX_VALUE; // Set a very large value initially

        // Loop through all product categories
        for (int i = 0; i < categories.length(); i++) {
            JSONObject category = categories.getJSONObject(i);
            JSONArray products = category.getJSONArray("produits");

            // Loop through all products in the category
            for (int j = 0; j < products.length(); j++) {
                JSONObject product = products.getJSONObject(j);
                int stockRemaining = product.getInt("quantiteStock"); // Remaining stock

                if (stockRemaining < lowestStock) {
                    lowestStock = stockRemaining;
                    mostSoldProduct = product; // Update the most sold product
                }
            }
        }

        return mostSoldProduct;
    }

    // Generate and save statistics to a text file
    public static void saveStatisticsToFile(String jsonFilePath, String textFilePath) {
        JSONObject stock = loadStockFromFile(jsonFilePath);
        if (stock == null) return;

        JSONObject pharmacy = stock.getJSONObject("pharmacie");
        JSONArray categories = pharmacy.getJSONArray("produits");

        JSONObject mostSoldProduct = getMostSoldProduct(jsonFilePath);
        double totalSalesRevenue = 0.0; // Variable to store the total revenue

        List<ProductSales> productSalesList = new ArrayList<>();

        for (int i = 0; i < categories.length(); i++) {
            JSONObject category = categories.getJSONObject(i);
            JSONArray products = category.getJSONArray("produits");

            for (int j = 0; j < products.length(); j++) {
                JSONObject product = products.getJSONObject(j);
                String productName = product.getString("nom");
                int stockRemaining = product.getInt("quantiteStock");

                int quantitySold = STOCK_INITIAL - stockRemaining;
                productSalesList.add(new ProductSales(productName, quantitySold));

            }
        }

        // Sort the products by quantity sold in descending order (most sold first)
        Collections.sort(productSalesList, new Comparator<ProductSales>() {
            @Override
            public int compare(ProductSales p1, ProductSales p2) {
                return Integer.compare(p2.quantitySold, p1.quantitySold); // Sorting in descending order
            }
        });


        try (FileWriter writer = new FileWriter(textFilePath)) {
            writer.write("===== STATISTIQUES DES VENTES =====\n\n");

            // Display the most sold product
            if (mostSoldProduct != null) {
                writer.write("📌 Produit le plus vendu :\n");
                writer.write("Nom: " + mostSoldProduct.getString("nom") + "\n");
                writer.write("ID: " + mostSoldProduct.getInt("id") + "\n");
                writer.write("Prix: " + mostSoldProduct.getDouble("prix") + " €\n");
                writer.write("Stock restant: " + mostSoldProduct.getInt("quantiteStock") + "\n");
                writer.write("Description: " + mostSoldProduct.getString("description") + "\n\n");
            }

            // Display quantities sold and calculate total sales revenue
            writer.write("📊 Quantités vendues par produit :\n");

            // Display sorted product sales
            for (ProductSales product : productSalesList) {
                writer.write("  - " + product.productName + ": " + product.quantitySold + " units sold\n");
            }

            // Display total revenue
            writer.write("\n💰 Chiffre d'affaires total : " + totalSalesRevenue + " €\n");

            System.out.println("✅ Statistiques enregistrées dans " + textFilePath);
        } catch (IOException e) {
            System.out.println("Erreur : Impossible d'écrire le fichier texte.");
            e.printStackTrace();
        }
    }



}
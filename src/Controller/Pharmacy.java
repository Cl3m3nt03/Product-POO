package Controller;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONObject;


public class Pharmacy {
    private JSONObject stock;
    private String nom;
    private String adresse;
    // Constructor to load the JSON file
    public Pharmacy(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            this.stock = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void afficherDetails() {
        System.out.println("Pharmacie: " + nom);
        System.out.println("Adresse: " + adresse);
    }

    public static void ReadJson(){
            JSONParser parser = new JSONParser();

            try (FileReader reader = new FileReader("src\\stocks_pharma.json")) {
                // Read JSON file
                Object object = parser.parse(reader);
                JSONObject jsonObject = (JSONObject) object;

                // Access pharmacy
                JSONObject pharmacie = (JSONObject) jsonObject.get("pharmacie");
                String nomPharmacie = (String) pharmacie.get("nom");
                System.out.println("Nom de la pharmacie: " + nomPharmacie);

                // Access produits
                JSONArray produits = (JSONArray) pharmacie.get("produits");
                for (Object produitObj : produits) {
                    JSONObject produit = (JSONObject) produitObj;
                    String categorie = (String) produit.get("categorie");
                    String sousCategorie = (String) produit.get("sousCategorie");
                    System.out.println("\nCatégorie: " + categorie);
                    System.out.println("Sous-catégorie: " + sousCategorie);

                    JSONArray produitsDetails = (JSONArray) produit.get("produits");


                    ArrayList<JSONObject> produitsDetailsList = new ArrayList<>();

                    // add JsonObject to list
                    for (Object produitDetailObj : produitsDetails) {
                        JSONObject produitDetail = (JSONObject) produitDetailObj;
                        produitsDetailsList.add(produitDetail);
                    }
                    produitsDetailsList.sort(Comparator.comparing(p -> (String) p.get("nom")));

                    // Display Products
                    for (JSONObject produitDetail : produitsDetailsList) {
                        String nomProduit = (String) produitDetail.get("nom");
                        Double prixProduit = (Double) produitDetail.get("prix");
                        Long quantiteStock = (Long) produitDetail.get("quantiteStock");
                        String description = (String) produitDetail.get("description");

                        System.out.println("Nom du produit: " + nomProduit);
                        System.out.println("Prix: " + prixProduit);
                        System.out.println("Quantité en stock: " + quantiteStock);
                        System.out.println("Description: " + description);
                        System.out.println();
                    }
                }

            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
    }

    public void addProduct(Product product, String category, String subCategory) {
        // Get the "pharmacy" object in the JSON
        JSONObject pharmacy = stock.getJSONObject("pharmacie");

        // Get the list of products in the pharmacy
        JSONArray products = pharmacy.getJSONArray("produits");

        // Variables to check if the category and sub-category are found
        boolean categoryFound = false;
        boolean subCategoryFound = false;

        // Loop through all categories of products in the pharmacy
        for (int i = 0; i < products.length(); i++) {
            // Get each product object from the list
            JSONObject productJSON = products.getJSONObject(i);

            // Debug: Print the category and sub-category
            System.out.println("Checking category: " + productJSON.getString("categorie"));
            System.out.println("Checking sub-category: " + productJSON.getString("sousCategorie"));

            // Check if the category and sub-category match
            if (productJSON.getString("categorie").equals(category) &&
                    productJSON.getString("sousCategorie").equals(subCategory)) {

                // If the category and sub-category are found, add the product
                subCategoryFound = true;
                categoryFound = true;

                productJSON.getJSONArray("produits").put(product.toJSON());  // Add the product
                System.out.println("Product added to the existing sub-category.");

                // Save the JSON file after modification
                saveStockToFile("src\\stocks_pharma.json");

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
        JSONObject pharmacy = stock.getJSONObject("pharmacie");

        // Get the list of products in the pharmacy
        JSONArray products = pharmacy.getJSONArray("produits");

        // Variables to check if the category and sub-category are found
        boolean categoryFound = false;
        boolean subCategoryFound = false;
        boolean productDeleted = false;

        // Loop through all categories of products in the pharmacy
        for (int i = 0; i < products.length(); i++) {
            // Get each product object from the list
            JSONObject productJSON = products.getJSONObject(i);

            // Debug: Print the category and sub-category
            System.out.println("Checking category: " + productJSON.getString("categorie"));
            System.out.println("Checking sub-category: " + productJSON.getString("sousCategorie"));

            // Check if the category and sub-category match
            if (productJSON.getString("categorie").equals(category) &&
                    productJSON.getString("sousCategorie").equals(subCategory)) {

                // If the category and sub-category are found
                subCategoryFound = true;
                categoryFound = true;

                // Get the list of products in this sub-category
                JSONArray subCategoryProducts = productJSON.getJSONArray("produits");

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
                        saveStockToFile("src\\stocks_pharma.json");
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

    public int getNextProductId() {
        JSONObject pharmacyData = stock.getJSONObject("pharmacie");
        JSONArray categories = pharmacyData.getJSONArray("produits");

        int maxId = 0;

        // Parcourir toutes les catégories et sous-catégories pour trouver le plus grand ID
        for (int i = 0; i < categories.length(); i++) {
            JSONObject category = categories.getJSONObject(i);
            JSONArray products = category.getJSONArray("produits");

            for (int j = 0; j < products.length(); j++) {
                JSONObject product = products.getJSONObject(j);
                int currentId = product.getInt("id");
                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
        }
        return maxId + 1; // Retourne un nouvel ID unique
    }
    public void serializeJson(String filePath) {
        File fichier =  new File(filePath) ; //Creating a ‘File’ file

        // open a stream on a file
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(fichier));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // sérialization de l'objet
        try {
            oos.writeObject(this.stock.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
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

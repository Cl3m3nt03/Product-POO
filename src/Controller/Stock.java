package Controller;

import java.io.FileReader;
import java.io.FileWriter;

import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import Model.Stockable;

public class Stock implements Stockable {

    private static int nextId = 1;  // ID global qui s'incrémente à chaque ajout de produit

    public Stock(Pharmacy pharmacy) {
        super();
    }

    @Override
    public void addProduct() {
        Scanner scanner = new Scanner(System.in);

        // Collecte des informations du produit
        System.out.print("Nom du produit: ");
        String name = scanner.nextLine();

        System.out.print("Catégorie du produit: ");
        String category = scanner.nextLine();

        System.out.print("Sous-catégorie du produit: ");
        String subcategory = scanner.nextLine();

        double price;
        do {
            System.out.print("Prix du produit: ");
            price = scanner.nextDouble();
            if (price <= 0) {
                System.out.println("Prix invalide");
            }
        } while (price <= 0);

        int quantity;
        do {
            System.out.print("Quantité du produit: ");
            quantity = scanner.nextInt();
            if (quantity <= 0) {
                System.out.println("Quantité invalide");
            }
        } while (quantity <= 0);

        scanner.nextLine();  // Consommer la nouvelle ligne
        System.out.print("Description du produit: ");
        String description = scanner.nextLine();

        // Appel de la fonction pour ajouter le produit dans le fichier JSON
        writeJson(category, subcategory, name, price, quantity, description);
        System.out.println("Produit ajouté !");
    }

    public void writeJson(String category, String subcategory, String name, double price, int quantity, String description) {
        try (FileReader reader = new FileReader("stocks_pharma.json")) {
            // Lire le fichier JSON dans une String
            StringBuilder jsonStringBuilder = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                jsonStringBuilder.append((char) c);
            }

            // Parser la chaîne JSON
            JSONObject jsonObject = new JSONObject(jsonStringBuilder.toString());

            // Accéder à la pharmacie et aux produits
            JSONObject pharmacy = jsonObject.getJSONObject("pharmacie");
            JSONArray productsArray = pharmacy.getJSONArray("produits");

            boolean categoryFound = false;
            boolean subcategoryFound = false;

            // Recherche de la catégorie
            for (int i = 0; i < productsArray.length(); i++) {
                JSONObject categoryObj = productsArray.getJSONObject(i);
                String existingCategory = categoryObj.getString("categorie");

                // Si la catégorie existe, rechercher la sous-catégorie
                if (existingCategory.equalsIgnoreCase(category)) {
                    categoryFound = true;
                    JSONArray subCategoriesArray = categoryObj.getJSONArray("produits");

                    // Recherche de la sous-catégorie dans la catégorie
                    for (int j = 0; j < subCategoriesArray.length(); j++) {
                        JSONObject subCategoryObj = subCategoriesArray.getJSONObject(j);

                        if (subCategoryObj.has("sousCategorie")) {
                            String existingSubcategory = subCategoryObj.getString("sousCategorie");

                            // Si la sous-catégorie existe, ajouter le produit
                            if (existingSubcategory.equalsIgnoreCase(subcategory)) {
                                subcategoryFound = true;
                                JSONArray products = subCategoryObj.getJSONArray("produits");

                                // Créer le nouveau produit
                                JSONObject newProduct = new JSONObject();
                                newProduct.put("id", generateNewId());
                                newProduct.put("nom", name);
                                newProduct.put("prix", price);
                                newProduct.put("quantiteStock", quantity);
                                newProduct.put("description", description);

                                // Ajouter le produit à la sous-catégorie existante
                                products.put(newProduct);
                                break;
                            }
                        }
                    }

                    // Si la sous-catégorie n'existe pas, la créer et ajouter le produit
                    if (!subcategoryFound) {
                        JSONObject newSubcategory = new JSONObject();
                        newSubcategory.put("sousCategorie", subcategory);

                        JSONArray newProductArray = new JSONArray();
                        JSONObject newProduct = new JSONObject();
                        newProduct.put("id", generateNewId());
                        newProduct.put("nom", name);
                        newProduct.put("prix", price);
                        newProduct.put("quantiteStock", quantity);
                        newProduct.put("description", description);

                        newProductArray.put(newProduct);
                        newSubcategory.put("produits", newProductArray);

                        subCategoriesArray.put(newSubcategory);
                    }
                    break;
                }
            }

            // Si la catégorie n'existe pas, la créer
            if (!categoryFound) {
                JSONObject newCategory = new JSONObject();
                newCategory.put("categorie", category);

                JSONArray newSubcategoryArray = new JSONArray();
                JSONObject newSubcategory = new JSONObject();
                newSubcategory.put("sousCategorie", subcategory);

                JSONArray newProductArray = new JSONArray();
                JSONObject newProduct = new JSONObject();
                newProduct.put("id", generateNewId());
                newProduct.put("nom", name);
                newProduct.put("prix", price);
                newProduct.put("quantiteStock", quantity);
                newProduct.put("description", description);

                newProductArray.put(newProduct);
                newSubcategory.put("produits", newProductArray);
                newSubcategoryArray.put(newSubcategory);

                newCategory.put("produits", newSubcategoryArray);
                productsArray.put(newCategory);
            }

            // Écrire le fichier JSON mis à jour avec la mise en forme
            try (FileWriter writer = new FileWriter("stocks_pharma.json")) {
                writer.write(jsonObject.toString(4)); // Utilisation de l'indentation pour une mise en forme lisible
                writer.flush();
            }

            System.out.println("✅ Le produit a été ajouté avec succès!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    private static int generateNewId() {
        // Cette méthode doit générer un ID unique pour chaque produit, ici on l'incrémente simplement
        // Assurez-vous de gérer l'incrémentation proprement pour éviter les doublons.
        int nextId = 16;  // Dernier ID utilisé dans ton JSON est 15, donc commencer à 16

        return nextId++;  // Incrémente l'ID avant de le retourner
    }


    @Override
    public void displayStock() {
        Pharmacy.ReadJson();
    }
}

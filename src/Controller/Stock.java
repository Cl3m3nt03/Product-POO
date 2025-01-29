package Controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import Model.Stockable;

public class Stock implements Stockable {

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

        JSONObject newProduct = new JSONObject();
        newProduct.put("id", 0); // L'ID sera généré dans writeJson
        newProduct.put("nom", name);
        newProduct.put("prix", price);
        newProduct.put("quantiteStock", quantity);
        newProduct.put("description", description);

        writeJson(category, subcategory, newProduct);
    }

    private void writeJson(String category, String subcategory, JSONObject newProduct) {
        try {
            // Lecture du fichier JSON existant
            FileReader reader = new FileReader("stocks_pharma.json");
            StringBuilder jsonContent = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                jsonContent.append((char) i);
            }
            reader.close();

            // Convertir le contenu JSON en objet
            JSONObject jsonObject = new JSONObject(jsonContent.toString());
            JSONObject pharmacie = jsonObject.getJSONObject("pharmacie");
            JSONArray produitsArray = pharmacie.getJSONArray("produits");

            boolean categoryFound = false;
            boolean subcategoryFound = false;
            JSONObject targetCategory = null;
            JSONArray targetSubcategory = null;

            // Recherche de la bonne catégorie et sous-catégorie
            for (int j = 0; j < produitsArray.length(); j++) {
                JSONObject currentCategory = produitsArray.getJSONObject(j);
                if (currentCategory.getString("categorie").equalsIgnoreCase(category)) {
                    categoryFound = true;

                    if (currentCategory.getString("sousCategorie").equalsIgnoreCase(subcategory)) {
                        subcategoryFound = true;
                        targetCategory = currentCategory;
                        targetSubcategory = currentCategory.getJSONArray("produits");
                        break;
                    }
                }
            }

            // Génération d'un nouvel ID unique
            int newId = generateNewId(targetSubcategory);
            newProduct.put("id", newId); // Assigner le nouvel ID

            if (subcategoryFound) {
                // Ajouter le produit dans la sous-catégorie existante
                targetSubcategory.put(newProduct);
            } else if (categoryFound) {
                // Ajouter la nouvelle sous-catégorie dans la catégorie existante
                JSONObject newSubcategory = new JSONObject();
                newSubcategory.put("sousCategorie", subcategory);
                newSubcategory.put("produits", new JSONArray().put(newProduct));

                // Correction ici : Ajouter dans un tableau sous-catégorie
                JSONArray subcategoriesArray = targetCategory.optJSONArray("produits");
                if (subcategoriesArray == null) {
                    subcategoriesArray = new JSONArray();
                    targetCategory.put("produits", subcategoriesArray);
                }

                subcategoriesArray.put(newSubcategory);
            } else {
                // Créer une nouvelle catégorie si elle n'existe pas
                JSONObject newCategory = new JSONObject();
                newCategory.put("categorie", category);
                newCategory.put("sousCategorie", subcategory);

                JSONArray newProductsArray = new JSONArray();
                newProductsArray.put(newProduct);

                newCategory.put("produits", newProductsArray);
                produitsArray.put(newCategory);
            }

            // Écriture dans le fichier JSON
            FileWriter file = new FileWriter("stocks_pharma.json");
            file.write(jsonObject.toString(4)); // Indentation pour lisibilité
            file.close();

            System.out.println("Produit ajouté avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout du produit.");
        }
    }

    private int generateNewId(JSONArray produits) {
        int maxId = 0;
        if (produits != null) {
            for (int i = 0; i < produits.length(); i++) {
                int currentId = produits.getJSONObject(i).getInt("id");
                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
        }
        return maxId + 1;
    }

    @Override
    public void displayStock() {
        Pharmacy.ReadJson();
    }
}

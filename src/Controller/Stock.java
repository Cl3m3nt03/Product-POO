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

                    // Recherche de la sous-catégorie dans la catégorie
                    JSONArray subcategoriesArray = currentCategory.optJSONArray("sousCategories");
                    if (subcategoriesArray == null) {
                        subcategoriesArray = new JSONArray();
                        currentCategory.put("sousCategories", subcategoriesArray);
                    }

                    // Recherche de la sous-catégorie
                    for (int k = 0; k < subcategoriesArray.length(); k++) {
                        JSONObject currentSubcategory = subcategoriesArray.getJSONObject(k);
                        if (currentSubcategory.getString("sousCategorie").equalsIgnoreCase(subcategory)) {
                            subcategoryFound = true;
                            targetSubcategory = currentSubcategory.getJSONArray("produits");
                            break;
                        }
                    }

                    // Si la sous-catégorie n'est pas trouvée, on la crée
                    if (!subcategoryFound) {
                        JSONObject newSubcategory = new JSONObject();
                        newSubcategory.put("sousCategorie", subcategory);
                        newSubcategory.put("produits", new JSONArray());
                        subcategoriesArray.put(newSubcategory);
                        targetSubcategory = newSubcategory.getJSONArray("produits");
                    }
                    break;
                }
            }

            // Si la catégorie n'est pas trouvée, on la crée
            if (!categoryFound) {
                JSONObject newCategory = new JSONObject();
                newCategory.put("categorie", category);
                JSONArray newSubcategoriesArray = new JSONArray();

                JSONObject newSubcategory = new JSONObject();
                newSubcategory.put("sousCategorie", subcategory);
                newSubcategory.put("produits", new JSONArray());

                newSubcategoriesArray.put(newSubcategory);
                newCategory.put("sousCategories", newSubcategoriesArray);
                produitsArray.put(newCategory);

                targetSubcategory = newSubcategory.getJSONArray("produits");
            }

            // Génération d'un nouvel ID unique pour le produit
            int newId = generateNewId(targetSubcategory);
            newProduct.put("id", newId); // Assigner le nouvel ID

            // Ajouter le produit à la sous-catégorie
            targetSubcategory.put(newProduct);

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

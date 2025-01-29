package Controller;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Pharmacy {
    private JSONObject stock;

    // Constructeur pour charger le fichier JSON
    public Pharmacy(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            this.stock = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour ajouter un produit
    public void ajouterProduit(Product product, String categorie, String sousCategorie) {
        // Récupérer l'objet "pharmacie" dans le JSON
        JSONObject pharmacie = stock.getJSONObject("pharmacie");

        // Récupérer la liste des produits dans la pharmacie
        JSONArray produits = pharmacie.getJSONArray("produits");

        // Variable pour savoir si la catégorie et la sous-catégorie sont trouvées
        boolean categorieTrouvee = false;
        boolean sousCategorieTrouvee = false;

        // Boucle à travers toutes les catégories de produits dans la pharmacie
        for (int i = 0; i < produits.length(); i++) {
            // Récupérer chaque objet de produit dans la liste
            JSONObject produitJSON = produits.getJSONObject(i);

            // Afficher la catégorie et sous-catégorie pour déboguer
            System.out.println("Vérification de la catégorie : " + produitJSON.getString("categorie"));
            System.out.println("Vérification de la sous-catégorie : " + produitJSON.getString("sousCategorie"));

            // Vérifier si la catégorie et la sous-catégorie correspondent
            if (produitJSON.getString("categorie").equals(categorie) &&
                    produitJSON.getString("sousCategorie").equals(sousCategorie)) {

                // Si la catégorie et la sous-catégorie sont trouvées, ajout du produit
                sousCategorieTrouvee = true;
                categorieTrouvee = true;

                produitJSON.getJSONArray("produits").put(product.toJSON());  // Ajouter le produit
                System.out.println("Produit ajouté à la sous-catégorie existante.");

                // Sauvegarder le fichier JSON après modification
                saveStockToFile("stocks_pharma.json");

                return;  // Sortir dès que le produit est ajouté
            }
        }

        // Si la catégorie et la sous-catégorie ne sont pas trouvées
        if (!sousCategorieTrouvee) {
            System.out.println("La sous-catégorie n'existe pas dans la catégorie, aucun produit ajouté.");
        }

        if (!categorieTrouvee) {
            System.out.println("La catégorie n'existe pas dans la catégorie, aucun produit ajouté.");
        }


    }

    // Méthode pour sauvegarder le fichier JSON après modification
    private void saveStockToFile(String filePath) {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(stock.toString(4)); // Formatage avec indentation
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
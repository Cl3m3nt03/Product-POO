package Controller;

import org.json.JSONObject;

class Product {
    private int id;
    private String nom;
    private double prix;
    private int quantiteStock;
    private String description;

    // Constructeur
    public Product(int id, String nom, double prix, int quantiteStock, String description) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantiteStock = quantiteStock;
        this.description = description;
    }

    // Convertir un produit en objet JSON
    public JSONObject toJSON() {
        JSONObject productJSON = new JSONObject();
        productJSON.put("id", this.id);
        productJSON.put("nom", this.nom);
        productJSON.put("prix", this.prix);
        productJSON.put("quantiteStock", this.quantiteStock);
        productJSON.put("description", this.description);
        return productJSON;
    }
}
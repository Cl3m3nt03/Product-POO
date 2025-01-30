package Controller;

import org.json.simple.JSONObject;

public class Product implements Comparable<Product> {
    private int id;
    private String category;
    private String sub_category;
    private String name;
    private double price;
    private int quantity;
    private String description;

    public Product(String category, String sub_category, String name, double price, int quantity, String description) {
        this.category = category;
        this.sub_category = sub_category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public String getCategory(){
        return category;
    }

    public String getSub_category() {
        return sub_category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Product other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    public Product(int id, String nom, double prix, int quantiteStock, String description) {
        this.id = id;
        this.name = nom;
        this.price = prix;
        this.quantity = quantiteStock;
        this.description = description;
    }

    // Convertir un produit en objet JSON
    public JSONObject toJSON() {
        JSONObject productJSON = new JSONObject();
        productJSON.put("id", this.id);
        productJSON.put("nom", this.name);
        productJSON.put("prix", this.price);
        productJSON.put("quantiteStock", this.quantity);
        productJSON.put("description", this.description);
        return productJSON;
    }
}


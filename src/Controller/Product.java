package Controller;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private String category;
    private String subcategory;
    private double price;
    private int quantity;
    private String description;



    public Product(String name, String category, String subcategory, double price, int quantity, String description) {
        this.name = name;
        this.category = category;
        this.subcategory = subcategory;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
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

    public String toString() {
        return name + " " + category + " " + subcategory + " " + price + " " + quantity + " " + description;
    }
}

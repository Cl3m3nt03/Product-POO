package Controller;

public class Product{

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

    public String toString() {
        return name + " | " + category + " | " + subcategory + " | " + price + " | " + quantity + " | " + description;
    }

}

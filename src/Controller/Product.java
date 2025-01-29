package Controller;

public class Product{

    private int id;
    private String name;
    private String category;
    private String subcategory;
    private double price;
    private int quantity;
    private String description;

    public Product(int id, String name, String category, String subcategory, double price, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.subcategory = subcategory;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public String toString() {
        return id + " " + name + " " + category + " " + subcategory + " " + price + " " + quantity + " " + description;
    }

}

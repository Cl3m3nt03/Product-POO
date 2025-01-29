package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.Stockable;

public class Stock implements Stockable {

    private List<Product> stock = new ArrayList<>();

    public Stock(Pharmacy pharmacy) {
        super();
    }

    @Override
    public void addProduct() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Product Category: ");
        String category = scanner.nextLine();

        double price;
        do {
            System.out.print("Product Price: ");
            price = scanner.nextDouble();
            if (price <= 0) {
                System.out.println("Invalid Price");
            }
        } while (price <= 0);

        int quantity;
        do {
            System.out.print("Product Quantity: ");
            quantity = scanner.nextInt();
            if (quantity <= 0) {
                System.out.println("Invalid Quantity");
            }
        } while (quantity <= 0);

        Product product = new Product(name, category, price, quantity);
        stock.add(product);
        System.out.println("Product Added !");
    }

    // A SUPPRIMER DANS LE STOCKABLE QUAND LA STORY 1 A ETE FAITE
    @Override
    public void displayStock() {
        System.out.println("Inventaire");
        if (stock.isEmpty()) {
            System.out.println("Nothing to show");
        }
        else {
            for (Product product : stock) {
                System.out.println(product);
            }
        }
    }


}

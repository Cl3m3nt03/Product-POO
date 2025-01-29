import Controller.Pharmacy;
import Controller.Stock;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Pharmacy pharmacy = new Pharmacy("Skibidi Pharmacy", "Quelque part");
        Stock stock = new Stock(pharmacy);
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("1.Add a product");
            System.out.println("2.Delete a product");
            System.out.println("3.Display all products");


            switch (scanner.nextInt()) {
                case 1:
                    stock.addProduct();
                    System.out.println(" ");
                    break;

                case 2:
                    return;

                case 3:
                    System.out.println(" ");
                    stock.displayStock();
                    System.out.println(" ");
                    break;

                default: {
                    System.out.println("Please enter a valid option");
                    break;
                }
            }
        }while (true);
    }
}
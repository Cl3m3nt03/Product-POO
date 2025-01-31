package Controller;

import Model.Rules;
import org.json.simple.parser.ParseException;
import view.Menu;

import java.io.IOException;
import java.util.Scanner;

public class Employed extends Client implements Rules {

        public Employed(String name, String password, String status) {
            super(name, password, status);
        }

        public Employed() {

        }

        @Override
        //showmenu for Employed
        public void showMenu() throws IOException, ParseException {

            Pharmacy pharmacy = new Pharmacy("stocks_pharma.json");
            Scanner sc = new Scanner(System.in);
            boolean quit = false;

            while (!quit) {
                Menu.drawTitle();
                displayMainMenuAdmin();
                System.out.print("                 Enter your choice: ");
                if (sc.hasNextLine()){
                    String choice = sc.nextLine();
                    switch (choice) {
                        case "1":
                            Order order = new Order();
                            break;
                        case "2":
                            //call function
                            FastSorting.FastSorting();
                            System.out.println("2");
                            break;
                        case "3":
                            Search.searchProduct();
                            break;
                        case "4":
                            String textFilePath = "statistics.txt";
                            String jsonFilePath = "stocks_pharma.json";
                            Statistics.saveStatisticsToFile(jsonFilePath, textFilePath);
                            break;
                        case "5":


                            System.out.println("\n--- Add a Product ---");

                            // Generate a unique ID
                            int id = pharmacy.getNextProductId();
                            System.out.println("Generated product ID: " + id);

                            System.out.print("Enter product name: ");
                            String name = sc.nextLine();

                            System.out.print("Enter product price: ");
                            double price = sc.nextDouble();
                            sc.nextLine();  // Consume the newline

                            System.out.print("Enter stock quantity: ");
                            int stockQuantity = sc.nextInt();
                            sc.nextLine();  // Consume the newline

                            System.out.print("Enter product description: ");
                            String description = sc.nextLine();

                            // Ask for category and subcategory
                            System.out.print("Enter product category: ");
                            String category = sc.nextLine();

                            System.out.print("Enter product subcategory: ");
                            String subCategory = sc.nextLine();

                            // Create the product and add it to the pharmacy
                            Product newProduct = new Product(id, name, price, stockQuantity, description);
                            pharmacy.addProduct(newProduct, category, subCategory);

                            break;
                        case "6":
                            System.out.println("\n--- Remove a Product ---");

                            System.out.print("Enter product ID to remove: ");
                            int productId = sc.nextInt();
                            sc.nextLine();  // Consume the newline

                            System.out.print("Enter product category: ");
                            String category1 = sc.nextLine();

                            System.out.print("Enter product subcategory: ");
                            String subCategory1 = sc.nextLine();

                            pharmacy.deleteProduct(productId, category1, subCategory1);

                            break;
                        case "7":

                            System.out.println("                 Thanks for playing. See you next time!");
                            System.exit(0);
                            quit = true;
                            break;
                        default:
                            System.out.println("                 Invalid choice. Please try again.");
                            break;
                    }
                }
                System.out.println();
            }
            sc.close();
        }

        //Show Visual Employed menu
        private void displayMainMenuAdmin() {
            final String CYAN = "\u001B[36m";
            final String RESET = "\u001B[0m";
            final String GREEN = "\u001B[32m";
            final String YELLOW = "\u001B[33m";
            final String RED = "\u001B[31m";

            System.out.println("                ========================= Employed MENU ========================= \n");
            System.out.println("                                           1." + CYAN + "Command" + RESET);
            System.out.println("                                           2." + GREEN + "Product in stock" + RESET);
            System.out.println("                                           3." + YELLOW + "Search" + RESET);
            System.out.println("                ============================ Panel employed ======================= ");
            System.out.println("                                           4." + RED + "Statistic" + RESET);
            System.out.println("                                           5." + RED + "Add Product" + RESET);
            System.out.println("                                           6." + RED + "Delete Product" + RESET);
            System.out.println("                 ============================================================= ");
            System.out.println("                                           7." + RED + "Quit" + RESET);
            System.out.println("                 ============================================================= ");
        }

        @Override
        public void Get_Rules() {

        }

}

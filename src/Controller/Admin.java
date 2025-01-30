package Controller;

import Model.Rules;
import org.json.simple.parser.ParseException;
import view.Menu;

import java.io.IOException;
import java.util.Scanner;

public class Admin extends Client implements Rules {

    public Admin(String name, String password, String status) {
        super(name, password, status);
    }

    public Admin() {

    }

    @Override
    public void showMenu() throws IOException, ParseException {

        Scanner sc = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            // Display the main menu
            Menu.drawTitle();
            displayMainMenuAdmin();
            // Get user's choice
            System.out.print("                 Enter your choice: ");
            if (sc.hasNextLine()){
                String choice = sc.nextLine();
                // Handle the user's choice
                switch (choice) {
                    case "1":
                        Search.searchProduct();
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
                        System.out.print("Admin");
                        break;
                    case "5":

                        System.out.println("                 Thanks for playing. See you next time!");
                        System.exit(0);
                        quit = true;
                        break;
                    default:
                        System.out.println("                 Invalid choice. Please try again.");
                        break;
                }
            }
            // Add a blank line for readability
            System.out.println();
        }
        sc.close();
    }

    private void displayMainMenuAdmin() {
        final String CYAN = "\u001B[36m";
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String RED = "\u001B[31m";

        System.out.println("                ========================= MAIN MENU ========================= \n");
        System.out.println("                                           1." + CYAN + "Command" + RESET);
        System.out.println("                                           2." + GREEN + "Product in stock" + RESET);
        System.out.println("                                           3." + YELLOW + "Search" + RESET);
        System.out.println("                ============================ Panel Admin ======================= ");
        System.out.println("                                           4." + RED + "Admin" + RESET);
        System.out.println("                 ============================================================= ");
        System.out.println("                                           5." + RED + "Quit\n" + RESET);
        System.out.println("                 ============================================================= ");
    }

    @Override
    public void Get_Rules() {

    }
}

package Controller;

import Model.Rules;
import org.json.simple.parser.ParseException;
import view.Menu;
import view.MenuManage;

import java.io.IOException;
import java.util.Scanner;

public class Client extends User implements Rules {

    public Client(String name, String password, String status) {
        super(name, password, status);
    }

    public void get_user_info() {
        System.out.print("Le Pseudo : " + this.getName() + "Le Mot de passe" +this.getPassword());
    }

    @Override
    public void showMenu() throws IOException, ParseException {

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            // Display the main menu
            Menu.drawTitle();
            displayMainMenuClient();
            // Get user's choice
            System.out.print("                 Enter your choice: ");
            if (scanner.hasNextLine()){
                String choice = scanner.nextLine();
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
                        MenuManage.MenuManageProduct();
                        break;
                    case "4":

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
        scanner.close();

    }

    private void displayMainMenuClient() {
        final String CYAN = "\u001B[36m";
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String RED = "\u001B[31m";

            System.out.println("                ========================= MAIN MENU ========================= \n");
            System.out.println("                                           1." + CYAN + "Command" + RESET);
            System.out.println("                                           2." + GREEN + "Product in stock" + RESET);
            System.out.println("                                           3." + RED + "Quit\n" + RESET);
            System.out.println("                 ============================================================= ");
    }

    @Override
    public void Get_Rules() {

    }
}

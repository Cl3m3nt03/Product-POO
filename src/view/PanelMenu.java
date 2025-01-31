package view;

import Controller.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class PanelMenu {
    public static void showMenu() throws IOException, ParseException {
        Account account = new Account();

        Scanner sc = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            displayMainMenuAdmin();
            if (sc.hasNextLine()){
                String choice = sc.nextLine();
                // Handle the user's choice
                switch (choice) {
                    case "1":
                        account.CreateAccount("employed");
                        System.out.println("1");
                        break;
                    case "2":
                        System.out.println("                 Thanks . See you next time!");
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

    private static void displayMainMenuAdmin() {
        final String CYAN = "\u001B[36m";
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String RED = "\u001B[31m";

        System.out.println("                ========================= Panel MENU ========================= \n");
        System.out.println("                                           1." + CYAN + "Add Employed" + RESET);
        System.out.println("                 ============================================================= ");
        System.out.println("                                           2." + RED + "Quit" + RESET);
        System.out.println("                 ============================================================= ");
    }
}

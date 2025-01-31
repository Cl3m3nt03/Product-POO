package view;
import Controller.Account;
import Controller.FastSorting;
import Controller.Search;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public static void setupMenu() throws IOException, ParseException {

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        Account account = new Account();
        while (!quit) {
            // Display the main menu
            drawTitle();
            displayMainMenu();
            System.out.print("                 Enter your choice: ");
            if (scanner.hasNextLine()){
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        account.CreateAccount("client");
                        break;
                    case "2":
                        account.loginAccount();
                        break;
                    case "3":

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
        scanner.close();
    }

    public static void drawTitle() {
        final String RESET = "\u001B[0m";
        final String YELLOW = "\u001B[33m";
        final String GREEN = "\u001B[32m";


        System.out.println(GREEN +
                " ________   ___  ___   ________   ________   _____ ______    ________   ________       ___    ___  \n" +
                "|\\   __  \\ |\\  \\|\\  \\ |\\   __  \\ |\\   __  \\ |\\   _ \\  _   \\ |\\   __  \\ |\\   ____\\     |\\  \\  /  /| \n" +
                "\\ \\  \\|\\  \\ \\  \\\\  \\ \\ \\  \\|\\  \\ \\ \\  \\|\\  \\ \\ \\  \\\\__\\ \\  \\ \\ \\  \\|\\  \\ \\  \\___|     \\ \\  \\/  / / \n" +
                " \\ \\   ____\\ \\   __  \\ \\ \\   __  \\ \\ \\   _  _\\ \\  \\|__| \\  \\ \\ \\   __  \\ \\  \\         \\ \\    / /  \n" +
                "  \\ \\  \\___| \\ \\  \\ \\  \\ \\ \\  \\ \\  \\ \\ \\  \\  \\|\\ \\  \\    \\ \\  \\ \\ \\  \\ \\  \\ \\  \\____     \\/  /  /   \n" +
                "   \\ \\__\\     \\ \\__\\ \\__\\ \\ \\__\\ \\__\\ \\ \\__\\ _\\ \\ \\__\\    \\ \\__\\ \\ \\__\\ \\__\\ \\_______\\ __/  / /    \n" +
                "    \\|__|      \\|__|\\|__|  \\|__|\\|__|  \\|__|\\|__| \\|__|     \\|__|  \\|__|\\|__| \\|_______||\\___/ /     \n" +
                "                                                                                     \\|___|/      \n" +
                RESET);

    }

    private static void drawSpaceship() {
        final String GREEN = "\u001B[32m";
        final String RESET = "\u001B[0m";

        System.out.println(GREEN +
                "                                ⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀  ⢀⣠⠴⠶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "                                ⠀⠀⠀⠀⠀⠀⠀⠀ ⠀ ⣠⠞⠉⠀⠀⠀ ⠀⠾⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "                                ⠀⠀⠀⠀⠀ ⣠⡤⠶⢾⠃⠀⠀⠀⠀⠀⠀⠀  ⠈⠛⠈⣷⠶⠤⣄⡀⠀⠀⠀⠀\n" +
                "                                ⠀⠀⢀⡴⠋⢡⣖⡆⢹⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⢀⡾  ⢰⣲⡌⠙⢦⡀⠀⠀\n" +
                "                                ⠀⢸⡁⠀⠀⠉⢁⡤⡝⠳⢤⣤⣄⣀⣀⣀⣤⡤⠶⢫⡤⡌⠉⠀⠀⠀   ⡷⠀⠀\n" +
                "⠀                                ⠈⠳⣄⠀⠀⠈⠛⠁ ⠀⣞⡶⠀⡴⢦⠀⢴⣳⠀⠈⠛⠁⠀⠀   ⣀⡾⠁⠀⠀\n" +
                "                                ⠀⠀⠀⠀⠈⠙⠶⢤⣄⣀⠀⠀⠀  ⠙⠋⠀⠀⠁  ⠀⣀⣀⣤⠴⠛⠁⠀⠀⠀⠀\n" +
                "⠀                                ⠀⠀⠀⠀⠀⠀⠀⠀  ⠉⠉⠛⠛⠒⠒⠒⠒⠛⠛⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" + RESET);
    }

    public static void displayMainMenu() {
        final String CYAN = "\u001B[36m";
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String RED = "\u001B[31m";

        drawSpaceship(); // Draw the spaceship
        System.out.println("                ========================= MAIN MENU ========================= \n");
        System.out.println("                                           1." + CYAN + "Sign Up" + RESET);
        System.out.println("                                           2." + GREEN + "Login" + RESET);
        System.out.println("                                           3." + RED + "Quit\n" + RESET);
        System.out.println("                 ============================================================= ");
    }

}
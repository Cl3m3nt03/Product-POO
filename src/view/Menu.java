package view;
import Controller.Search;

import java.util.Scanner;

public class Menu {
    public static void setupMenu(){

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            // Display the main menu
            drawTitle();
            displayMainMenu();
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
                        System.out.println("2");
                        break;
                    case "3":

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

    public static void drawTitle() {
        final String RESET = "\u001B[0m";
        final String YELLOW = "\u001B[33m";

        System.out.println(YELLOW + " ______  ______ ______  ______  ______       ______ ______  ______  __  ______ __  ______  __   __    \n" +
                "/\\  ___\\/\\  == /\\  __ \\/\\  ___\\/\\  ___\\     /\\  == /\\  __ \\/\\  ___\\/\\ \\/\\__  _/\\ \\/\\  __ \\/\\ \"-.\\ \\   \n" +
                "\\ \\___  \\ \\  _-\\ \\  __ \\ \\ \\___\\ \\  __\\     \\ \\  _-\\ \\ \\/\\ \\ \\___  \\ \\ \\/_/\\ \\\\ \\ \\ \\ \\/\\ \\ \\ \\-.  \\  \n" +
                " \\/\\_____\\ \\_\\  \\ \\_\\ \\_\\ \\_____\\ \\_____\\    \\ \\_\\  \\ \\_____\\/\\_____\\ \\_\\ \\ \\_\\\\ \\_\\ \\_____\\ \\_\\\\\"\\_\\ \n" +
                "  \\/_____/\\/_/   \\/_/\\/_/\\/_____/\\/_____/     \\/_/   \\/_____/\\/_____/\\/_/  \\/_/ \\/_/\\/_____/\\/_/ \\/_/ \n" +
                "                                                                                                      " + RESET);
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

    private static void displayMainMenu() {
        final String CYAN = "\u001B[36m";
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String RED = "\u001B[31m";

        drawSpaceship(); // Draw the spaceship
        System.out.println("                ========================= MAIN MENU ========================= \n");
        System.out.println("                                           1." + CYAN + "Search" + RESET);
        System.out.println("                                           2." + GREEN + "Rules" + RESET);
        System.out.println("                                           3." + RED + "Quit\n" + RESET);
        System.out.println("                 ============================================================= ");
    }

}
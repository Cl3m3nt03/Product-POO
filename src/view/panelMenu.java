package view;

import Controller.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class panelMenu {
    public void showMenu() throws IOException, ParseException {

        Scanner sc = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            System.out.print("                 Enter your choice: ");
            if (sc.hasNextLine()){
                String choice = sc.nextLine();
                // Handle the user's choice
                switch (choice) {
                    case "1":
                        System.out.println("1");
                        break;
                    case "2":
                        System.out.println("2");
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
        sc.close();
    }
}

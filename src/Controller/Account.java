package Controller;

import org.json.simple.parser.ParseException;
import view.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account {

    public static
    List<Client> users = new ArrayList<>();

    public static void CreateAccount() {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Création de Compte \n");

            System.out.printf("Enter your name :  \n");
            String name = scanner.nextLine();

            System.out.printf("Enter the password  : \n");
            String password = scanner.nextLine();

            String status = "Client";

            users.add(new Client("" + name, "" + password, "" + status));

        Menu.drawTitle();
    }

    public static void loginAccount() throws IOException, ParseException {
        System.out.print("Login Account \n");
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Enter the name for player :  \n");
        String name = scanner.nextLine();

        System.out.printf("Enter the password  : \n");
        String password = scanner.nextLine();

        if(name.equals(users.get(0).getName()) && password.equals(users.get(0).getPassword())){
            if (users.get(0).getStatus().equals("Client")){
                users.get(0).showMenu(/* Ajouter du GOUANA */);
            }else {
                System.out.print("Login Admin \n");
            }
        } else {
            System.out.println("Invalid login \n");
        }
    }
}

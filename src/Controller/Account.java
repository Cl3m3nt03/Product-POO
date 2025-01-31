package Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.parser.ParseException;
import view.Menu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account {

    public List<Client> users = new ArrayList<>();
// Create Account
    public void CreateAccount(String status) {
        boolean valid = false;
        Scanner scanner = new Scanner(System.in);
        recoveryUser();

        while (!valid) {
            System.out.println("=== Create Account ===");

            System.out.print("Enter Your Name : ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Error : Name cannot be empty");
                continue;
            }

            System.out.print("Enter Your Password : ");
            String password = scanner.nextLine().trim();

            if (password.length() < 6) {
                System.out.println("Error : Password must be at least 6 characters long");
                continue;
            }

            users.add(new Client(name, password, status));
            valid = true;
            System.out.println("Account created successfully");

            saveUser();
        }
        Menu.drawTitle();
    }
//Connect Account
    public void loginAccount() throws IOException, ParseException {
        recoveryUser();
        System.out.print("Login Account \n");
        Scanner scanner = new Scanner(System.in);
        boolean next = false;

        System.out.printf("Enter the name for player :  \n");
        String name = scanner.nextLine();

        System.out.printf("Enter the password  : \n");
        String password = scanner.nextLine();

        for (User user : users) {
            if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                System.out.println(user.getStatus());
                if (user.getStatus().equalsIgnoreCase("client")) {
                    Client client = new Client();
                    client.showMenu();
                } else if (user.getStatus().equalsIgnoreCase("employed")) {
                    Employed employed = new Employed();
                    employed.showMenu();
                } else {
                    Admin admin = new Admin();
                    admin.showMenu();
                }
                return;
            }
        }
    }
    //Function save after create user
    void saveUser() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("users.json");
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
            System.out.println("✅ Utilisateurs sauvegardés !");
        } catch (IOException e) {
            System.err.println("❌ Erreur lors de l'écriture du fichier : " + e.getMessage());
        }
    }

    //recup user before action
    private void recoveryUser() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        File file = new File("users.json");
        if (file.exists()) {
            try {
                users = objectMapper.readValue(file, new TypeReference<List<Client>>() {});
            } catch (IOException e) {
                System.err.println("❌ Erreur lors de la lecture du fichier : " + e.getMessage());
            }
        } else {
            System.out.println("❌ Le fichier d'utilisateurs n'existe pas. Aucun utilisateur chargé.");
        }
    }
}

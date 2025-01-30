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

    public void CreateAccount() {
        boolean valid = false;
        Scanner scanner = new Scanner(System.in);
        recoveryUser();

        while (!valid) {
            System.out.println("=== Création de Compte ===");

            System.out.print("Entrez votre nom : ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Erreur : Le nom ne peut pas être vide !");
                continue;
            }

            System.out.print("Entrez votre mot de passe (min. 6 caractères) : ");
            String password = scanner.nextLine().trim();

            if (password.length() < 6) {
                System.out.println("Erreur : Le mot de passe doit contenir au moins 6 caractères !");
                continue;
            }

            String status = "client";
            users.add(new Client(name, password, status));
            valid = true;
            System.out.println("Compte créé avec succès !");

            saveUser();
        }
        Menu.drawTitle();
    }

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
                } else {
                    System.out.println("Login Admin \n");
                    Admin admin = new Admin();
                    admin.showMenu();
                }
                return; // On sort de la boucle dès qu'un utilisateur est trouvé
            }
        }
    }
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

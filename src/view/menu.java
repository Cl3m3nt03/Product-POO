package view;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Scanner;
import Controller.Order;
import Controller.Pharmacy;
import org.json.*;
import java.util.ArrayList;

public class menu {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Order> commendes = new ArrayList<>();
        int choix = 0;
        int choix2;

        try {
            // Charger le fichier JSON à partir du répertoire parent
            String jsonString = new String(Files.readAllBytes(Paths.get("src/stocks_pharma.json")), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(jsonString);

            // Vérifier que "pharmacie" existe dans le JSON et qu'il s'agit d'un objet
            if (!jsonObject.has("pharmacie") || !(jsonObject.get("pharmacie") instanceof JSONObject)) {
                System.out.println("Erreur : La clé 'pharmacie' n'existe pas ou n'est pas un objet.");
                return;
            }

            // Récupérer l'objet pharmacie
            JSONObject pharmacie = jsonObject.getJSONObject("pharmacie");

            // Accéder au nom et à l'adresse de la pharmacie
            String nomPharmacie = pharmacie.getString("nom");
            String adressePharmacie = pharmacie.getString("adresse");


            Order commende = new Order("test","test");
            commendes.add(commende);

            do {
                System.out.println("=== Main Menu ===");
                System.out.println("1. Pharmacy");
                System.out.println("2. Info");
                System.out.println("3. Software Settings");
                System.out.println("0. EXIT");
                System.out.print("Please choose an option: ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    scanner.next();
                }
                choix = scanner.nextInt();

                switch (choix) {
                    case 1:
                        do {
                            System.out.println("=== Pharmacy Selection ===");
                            // Afficher le nom de la pharmacie
                            System.out.println("Pharmacy Name: " + nomPharmacie);
                            System.out.println("Address: " + adressePharmacie);




                            System.out.println("1. test commende");




                            System.out.println("0. EXIT");
                            System.out.print("Please choose a pharmacy: ");

                            while (!scanner.hasNextInt()) {
                                System.out.println("Please enter a valid number.");
                                scanner.next();
                            }
                            choix2 = scanner.nextInt();
                            switch (choix2) {
                                case 1:

                                    System.out.println();
                                    for (Order commend : commendes) {
                                        // Traiter chaque objet commende
                                        commend.displayOrderDetails(); // Affiche ou traite l'objet commende ici
                                    }
                                    System.out.println();
                                    break;
                            }
                        } while (choix2 != 0);
                        break;
                    case 2:
                        System.out.println(" ");
                        System.out.println("=== Information ===");
                        System.out.println(" ");
                        System.out.println("To connect to a pharmacy, please select the 'Pharmacy' option from the main menu.");
                        System.out.println("Then, enter the name of the pharmacy you wish to access.");
                        System.out.println("Once connected, you will be able to view the complete list of available medications and their details.");
                        System.out.println(" ");
                        break;
                    case 3:
                        do {
                            System.out.println("0. EXIT");
                            while (!scanner.hasNextInt()) {
                                System.out.println("Please enter a valid number.");
                                scanner.next();
                            }
                            choix2 = scanner.nextInt();
                            commende = new Order("test2", "test2");
                            commendes.add(commende);
                        } while (choix2 != 0);
                        break;
                }
            } while (choix != 0);
        } catch (IOException e) {
            System.out.println("Error: The file 'stocks_pharma.json' was not found.");
            e.printStackTrace();  // Afficher l'exception détaillée si le fichier est introuvable
        } catch (JSONException e) {
            System.out.println("Error: The JSON structure is incorrect.");
            e.printStackTrace();  // Afficher l'exception détaillée pour un problème avec le JSON
        }
    }
}
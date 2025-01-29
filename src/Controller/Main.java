package Controller;


import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Créer un objet Pharmacy pour gérer le stock à partir du fichier JSON
        Pharmacy pharmacy = new Pharmacy("stocks_pharma.json");

        // Scanner pour prendre les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Afficher le menu
            System.out.println("===== Menu =====");
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Supprimer un produit");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option (1/2/3): ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consommer le newline restant

            if (choice == 1) {
                // Ajouter un produit
                System.out.println("\n--- Ajouter un produit ---");

                // Demander les informations du produit à l'utilisateur
                System.out.print("Entrez l'id du produit: ");
                int id = scanner.nextInt();
                scanner.nextLine();  // Consommer le newline restant

                System.out.print("Entrez le nom du produit: ");
                String nom = scanner.nextLine();

                System.out.print("Entrez le prix du produit: ");
                double prix = scanner.nextDouble();

                System.out.print("Entrez la quantité en stock du produit: ");
                int quantiteStock = scanner.nextInt();
                scanner.nextLine();  // Consommer le newline restant

                System.out.print("Entrez la description du produit: ");
                String description = scanner.nextLine();

                // Créer un nouvel objet Product avec les informations de l'utilisateur
                Product newProduct = new Product(id, nom, prix, quantiteStock, description);

                // Demander à l'utilisateur la catégorie et la sous-catégorie
                System.out.print("Entrez la catégorie du produit: ");
                String categorie = scanner.nextLine();

                System.out.print("Entrez la sous-catégorie du produit: ");
                String sousCategorie = scanner.nextLine();

                // Ajouter le produit à la pharmacie
                pharmacy.ajouterProduit(newProduct, categorie, sousCategorie);

            } else if (choice == 2) {
                // Supprimer un produit
                System.out.println("\n--- Supprimer un produit ---");

                // Demander l'ID du produit à supprimer
                System.out.print("Entrez l'id du produit à supprimer: ");
                int productId = scanner.nextInt();
                scanner.nextLine();  // Consommer le newline restant

                // Demander la catégorie et la sous-catégorie
                System.out.print("Entrez la catégorie du produit: ");
                String categorie = scanner.nextLine();

                System.out.print("Entrez la sous-catégorie du produit: ");
                String sousCategorie = scanner.nextLine();

                // Supprimer le produit de la pharmacie
                pharmacy.supprimerProduit(productId, categorie, sousCategorie);

            } else if (choice == 3) {
                // Quitter l'application
                System.out.println("Au revoir!");
                break;
            } else {
                // Si l'utilisateur entre une option invalide
                System.out.println("Option invalide, veuillez réessayer.");
            }
        }

        scanner.close();  // Fermer le scanner
    }
}




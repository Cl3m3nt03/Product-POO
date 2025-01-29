package Controller;


import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Créer un objet Pharmacy pour gérer le stock à partir du fichier JSON
        Pharmacy pharmacy = new Pharmacy("stocks_pharma.json");

        // Scanner pour prendre les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

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

        scanner.close();  // Fermer le scanner
    }
}




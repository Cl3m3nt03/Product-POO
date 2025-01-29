package Controller;


import java.util.List;
import java.util.Scanner;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Création d'une pharmacie
        Pharmacy pharmacy = new Pharmacy("Skibidi Pharmacy", "Quelque part");

        // Liste initiale des produits
        List<Product> products = List.of(
                new Product("Amoxicilline", "Médicaments", "Antibiotiques", 5.99, 120, "Antibiotique à large spectre pour les infections bactériennes."),
                new Product("Azithromycine", "Médicaments", "Antibiotiques", 7.49, 50, "Traitement des infections respiratoires et ORL."),
                new Product("Ciprofloxacine", "Médicaments", "Antibiotiques", 8.29, 75, "Antibiotique utilisé pour les infections urinaires."),

                new Product("Paracétamol", "Médicaments", "Antalgiques", 1.99, 300, "Traitement de la douleur et de la fièvre."),
                new Product("Ibuprofène", "Médicaments", "Antalgiques", 3.49, 200, "Anti-inflammatoire non stéroïdien."),
                new Product("Tramadol", "Médicaments", "Antalgiques", 9.99, 25, "Antalgique pour les douleurs modérées à sévères."),

                new Product("Crème hydratante", "Cosmétiques", "Soins de la peau", 14.99, 80, "Crème pour hydrater les peaux sèches."),
                new Product("Gel nettoyant visage", "Cosmétiques", "Soins de la peau", 9.49, 150, "Gel doux pour nettoyer le visage en profondeur."),
                new Product("Sérum anti-âge", "Cosmétiques", "Soins de la peau", 24.99, 40, "Sérum pour réduire les signes de vieillissement."),

                new Product("Brosse à dents électrique", "Parapharmacie", "Hygiène bucco-dentaire", 39.99, 15, "Brosse à dents électrique avec plusieurs modes."),
                new Product("Dentifrice blancheur", "Parapharmacie", "Hygiène bucco-dentaire", 3.99, 250, "Dentifrice pour des dents plus blanches."),
                new Product("Bain de bouche antiseptique", "Parapharmacie", "Hygiène bucco-dentaire", 6.49, 180, "Solution pour une hygiène buccale complète."),

                new Product("Vitamine C", "Compléments alimentaires", "Vitamines", 12.99, 120, "Booste le système immunitaire et réduit la fatigue."),
                new Product("Vitamine D", "Compléments alimentaires", "Vitamines", 8.49, 90, "Améliore la santé des os et du système immunitaire."),
                new Product("Oméga-3", "Compléments alimentaires", "Vitamines", 19.99, 60, "Améliore la santé cardiovasculaire.")
        );

        // Ajouter les produits de la liste initiale à la pharmacie
        for (Product product : products) {
            pharmacy.addProduct(product);
        }

        // Scanner pour prendre l'input de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            System.out.println("\nBienvenue à la pharmacie " + pharmacy.getName() + " !");
            System.out.println("1. Afficher le stock de la pharmacie");
            System.out.println("2. Ajouter un nouveau produit");
            System.out.println("3. Supprimer un produit");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consommer la ligne restante après l'entier

            switch (choice) {
                case 1:
                    // Afficher le stock
                    pharmacy.showStock();
                    break;

                case 2:
                    System.out.println("\nEntrez les informations du produit à ajouter :");

                    System.out.print("Nom du produit : ");
                    String name = scanner.nextLine();

                    System.out.print("Catégorie : ");
                    String category = scanner.nextLine();

                    System.out.print("Sous-catégorie : ");
                    String subCategory = scanner.nextLine();

                    double price = -1;
                    while (price <= 0) {
                        System.out.print("Prix : ");
                        if (scanner.hasNextDouble()) {
                            price = scanner.nextDouble();
                            if (price <= 0) {
                                System.out.println("Le prix doit être un nombre positif. Essayez à nouveau.");
                            }
                        } else {
                            System.out.println("Veuillez entrer un prix valide (un nombre positif).");
                            scanner.next();
                        }
                    }

                    int stock = -1;
                    while (stock < 0) {
                        System.out.print("Quantité en stock : ");
                        if (scanner.hasNextInt()) {
                            stock = scanner.nextInt();
                            if (stock < 0) {
                                System.out.println("La quantité doit être un nombre entier positif.");
                            }
                        } else {
                            System.out.println("Veuillez entrer un nombre entier valide.");
                            scanner.next();
                        }
                    }
                    scanner.nextLine();

                    System.out.print("Description : ");
                    String description = scanner.nextLine();

                    Product newProduct = new Product(name, category, subCategory, price, stock, description);
                    pharmacy.addProduct(newProduct);
                    System.out.println("Le produit a été ajouté avec succès !");
                    break;

                case 3:
                    // Supprimer un produit
                    System.out.print("Entrez le nom du produit à supprimer : ");
                    String productName = scanner.nextLine();
                    pharmacy.removeProduct(productName);
                    break;

                case 4:
                    // Quitter le programme
                    continueRunning = false;
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Option invalide. Essayez à nouveau.");
                    break;
            }
        }

        scanner.close();
    }
}



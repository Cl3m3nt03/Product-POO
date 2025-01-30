package Controller;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.UUID;

public class Order {
    private String orderNumber;    // Numéro de la commande
    private String orderDate;      // Date de la commande
    private String product;        // Produit commandé
    private boolean isPriority;    // Indicateur de priorité de la commande
    private int productQuantity;   // Quantité de produit commandée

    // Constructeur avec paramètres
    public Order() {
        // Générer un numéro de commande unique
        this.orderNumber = generateOrderNumber();
        // Générer la date actuelle
        this.orderDate = LocalDate.now().toString();
        // Demander à l'utilisateur si la commande est prioritaire
        Scanner scanner = new Scanner(System.in);
        System.out.println("La commande est-elle prioritaire ? (true/false)");
        this.isPriority = scanner.nextBoolean();

        // Processus de commande
        processOrder();
    }

    // Méthode pour générer un numéro de commande unique
    private String generateOrderNumber() {
        return UUID.randomUUID().toString();
    }

    // Méthode pour afficher les détails de la commande
    private void processOrder() {
        Scanner scanner = new Scanner(System.in);
        boolean productFound = false;
        String productName = "";
        int quantityRequested = 0;

        // Charger les produits du fichier JSON
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("stocks_pharma.json")) {
            Object object = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject pharmacie = (JSONObject) jsonObject.get("pharmacie");
            JSONArray products = (JSONArray) pharmacie.get("produits");

            // Demander à l'utilisateur quel produit il souhaite acheter
            while (!productFound) {
                System.out.println("Entrez le nom du médicament que vous voulez acheter :");
                productName = scanner.nextLine();

                // Chercher le produit dans le stock
                for (Object productObj : products) {
                    JSONObject product = (JSONObject) productObj;
                    JSONArray productDetails = (JSONArray) product.get("produits");

                    for (Object productDetailObj : productDetails) {
                        JSONObject productDetail = (JSONObject) productDetailObj;
                        String productNameInStock = (String) productDetail.get("nom");

                        if (productNameInStock.equalsIgnoreCase(productName)) {
                            productFound = true;

                            // Afficher la quantité disponible du produit
                            Long stockQuantity = (Long) productDetail.get("quantiteStock");
                            System.out.println("Produit trouvé : " + productNameInStock);
                            System.out.println("Quantité en stock : " + stockQuantity);

                            // Demander la quantité voulue par l'utilisateur
                            while (quantityRequested <= 0 || quantityRequested > stockQuantity) {
                                System.out.println("Combien d'unités voulez-vous acheter ? (Quantité disponible : " + stockQuantity + ")");
                                quantityRequested = scanner.nextInt();

                                if (quantityRequested <= 0) {
                                    System.out.println("Veuillez entrer une quantité valide (plus grande que 0).");
                                } else if (quantityRequested > stockQuantity) {
                                    System.out.println("Désolé, il n'y a pas assez de stock. Quantité en stock : " + stockQuantity);
                                }
                            }

                            // Mettre à jour la quantité en stock
                            productDetail.put("quantiteStock", stockQuantity - quantityRequested);
                            System.out.println("Commande effectuée pour " + quantityRequested + " " + productNameInStock);
                            this.product = productNameInStock;
                            this.productQuantity = quantityRequested;

                            // Sauvegarder les modifications dans le fichier JSON (stocks)
                            try (FileWriter writer = new FileWriter("stocks_pharma.json")) {
                                writer.write(jsonObject.toJSONString());
                                writer.flush();
                            }

                            break; // Sortie des boucles après avoir trouvé le produit
                        }
                    }

                    if (productFound) break; // Sortie de la boucle si produit trouvé
                }

                if (!productFound) {
                    System.out.println("Le produit demandé n'est pas disponible dans le stock. Veuillez essayer à nouveau.");
                }
            }

            // Après validation de la commande, l'enregistrer dans le fichier des commandes
            saveOrderToFile();

        } catch (IOException | ParseException e) {
            System.out.println("Une erreur est survenue lors du traitement de la commande : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Méthode pour sauvegarder la commande dans le fichier 'command.json'
    private void saveOrderToFile() {
        JSONParser parser = new JSONParser();
        JSONArray commands = new JSONArray();  // Liste des commandes

        try {
            // Essayer de charger les commandes existantes depuis le fichier 'command.json'
            FileReader reader = new FileReader("command.json");
            Object obj = parser.parse(reader);
            // Vérifier si le fichier contient déjà un tableau JSON
            if (obj instanceof JSONArray) {
                commands = (JSONArray) obj;
            } else {
                System.out.println("Le fichier 'command.json' est mal formé. Un tableau JSON est attendu.");
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // Si le fichier n'existe pas, c'est acceptable. On va créer un nouveau fichier.
            System.out.println("Le fichier 'command.json' n'existe pas. Création d'un nouveau fichier.");
        } catch (IOException | ParseException e) {
            // Gérer les autres erreurs d'entrée/sortie ou d'analyse du fichier JSON
            System.out.println("Erreur lors de la lecture du fichier 'command.json': " + e.getMessage());
            e.printStackTrace();
        }

        // Créer un objet pour la commande à enregistrer
        JSONObject orderDetails = new JSONObject();
        orderDetails.put("orderNumber", orderNumber);
        orderDetails.put("orderDate", orderDate);
        orderDetails.put("product", product);
        orderDetails.put("quantity", productQuantity);
        orderDetails.put("isPriority", isPriority);

        // Ajouter la nouvelle commande à la liste des commandes
        commands.add(orderDetails);


        try (FileWriter writer = new FileWriter("command.json")) {
            writer.write(commands.toJSONString());
            writer.flush();  // S'assurer que les données sont écrites dans le fichier
            System.out.println("Commande enregistrée dans le fichier 'command.json'");
        } catch (IOException e) {
            // Gérer les erreurs lors de l'écriture dans le fichier
            System.out.println("Erreur lors de l'enregistrement de la commande : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

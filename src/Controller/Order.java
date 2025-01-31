package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class Order {
    private String orderNumber;
    private String orderDate;
    private String product;
    private boolean isPriority;
    private int productQuantity;

    // Constructor
    public Order() {
        this.orderNumber = generateOrderNumber();
        this.orderDate = LocalDate.now().toString();

        Scanner scanner = new Scanner(System.in);
        System.out.println("La commande est-elle prioritaire ? (true/false)");
        this.isPriority = scanner.nextBoolean();

        processOrder();
    }

    // Generate number command
    private String generateOrderNumber() {
        return UUID.randomUUID().toString();
    }

    // Process command
    private void processOrder() {
        Scanner scanner = new Scanner(System.in);
        boolean productFound = false;
        String productName = "";
        int quantityRequested = 0;

        JSONParser parser = new JSONParser();
        JSONObject jsonObject;

        try (FileReader reader = new FileReader("stocks_pharma.json")) {
            Object object = parser.parse(reader);
            jsonObject = (JSONObject) object;
            JSONObject pharmacie = (JSONObject) jsonObject.get("pharmacie");
            JSONArray products = (JSONArray) pharmacie.get("produits");

            while (!productFound) {
                System.out.println("Entrez le nom du médicament que vous voulez acheter :");
                productName = scanner.nextLine();

                for (Object productObj : products) {
                    JSONObject product = (JSONObject) productObj;
                    JSONArray productDetails = (JSONArray) product.get("produits");

                    for (Object productDetailObj : productDetails) {
                        JSONObject productDetail = (JSONObject) productDetailObj;
                        String productNameInStock = (String) productDetail.get("nom");

                        if (productNameInStock.equalsIgnoreCase(productName)) {
                            productFound = true;

                            Long stockQuantityLong = (Long) productDetail.get("quantiteStock");
                            int stockQuantity = stockQuantityLong.intValue();

                            System.out.println("✅ Produit trouvé : " + productNameInStock);
                            System.out.println("📦 Quantité en stock : " + stockQuantity);

                            // Request quantity with verif
                            while (true) {
                                System.out.println("Combien d'unités voulez-vous acheter ? (Disponible : " + stockQuantity + ")");
                                if (scanner.hasNextInt()) {
                                    quantityRequested = scanner.nextInt();
                                    scanner.nextLine();

                                    if (quantityRequested > 0 && quantityRequested <= stockQuantity) {
                                        break;
                                    } else {
                                        System.out.println("❌ Quantité invalide. Veuillez réessayer.");
                                    }
                                } else {
                                    System.out.println("❌ Entrée invalide. Veuillez entrer un nombre.");
                                    scanner.nextLine(); // Nettoyer l'entrée incorrecte
                                }
                            }

                            // Update values before save
                            this.product = productNameInStock;
                            this.productQuantity = quantityRequested;

                            // Update Stock and save
                            updateStock(jsonObject, productDetail, productNameInStock, stockQuantity, quantityRequested);
                            saveOrderToFile();
                            break;
                        }
                    }
                    if (productFound) break;
                }

                if (!productFound) {
                    System.out.println("❌ Le produit n'est pas disponible. Veuillez essayer un autre.");
                }
            }

        } catch (IOException | ParseException e) {
            System.out.println("❌ Une erreur est survenue : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Save command
    private void saveOrderToFile() {
        JSONParser parser = new JSONParser();
        JSONArray commands = new JSONArray();
        File file = new File("command.json");

        try {
            // Verif IF file exist and is not empty
            if (file.exists() && file.length() > 0) {
                FileReader reader = new FileReader(file);
                Object obj = parser.parse(reader);

                if (obj instanceof JSONArray) {
                    commands = (JSONArray) obj;
                } else {
                    System.out.println("⚠️ Le fichier 'command.json' est mal formé. Réinitialisation...");
                    commands = new JSONArray(); // Réinitialiser le fichier
                }
                reader.close();
            } else {
                System.out.println("📂 Création du fichier 'command.json'...");
            }
        } catch (IOException | ParseException e) {
            System.out.println("⚠️ Erreur de lecture de 'command.json', réinitialisation...");
            commands = new JSONArray(); // Réinitialiser en cas d'erreur de parsing
        }

        // Ajouter la nouvelle commande
        JSONObject orderDetails = new JSONObject();
        orderDetails.put("orderNumber", orderNumber);
        orderDetails.put("orderDate", orderDate);
        orderDetails.put("product", product);
        orderDetails.put("quantity", productQuantity);
        orderDetails.put("isPriority", isPriority);

        commands.add(orderDetails);

        try (FileWriter writer = new FileWriter(file)) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String prettyJson = objectMapper.writeValueAsString(commands);

            writer.write(prettyJson);
            writer.flush();
            System.out.println("✅ Commande enregistrée dans 'command.json' avec indentation !");
        } catch (IOException e) {
            System.out.println("❌ Erreur d'enregistrement de la commande : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Mise à jour du stock et sauvegarde
    private void updateStock(JSONObject jsonObject, JSONObject productDetail, String productNameInStock, int stockQuantity, int quantityRequested) {
        productDetail.put("quantiteStock", stockQuantity - quantityRequested);
        System.out.println("✅ Stock mis à jour pour " + quantityRequested + " unités de " + productNameInStock);

        File file = new File("stocks_pharma.json");
        try (FileWriter writer = new FileWriter(file)) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String prettyJson = objectMapper.writeValueAsString(jsonObject);

            writer.write(prettyJson);
            writer.flush();
            System.out.println("✅ Stock sauvegardé dans 'stocks_pharma.json' !");
        } catch (IOException e) {
            System.err.println("❌ Erreur de sauvegarde du stock : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

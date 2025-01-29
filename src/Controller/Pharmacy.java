package Controller;
import java.util.ArrayList;
import java.util.List;



public class Pharmacy {
    private String nom;
    private String adresse;
    private List<Product> productList = new ArrayList<>();

    public Pharmacy(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    public String getName() {
        return nom;
    }

    public void afficherDetails() {
        System.out.println("Pharmacie: " + nom);
        System.out.println("Adresse: " + adresse);
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void showStock() {
        System.out.println("Stock de la pharmacie: " + nom);

        int id = 1;

        for (Product product : productList) {
            System.out.println("\nCatégorie: " + product.getCategory());
            System.out.println("  Sous-catégorie: " + product.getSubcategory());

            System.out.println("    " + id + ". " + product.getName() + " (Prix: " + product.getPrice() + "€, Stock: " + product.getQuantity() + ")");
            System.out.println("       → " + product.getDescription());

            id++;
        }
    }


    public void removeProduct(String productName) {
        boolean found = false;

        for (Product product : productList) {
            if (product.getName().equalsIgnoreCase(productName)) {
                productList.remove(product);
                found = true;
                System.out.println("Le produit " + productName + " a été supprimé de l'inventaire.");
                break;
            }
        }

        if (!found) {
            System.out.println("Aucun produit trouvé avec le nom : " + productName);
        }
    }
}

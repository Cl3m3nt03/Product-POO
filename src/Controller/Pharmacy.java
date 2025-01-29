package Controller;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;


public abstract class Pharmacy {
    private String nom;
    private String adresse;

    public static void ReadJson(){
            JSONParser parser = new JSONParser();

            try (FileReader reader = new FileReader("stocks_pharma.json")) {
                // Read JSON file
                Object object = parser.parse(reader);
                JSONObject jsonObject = (JSONObject) object;

                // Access pharmacy
                JSONObject pharmacie = (JSONObject) jsonObject.get("pharmacie");
                String nomPharmacie = (String) pharmacie.get("nom");
                System.out.println("Nom de la pharmacie: " + nomPharmacie);

                // Access produits
                JSONArray produits = (JSONArray) pharmacie.get("produits");
                for (Object produitObj : produits) {
                    JSONObject produit = (JSONObject) produitObj;
                    String categorie = (String) produit.get("categorie");
                    String sousCategorie = (String) produit.get("sousCategorie");
                    System.out.println("\nCatégorie: " + categorie);
                    System.out.println("Sous-catégorie: " + sousCategorie);

                    JSONArray produitsDetails = (JSONArray) produit.get("produits");
                    for (Object produitDetailObj : produitsDetails) {
                        JSONObject produitDetail = (JSONObject) produitDetailObj;
                        String nomProduit = (String) produitDetail.get("nom");
                        Double prixProduit = (Double) produitDetail.get("prix");
                        Long quantiteStock = (Long) produitDetail.get("quantiteStock");
                        String description = (String) produitDetail.get("description");
                        System.out.println("Nom du produit: " + nomProduit);
                        System.out.println("Prix: " + prixProduit);
                        System.out.println("Quantité en stock: " + quantiteStock);
                        System.out.println("Description: " + description);
                        System.out.println();
                    }
                }

            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
    }

    public Pharmacy(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    public void afficherDetails() {
        System.out.println("Pharmacie: " + nom);
        System.out.println("Adresse: " + adresse);
    }

}

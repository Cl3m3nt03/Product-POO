package Controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FastSorting {

    public static void FastSorting() throws IOException, ParseException {
        //load file json
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("stocks_pharma.json");
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        // Access Product
        JSONObject pharmacie = (JSONObject) jsonObject.get("pharmacie");
        JSONArray categories = (JSONArray) pharmacie.get("produits");

        List<Produit> produits = new ArrayList<>();

        for (Object categorieObj : categories) {
            JSONObject categorie = (JSONObject) categorieObj;
            JSONArray produitsCategorie = (JSONArray) categorie.get("produits");
            for (Object produitObj : produitsCategorie) {
                JSONObject produit = (JSONObject) produitObj;
                String nom = (String) produit.get("nom");
                int quantiteStock = ((Long) produit.get("quantiteStock")).intValue();
                // Ajouter les produits dans la liste
                produits.add(new Produit(nom, quantiteStock));
            }
        }

        quickSort(produits, 0, produits.size() - 1);

        for (Produit produit : produits) {
            if( produit.getQuantiteStock() < 5 ){
                System.out.println(produit.getNom() + " - Quantité en stock: " + produit.getQuantiteStock() + " Attention");
            } else{
                System.out.println(produit.getNom() + " - Quantité en stock: " + produit.getQuantiteStock());
            }
        }
    }

    public static void quickSort(List<Produit> produits, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(produits, begin, end);
            quickSort(produits, begin, partitionIndex - 1);
            quickSort(produits, partitionIndex + 1, end);
        }
    }

    private static int partition(List<Produit> produits, int begin, int end) {
        int pivot = produits.get(end).getQuantiteStock();
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (produits.get(j).getQuantiteStock() <= pivot) {
                i++;
                // Échanger les éléments
                Produit temp = produits.get(i);
                produits.set(i, produits.get(j));
                produits.set(j, temp);
            }
        }
        Produit temp = produits.get(i + 1);
        produits.set(i + 1, produits.get(end));
        produits.set(end, temp);
        return i + 1;
    }

    static class Produit {
        private String nom;
        private int quantiteStock;

        public Produit(String nom, int quantiteStock) {
            this.nom = nom;
            this.quantiteStock = quantiteStock;
        }

        public String getNom() {
            return nom;
        }

        public int getQuantiteStock() {
            return quantiteStock;
        }
    }
}

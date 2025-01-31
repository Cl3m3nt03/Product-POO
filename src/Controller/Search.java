package Controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Search extends Product {

    public Search(String category, String sub_category, String name, double price, int quantity, String description) {
        super(category, sub_category, name, price, quantity, description);
    }

    public static void searchProduct() {
        ArrayList<Product> product = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        product.add(new Product("Médicaments","Antibiotiques","Amoxicilline", 5.99, 120,"Antibiotique à large spectre pour les infections bactériennes."));
        product.add(new Product("Médicaments","Antibiotiques","Azithromycine", 7.49, 50,"Traitement des infections respiratoires et ORL."));
        product.add(new Product("Médicaments","Antibiotiques","Ciprofloxacine", 8.29, 75,"Antibiotique utilisé pour les infections urinaires."));
        product.add(new Product("Médicaments","Antalgiques","Paracetamol", 1.99, 300,"Traitement de la douleur et de la fièvre."));
        product.add(new Product("Médicaments","Antalgiques","Ibuprofene", 3.49, 200,"Anti-inflammatoire non stéroïdien."));
        product.add(new Product("Médicaments","Antalgiques","Tramadol", 9.99, 25,"Antalgique pour les douleurs modérées à sévères."));
        product.add(new Product("Cosmétiques","Soins de la peau","Creme hydratante", 14.99, 80,"Crème pour hydrater les peaux sèches."));
        product.add(new Product("Cosmétiques","Soins de la peau","Gel nettoyant visage", 9.49, 150,"Gel doux pour nettoyer le visage en profondeur."));
        product.add(new Product("Cosmétiques","Soins de la peau","Serum anti-age", 24.99, 40,"Sérum pour réduire les signes de vieillissement."));
        product.add(new Product("Parapharmacie","Hygiène bucco-dentaire","Brosse a dents électrique", 39.99, 15,"Brosse à dents électrique avec plusieurs modes."));
        product.add(new Product("Parapharmacie","Hygiène bucco-dentaire","Dentifrice blancheur", 3.99, 250, "Dentifrice pour des dents plus blanches."));
        product.add(new Product("Parapharmacie","Hygiène bucco-dentaire","Bain de bouche antiseptique", 6.49, 180,"Solution pour une hygiène buccale complète."));
        product.add(new Product("Compléments alimentaires","Vitamines","Vitamine C", 12.99, 120,"Booste le système immunitaire et réduit la fatigue."));
        product.add(new Product("Compléments alimentaires","Vitamines","Vitamine D", 8.49, 90,"Améliore la santé des os et du système immunitaire."));
        product.add(new Product("Compléments alimentaires","Vitamines","Omega-3", 19.99, 60,"Améliore la santé cardiovasculaire."));

        Collections.sort(product);

        boolean continueSearch = true;

        do {
            System.out.print("Enter the product name: ");
            String target = scanner.nextLine().trim();
            String answer;

            int index = binarySearch(product, target);

            if (index != -1) {
                System.out.println("Product found : " + product.get(index).getName() + " | Price : " + product.get(index).getPrice() + " | Quantity available : " + product.get(index).getQuantity() + " | Description : " + product.get(index).getDescription());
            } else {
                System.out.println("The product has not been found or does not exist!");
            }

            do {
                System.out.println("Would you like to search for another product (yes/no)?");
                answer = scanner.nextLine().trim().toLowerCase();

                if (!answer.equals("yes") && !answer.equals("no")) {
                    System.out.println("Please enter yes or no!");
                }
            } while (!answer.equals("yes") && !answer.equals("no"));

            if (answer.equals("no")) {
                continueSearch = false;
            }

        } while (continueSearch);
    }
// Function search Product
    public static int binarySearch(ArrayList<Product> products, String target) {
        int left = 0, right = products.size() - 1;
        target = target.toLowerCase();

        while (left <= right) { // Start loop
            int mid = left + (right - left) / 2;
            String midName = products.get(mid).getName().toLowerCase(); //research in middle

            if (midName.equals(target)) {
                return mid;
            } else if (midName.compareTo(target) < 0) {
                left = mid + 1; //midName before research
            } else {
                right = mid - 1; // midName after research
            }
        }
        return -1; //product no find
    }
}

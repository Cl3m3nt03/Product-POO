import Controller.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main extends Product {

    public Main(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    public static void main(String[] args) {

        ArrayList<Product> product = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        product.add(new Product("Amoxicilline", 5.99, 120));
        product.add(new Product("Azithromycine", 7.49, 50));
        product.add(new Product("Ciprofloxacine", 8.29, 75));
        product.add(new Product("Paracetamol", 1.99, 300));
        product.add(new Product("Ibuprofene", 3.49, 200));
        product.add(new Product("Tramadol", 9.99, 25));
        product.add(new Product("Creme hydratante", 14.99, 80));
        product.add(new Product("Gel nettoyant visage", 9.49, 150));
        product.add(new Product("Serum anti-age", 24.99, 40));
        product.add(new Product("Brosse a dents électrique", 39.99, 15));
        product.add(new Product("Dentifrice blancheur", 3.99, 250));
        product.add(new Product("Bain de bouche antiseptique", 6.49, 180));
        product.add(new Product("Vitamine C", 12.99, 120));
        product.add(new Product("Vitamine D", 8.49, 90));
        product.add(new Product("Omega-3", 19.99, 60));

        Collections.sort(product);

        System.out.println("\nWelcome to Skibidi Pharmacy !");
        System.out.println("=============================");

        // Product search
        boolean continueSearch = true;

        do {
            System.out.print("Enter the product name: ");
            String target = scanner.nextLine().trim();
            String answer;

            int index = binarySearch(product, target);

            if (index != -1) {
                System.out.println("Product found: " + product.get(index).getName() + " | Price: " + product.get(index).getPrice() + " | Quantity available: " + product.get(index).getQuantity());
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

        scanner.close();
    }

        //Binary search algorithm
        public static int binarySearch(ArrayList<Product> products, String target) {
            int left = 0, right = products.size() - 1;
            target = target.toLowerCase();

            while (left <= right) {
                int mid = left + (right - left) / 2;
                String midName = products.get(mid).getName().toLowerCase();

                if (midName.equals(target)) {
                    return mid;
                } else if (midName.compareTo(target) < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;//No product found
        }
    }


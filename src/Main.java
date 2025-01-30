import Controller.Search;
import view.Menu;
import Controller.Pharmacy;
import Controller.Order;

public class Main {
    public static void main(String[] args) {

        //test commende
        System.out.println("test");

        Pharmacy.ReadJson();

        System.out.println("");
        System.out.println("---------------------------commande---------------------------------");
        System.out.println("");

        Order order = new Order();

        System.out.println("");
        System.out.println("---------------------------commande---------------------------------");
        System.out.println("");

        Pharmacy.ReadJson();

        System.out.println("fin test");






        Menu.setupMenu();
    }
}


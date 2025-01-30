import Controller.*;
import org.json.simple.parser.ParseException;
import view.Menu;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        Pharmacy pharmacy = new Pharmacy("src\\stocks_pharma.json");
        pharmacy.serializeJson("C:\\Users\\theol\\OneDrive\\Bureau\\stock_pharmacy.cer");

        Menu.setupMenu();
        FastSorting.FastSorting();
    }
}
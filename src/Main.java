import Controller.Account;
import Controller.Pharmacy;
import org.json.simple.parser.ParseException;
import view.Menu;
import Controller.FastSorting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        Pharmacy pharmacy = new Pharmacy("src\\stocks_pharma.json");
        pharmacy.serializeJson("C:\\Users\\theol\\OneDrive\\Bureau\\stock_pharmacy.cer");


        Menu.setupMenu();
        FastSorting.FastSorting();
    }
}


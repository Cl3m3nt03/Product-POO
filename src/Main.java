import Controller.Pharmacy;
import Controller.Product;
import org.json.simple.parser.ParseException;
import view.Menu;
import Controller.FastSorting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Menu.setupMenu();
        FastSorting.FastSorting();
    }
}


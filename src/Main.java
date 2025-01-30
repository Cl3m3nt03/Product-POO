import Controller.Account;
import Controller.Statistics;
import org.json.simple.parser.ParseException;
import view.Menu;
import static Controller.Statistics.saveStatisticsToFile;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        Menu.setupMenu();
    }
}


package Controller;

import Model.Rules;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Admin extends User implements Rules {

    public Admin(String name, String password, String status) {
        super(name, password, status);
    }

    @Override
    public void showMenu() throws IOException, ParseException {

    }

    @Override
    public void Get_Rules() {

    }
}

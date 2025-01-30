package Controller;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public abstract class User {
    private String name;
    private String password;
    private String status;
    private int userID;

    public abstract void showMenu() throws IOException, ParseException;

    public User(String name, String password, String status) {
        this.name = name;
        this.password = password;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

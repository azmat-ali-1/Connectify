package SpringProject.SpringProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;
    private Password Password;

    private Status connect;
    private HashMap<String,User> connection;

    public HashMap<String, User> getConnection() {
        return connection;
    }
    public List<String> inbox = new ArrayList<>();

    public void setConnection(HashMap<String, User> connection) {
        this.connection = connection;
    }

    public User(int id, String name, String email, SpringProject.SpringProject.Password password, HashMap<String, User> connection) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.Password = password;
        this.connection = connection;
    }

    public Status getConnect() {
        return connect;
    }

    public void setConnect(Status connect) {
        this.connect = connect;
    }

    public User() {
    }

    public User(int id, String name, String email, SpringProject.SpringProject.Password password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.Password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SpringProject.SpringProject.Password getPassword() {
        return Password;
    }

    public void setPassword(SpringProject.SpringProject.Password password) {
        Password = password;
    }

    public List<String> getInbox() {
        return inbox;
    }

    public void setInbox(List<String> inbox) {
        this.inbox = inbox;
    }

}

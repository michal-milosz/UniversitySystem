package app;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class UserProperty {
    private IntegerProperty id;
    private StringProperty username;
    private StringProperty password;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty email;

    public UserProperty(int id, String username, String password, String firstName, String lastName, String email) {
        this.id = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
    }

    public UserProperty(User user) {
        this.id = new SimpleIntegerProperty(user.getId());
        this.username = new SimpleStringProperty(user.getUsername());
        this.password = new SimpleStringProperty(user.getPassword());
        this.firstName = new SimpleStringProperty(user.getFirstName());
        this.lastName = new SimpleStringProperty(user.getLastName());
        this.email = new SimpleStringProperty(user.getEmail());
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * Make the list of UserProperty objects corresponding to the given list of User objects.
     * @param userList The list of User objects
     * @return The list of UserProperty objects corresponding to the given list of User objects
     */
    public static List<UserProperty> makeUserPropertyList(List<User> userList) {
        List<UserProperty> userPropertyList = new ArrayList<>();
        for (User user : userList) {
            userPropertyList.add(new UserProperty(user));
        }
        return userPropertyList;
    }
}

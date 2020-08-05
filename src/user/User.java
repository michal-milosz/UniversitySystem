package user;

public class User {
    protected UserGroup userGroup = UserGroup.UNDEFINED;
    protected int id;
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String email;

    public User() {}

    public User(UserGroup userGroup, int id, String username, String password,
                String firstName, String lastName, String email) {
        this.userGroup = userGroup;
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}

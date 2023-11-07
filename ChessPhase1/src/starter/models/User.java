package models;

/**
 * Represents a user in the application.
 */
public class User {

    /** The username of the user. */
    private String username;

    /** The password of the user. */
    private String password;

    /** The email of the user. */
    private String email;

    /**
     * Constructs a new User with default values.
     */
    public User() {
        username = "";
        password = "";
        email = "";
    }

    /**
     * Constructs a new User with specified username, password, and email.
     *
     * @param userName The username of the user.
     * @param passWord The password of the user.
     * @param eMail The email of the user.
     */
    public User(String userName, String passWord, String eMail) {
        username = userName;
        password = passWord;
        email = eMail;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package requests;

/**
 * Represents a request to log in with a username and password.
 */
public class LoginRequest {

    /** The username for login. */
    private String username;

    /** The password for login. */
    private String password;

    /**
     * Constructs a new LoginRequest with default values.
     */
    public LoginRequest() {
        username = "";
        password = "";
    }

    /**
     * Gets the username for login.
     *
     * @return The username for login.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for login.
     *
     * @param username The username for login to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password for login.
     *
     * @return The password for login.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for login.
     *
     * @param password The password for login to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

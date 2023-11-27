package requests;

/**
 * Represents a request to register a new user.
 */
public class RegisterRequest {

    /** The username for registration. */
    private String username;

    /** The password for registration. */
    private String password;

    /** The email for registration. */
    private String email;

    /**
     * Constructs a new RegisterRequest with default values.
     */
    public RegisterRequest() {}

    /**
     * Gets the username for registration.
     *
     * @return The username for registration.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for registration.
     *
     * @param username The username for registration to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password for registration.
     *
     * @return The password for registration.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for registration.
     *
     * @param password The password for registration to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email for registration.
     *
     * @return The email for registration.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email for registration.
     *
     * @param email The email for registration to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}


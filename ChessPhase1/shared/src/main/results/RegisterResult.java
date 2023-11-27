package results;

/**
 * Represents the result of a registration request.
 */
public class RegisterResult {

    /** The username of the registered user. */
    private String username;

    /** The authentication token for the registered user. */
    private String authToken;

    /** The message indicating the result of the registration request. */
    private String message;

    /**
     * Constructs a new RegisterResult with default values.
     */
    public RegisterResult() {
        username = "";
        authToken = "";
        message = null;
    }

    /**
     * Gets the username of the registered user.
     *
     * @return The username of the registered user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the registered user.
     *
     * @param username The username of the registered user to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the authentication token for the registered user.
     *
     * @return The authentication token for the registered user.
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Sets the authentication token for the registered user.
     *
     * @param authToken The authentication token for the registered user to set.
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * Gets the message indicating the result of the registration request.
     *
     * @return The message indicating the result of the registration request.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message indicating the result of the registration request.
     *
     * @param message The message indicating the result of the registration request to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
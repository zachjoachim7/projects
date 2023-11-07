package results;

/**
 * Represents the result of a login request.
 */
public class LoginResult {

    /** The username of the logged-in user. */
    private String username;

    /** The authentication token for the logged-in user. */
    private String authToken;

    /** The message indicating the result of the login request. */
    private String message;

    /**
     * Constructs a new LoginResult with default values.
     */
    public LoginResult() {
        username = "";
        authToken = "";
        message = null;
    }

    public LoginResult(String error_message) {
        message = error_message;
    }

    /**
     * Gets the username of the logged-in user.
     *
     * @return The username of the logged-in user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the logged-in user.
     *
     * @param username The username of the logged-in user to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the authentication token for the logged-in user.
     *
     * @return The authentication token for the logged-in user.
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Sets the authentication token for the logged-in user.
     *
     * @param authToken The authentication token for the logged-in user to set.
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * Gets the message indicating the result of the login request.
     *
     * @return The message indicating the result of the login request.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message indicating the result of the login request.
     *
     * @param message The message indicating the result of the login request to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

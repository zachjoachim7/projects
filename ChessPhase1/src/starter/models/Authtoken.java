package models;

/**
 * Represents an authentication token associated with a username.
 */
public class Authtoken {

    /** The authentication token. */
    private String authToken;

    /** The associated username. */
    private String username;

    /**
     * Constructs a new Authtoken with default values.
     */
    public Authtoken() {
        authToken = "";
        username = "";
    }

    /**
     * Constructs a new Authtoken with specified token and username.
     *
     * @param token The authentication token.
     * @param userName The associated username.
     */
    public Authtoken(String token, String userName) {
        authToken = token;
        username = userName;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

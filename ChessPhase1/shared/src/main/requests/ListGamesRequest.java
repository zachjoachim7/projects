package requests;

import models.Authtoken;

/**
 * Represents a request to list available games.
 */
public class ListGamesRequest {

    /** The authentication token for the player. */
    private Authtoken authToken;

    /**
     * Constructs a new ListGamesRequest with default values.
     */
    public ListGamesRequest() {
        authToken = new Authtoken();
    }

    /**
     * Gets the authentication token.
     *
     * @return The authentication token.
     */
    public Authtoken getAuthToken() {
        return authToken;
    }

    /**
     * Sets the authentication token.
     *
     * @param authToken The authentication token to set.
     */
    public void setAuthToken(Authtoken authToken) {
        this.authToken = authToken;
    }
}

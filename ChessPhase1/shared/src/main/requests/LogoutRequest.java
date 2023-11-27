package requests;

import models.Authtoken;

/**
 * Represents a request to log out using an authentication token.
 */
public class LogoutRequest {

    /** The authentication token used for logout. */
    private Authtoken authToken;

    /**
     * Constructs a new LogoutRequest with default values.
     */
    public LogoutRequest() {
        authToken = new Authtoken();
    }

    /**
     * Gets the authentication token for logout.
     *
     * @return The authentication token for logout.
     */
    public Authtoken getAuthToken() {
        return authToken;
    }

    /**
     * Sets the authentication token for logout.
     *
     * @param authToken The authentication token for logout to set.
     */
    public void setAuthToken(Authtoken authToken) {
        this.authToken = authToken;
    }
}

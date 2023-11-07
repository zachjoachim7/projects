package requests;


/**
 * Represents a request to create a new game.
 */
public class CreateGameRequest {

    /** The authentication token for the player. */
    private String authToken;

    /** The name of the game to be created. */
    private String gameName;

    /**
     * Constructs a new CreateGameRequest with default values.
     */
    public CreateGameRequest() {
        authToken = null;
        gameName = null;
    }

    /**
     * Gets the authentication token.
     *
     * @return The authentication token.
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Sets the authentication token.
     *
     * @param authToken The authentication token to set.
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * Gets the name of the game to be created.
     *
     * @return The name of the game.
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * Sets the name of the game to be created.
     *
     * @param gameName The name of the game to set.
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}


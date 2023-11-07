package results;

/**
 * Represents the result of a request to create a new game.
 */
public class CreateGameResult {

    /** The ID of the newly created game. */
    private Integer gameID;

    /** The message indicating the result of the create game request. */
    private String message;

    /**
     * Constructs a new CreateGameResult with default values.
     */
    public CreateGameResult() {
        gameID = null;
        message = null;
    }

    /**
     * Gets the ID of the newly created game.
     *
     * @return The ID of the newly created game.
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * Sets the ID of the newly created game.
     *
     * @param gameID The ID of the newly created game to set.
     */
    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    /**
     * Gets the message indicating the result of the create game request.
     *
     * @return The message indicating the result of the create game request.
     */
    public String getMessage() {
        if (this.message == null) {
            return null;
        }
        return message;
    }

    /**
     * Sets the message indicating the result of the create game request.
     *
     * @param message The message indicating the result of the create game request to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

package results;

/**
 * Represents the result of a request to join a game.
 */
public class JoinGameResult {

    /** The message indicating the result of the join game request. */
    private String message;

    /**
     * Constructs a new JoinGameResult with default values.
     */
    public JoinGameResult() {
        message = null;
    }

    /**
     * Gets the message indicating the result of the join game request.
     *
     * @return The message indicating the result of the join game request.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message indicating the result of the join game request.
     *
     * @param message The message indicating the result of the join game request to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

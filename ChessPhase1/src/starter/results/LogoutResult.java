package results;

/**
 * Represents the result of a logout request.
 */
public class LogoutResult {

    /** The message indicating the result of the logout request. */
    private String message;

    /**
     * Constructs a new LogoutResult with default values.
     */
    public LogoutResult() {
        message = "";
    }

    /**
     * Gets the message indicating the result of the logout request.
     *
     * @return The message indicating the result of the logout request.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message indicating the result of the logout request.
     *
     * @param message The message indicating the result of the logout request to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}


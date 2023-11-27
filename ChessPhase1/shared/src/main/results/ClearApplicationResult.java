package results;

/**
 * Represents the result of a request to clear the application data.
 */
public class ClearApplicationResult {

    /** The message indicating the result of the clear application request. */
    private String message;

    /**
     * Constructs a new ClearApplicationResult with default values.
     */
    public ClearApplicationResult() {
        message = "";
    }

    /**
     * Gets the message indicating the result of the clear application request.
     *
     * @return The message indicating the result of the clear application request.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message indicating the result of the clear application request.
     *
     * @param message The message indicating the result of the clear application request to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

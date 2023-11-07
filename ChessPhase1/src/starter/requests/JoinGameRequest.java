package requests;

import chess.ChessGame;

/**
 * Represents a request to join a game.
 */
public class JoinGameRequest {

    /** The authentication token for the player. */
    private String authToken;

    /** The color chosen by the player. */
    private ChessGame.TeamColor playerColor;

    /** The ID of the game to join. */
    private int gameID;

    /**
     * Constructs a new JoinGameRequest with default values.
     */
    public JoinGameRequest() {
        authToken = "";
        playerColor = null;
        gameID = 0;
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
     * Gets the player's chosen color.
     *
     * @return The player's chosen color.
     */
    public ChessGame.TeamColor getPlayerColor() {
        return playerColor;
    }

    /**
     * Sets the player's chosen color.
     *
     * @param playerColor The player's chosen color to set.
     */
    public void setPlayerColor(ChessGame.TeamColor playerColor) {
        this.playerColor = playerColor;
    }

    /**
     * Gets the ID of the game to join.
     *
     * @return The game ID.
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * Sets the ID of the game to join.
     *
     * @param gameID The game ID to set.
     */
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}

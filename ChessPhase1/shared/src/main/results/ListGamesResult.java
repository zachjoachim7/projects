package results;

import java.util.List;
import java.util.ArrayList;
import models.Game;

/**
 * Represents the result of a request to list available games.
 */
public class ListGamesResult {

    /** The list of ChessGame objects representing available games. */
    private List<Game> games;

    /** The ID of the game. */
    private int gameID;

    /** The name of the game. */
    private String gameName;

    /** The message indicating the result of the list games request. */
    private String message;

    /**
     * Constructs a new ListGamesResult with default values.
     */
    public ListGamesResult() {
        games = new ArrayList<>();
        gameID = 0;
        gameName = "";
        message = "";
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

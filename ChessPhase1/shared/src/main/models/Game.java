package models;

import chess.MyChessGame;

/**
 * Represents a game in the application.
 */
public class Game {

    /** The ID of the game. */
    private int gameID;

    /** The username of the white player. */
    private String whiteUsername;

    /** The username of the black player. */
    private String blackUsername;

    /** The name of the game. */
    private String gameName;

    /** The ChessGame object representing the game. */
    private MyChessGame game;

    /**
     * Constructs a new Game with default values and a MyChessGame.
     */
    public Game() {
        gameID = 0;
        whiteUsername = null;
        blackUsername = null;
        gameName = null;
        game = new MyChessGame();
    }

    public String getWhiteUsername() {
        return whiteUsername;
    }

    public void setWhiteUsername(String whiteUsername) {
        this.whiteUsername = whiteUsername;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public MyChessGame getGame() {
        return game;
    }

    public void setGame(MyChessGame game) {
        this.game = game;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getBlackUsername() {
        return blackUsername;
    }

    public void setBlackUsername(String blackUsername) {
        this.blackUsername = blackUsername;
    }
}

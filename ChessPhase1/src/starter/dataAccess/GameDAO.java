package dataAccess;

import chess.ChessGame;
import chess.MyChessGame;
import com.google.gson.Gson;
import models.Game;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.sql.*;

/**
 * Data Access Object (DAO) for managing ChessGame entities in the database.
 */
public class GameDAO {

    private static final List<Game> games = new ArrayList<>();

    public void CreateGameSQL(Connection conn, String whiteUsername, String blackUsername, String gameName) throws DataAccessException {

        String gameID = createGameID();
        try {
            var createStatement = conn.prepareStatement("INSERT INTO chess.games (gameID, gameName, whiteUsername, blackUsername, ChessGame) VALUES (?, ?, ?, ?, ?)");
            createStatement.setInt(1, Integer.parseInt(gameID));
            createStatement.setString(2, gameName);
            createStatement.setString(3, whiteUsername);
            createStatement.setString(4, blackUsername);
            createStatement.setString(5, new Gson().toJson(new MyChessGame()));
            createStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error: bad request");
        }
    }

    public List<Game> getGames() {
        return games;
    }

    private String createGameID() {

        Random random = new Random();
        StringBuilder gameID = new StringBuilder();
        for (int i = 0; i < 4; i ++) {
            int dig = random.nextInt(10);
            gameID.append(dig);
        }
        return gameID.toString();
    }

    public Game FindGameSQL(Connection conn, String gameName) throws DataAccessException {

        Game game = new Game();
        try {
            var findStatement = conn.prepareStatement("SELECT * FROM chess.games WHERE gameName = ?");
            findStatement.setString(1, gameName);
            try (var result = findStatement.executeQuery()) {
                if (result.next()) {
                    game.setGameID(result.getInt("gameID"));
                    game.setGameName(result.getString("gameName"));
                    game.setWhiteUsername(result.getString("WhiteUsername"));
                    game.setBlackUsername(result.getString("blackUsername"));
                    game.setGame(new Gson().fromJson(result.getString("ChessGame"), MyChessGame.class));
                }
                else {
                    throw new DataAccessException("Error: bad request");
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return game;
    }

    public Game FindGamebyIDSQL(Connection conn, int id) throws DataAccessException {

        Game game = new Game();
        try {
            var findStatement = conn.prepareStatement("SELECT * FROM chess.games WHERE gameID = ?");
            findStatement.setInt(1, id);
            try (var result = findStatement.executeQuery()) {
                if (result.next()) {
                    game.setGameID(result.getInt("gameID"));
                    game.setGameName(result.getString("gameName"));
                    game.setWhiteUsername(result.getString("WhiteUsername"));
                    game.setBlackUsername(result.getString("blackUsername"));
                    game.setGame(new Gson().fromJson(result.getString("ChessGame"), MyChessGame.class));
                }
                else {
                    throw new DataAccessException("Error: bad request");
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return game;

    }

    public void ClaimSpotSQL(Connection conn, String gameName, ChessGame.TeamColor color, String username) throws DataAccessException {

        try {
            if (color == ChessGame.TeamColor.BLACK) {
                var claimBlackStatement = conn.prepareStatement("UPDATE chess.games SET blackUsername = ? WHERE gameName = ?");
                claimBlackStatement.setString(1, username);
                claimBlackStatement.setString(2, gameName);
                claimBlackStatement.executeUpdate();
            } else if (color == ChessGame.TeamColor.WHITE){
                var claimWhiteStatement = conn.prepareStatement("UPDATE chess.games SET whiteUsername = ? WHERE gameName = ?");
                claimWhiteStatement.setString(1, username);
                claimWhiteStatement.setString(2, gameName);
                claimWhiteStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }


    public void DeleteGameSQL(Connection conn, int gameID) throws DataAccessException {

        try {
            var deleteStatement = conn.prepareStatement("DELETE FROM chess.games WHERE gameID = ?");
            deleteStatement.setInt(1, gameID);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Clears the object
     */
    public void clear(Connection conn) throws DataAccessException {
        try {
            var clearStatement = conn.prepareStatement("DELETE FROM chess.games");
            clearStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}

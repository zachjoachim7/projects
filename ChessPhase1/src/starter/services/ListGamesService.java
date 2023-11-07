package services;

import chess.MyChessGame;
import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.GameDAO;
import requests.ListGamesRequest;
import results.ListGamesResult;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import models.Game;
import java.util.List;


/**
 * Service for handling a request to list available games.
 */
public class ListGamesService {

    /**
     * The ListGamesRequest object associated with this service.
     */
    private ListGamesRequest request;

    /**
     * Constructs a new ListGamesService with a null request.
     */
    public ListGamesService() {
        request = null;
    }

    /**
     * Constructs a new ListGamesService with the specified request.
     *
     * @param request The ListGamesRequest associated with this service.
     */
    public ListGamesService(ListGamesRequest request) {
        this.request = request;
    }

    /**
     * Gets the ListGamesRequest associated with this service.
     *
     * @return The ListGamesRequest associated with this service.
     */
    public ListGamesRequest getRequest() {
        return request;
    }

    /**
     * Sets the ListGamesRequest associated with this service.
     *
     * @param request The ListGamesRequest to set for this service.
     */
    public void setRequest(ListGamesRequest request) {
        this.request = request;
    }

    public ListGamesResult listGames(String token) {

        ListGamesResult result = new ListGamesResult();
        List<Game> games = new ArrayList<>();
        AuthDAO accessor = new AuthDAO();
        try (Connection conn = Database.getInstance().getConnection()) {
            if (accessor.FindAuthTokenSQL(conn, token) != null) {
                var queryStatement = conn.prepareStatement("SELECT * FROM chess.games");
                var queryResult = queryStatement.executeQuery();
                while (queryResult.next()) {
                    Game game = new Game();
                    game.setGameID(queryResult.getInt(1));
                    game.setGameName(queryResult.getString("gameName"));
                    game.setWhiteUsername(queryResult.getString("WhiteUsername"));
                    game.setBlackUsername(queryResult.getString("blackUsername"));
                    game.setGame(new Gson().fromJson(queryResult.getString("ChessGame"), MyChessGame.class));
                    games.add(game);
                }
                result.setGames(games);
                return result;
            }
        } catch (DataAccessException e) {
            result.setMessage(e.getMessage());
        } catch (SQLException e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }
}

package services;

import chess.ChessGame;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.GameDAO;
import models.Authtoken;
import models.Game;
import requests.JoinGameRequest;
import results.JoinGameResult;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Service for handling a request to join a game.
 */
public class JoinGameService {

    /** The JoinGameRequest object associated with this service. */
    private JoinGameRequest request;

    /**
     * Constructs a new JoinGameService with a null request.
     */
    public JoinGameService() {
        request = null;
    }

    /**
     * Constructs a new JoinGameService with the specified request.
     *
     * @param request The JoinGameRequest associated with this service.
     */
    public JoinGameService(JoinGameRequest request) {
        this.request = request;
    }

    /**
     * Gets the JoinGameRequest associated with this service.
     *
     * @return The JoinGameRequest associated with this service.
     */
    public JoinGameRequest getRequest() {
        return request;
    }

    /**
     * Sets the JoinGameRequest associated with this service.
     *
     * @param request The JoinGameRequest to set for this service.
     */
    public void setRequest(JoinGameRequest request) {
        this.request = request;
    }

    public JoinGameResult joinGame(JoinGameRequest request) {

        JoinGameResult result = new JoinGameResult();
        GameDAO game_accessor = new GameDAO();
        Game game;
        Authtoken token;
        try (Connection conn = Database.getInstance().getConnection()){
            AuthDAO auth_accessor = new AuthDAO();
            token = auth_accessor.FindAuthTokenSQL(conn, request.getAuthToken());
            game = game_accessor.FindGamebyIDSQL(conn, request.getGameID());
            if (game.getBlackUsername() != null && request.getPlayerColor() == ChessGame.TeamColor.BLACK) {
                result.setMessage("Error: already taken");
                return result;
            }
            else if (game.getWhiteUsername() != null && request.getPlayerColor() == ChessGame.TeamColor.WHITE) {
                result.setMessage("Error: already taken");
                return result;
            }
            game_accessor.ClaimSpotSQL(conn, game.getGameName(), request.getPlayerColor(), token.getUsername());

        } catch (DataAccessException e) {
            result.setMessage(e.getMessage());
        } catch (SQLException e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }
}

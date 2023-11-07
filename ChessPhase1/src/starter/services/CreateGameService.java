package services;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.GameDAO;
import requests.CreateGameRequest;
import results.CreateGameResult;
import models.Game;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Service for handling a request to create a new game.
 */
public class CreateGameService {

    /** The CreateGameRequest object associated with this service. */
    private CreateGameRequest request;

    /**
     * Constructs a new CreateGameService with a null request.
     */
    public CreateGameService() {
        request = null;
    }

    /**
     * Constructs a new CreateGameService with the specified request.
     *
     * @param request The CreateGameRequest associated with this service.
     */
    public CreateGameService(CreateGameRequest request) {
        this.request = request;
    }

    /**
     * Gets the CreateGameRequest associated with this service.
     *
     * @return The CreateGameRequest associated with this service.
     */
    public CreateGameRequest getRequest() {
        return request;
    }

    /**
     * Sets the CreateGameRequest associated with this service.
     *
     * @param request The CreateGameRequest to set for this service.
     */
    public void setRequest(CreateGameRequest request) {
        this.request = request;
    }

    public CreateGameResult createGame(CreateGameRequest request) {

        CreateGameResult result = new CreateGameResult();
        AuthDAO auth_accessor = new AuthDAO();
        GameDAO game_accessor = new GameDAO();
        try (Connection conn = Database.getInstance().getConnection()) {

            if (auth_accessor.FindAuthTokenSQL(conn, request.getAuthToken()) != null) {
                game_accessor.CreateGameSQL(conn, "", "", request.getGameName());
            }

            Game game = game_accessor.FindGameSQL(conn, request.getGameName());
            result.setGameID(game.getGameID());
            return result;
        }
        catch (DataAccessException e) {
            result.setMessage(e.getMessage());
            result.setGameID(null);
        } catch (SQLException e) {
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setMessage("Error: bad request");
            result.setGameID(null);
        }
        return result;
    }
}

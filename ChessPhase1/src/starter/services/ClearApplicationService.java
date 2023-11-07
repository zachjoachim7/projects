package services;

import dataAccess.*;
import requests.ClearApplicationRequest;
import results.ClearApplicationResult;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Service for handling a request to clear the application data.
 */
public class ClearApplicationService {

    /** The ClearApplicationRequest object associated with this service. */
    private ClearApplicationRequest request;

    /**
     * Constructs a new ClearApplicationService with a null request.
     */
    public ClearApplicationService() {
        request = null;
    }

    /**
     * Constructs a new ClearApplicationService with the specified request.
     *
     * @param request The ClearApplicationRequest associated with this service.
     */
    public ClearApplicationService(ClearApplicationRequest request) {
        this.request = request;
    }

    /**
     * Gets the ClearApplicationRequest associated with this service.
     *
     * @return The ClearApplicationRequest associated with this service.
     */
    public ClearApplicationRequest getRequest() {
        return request;
    }

    /**
     * Sets the ClearApplicationRequest associated with this service.
     *
     * @param request The ClearApplicationRequest to set for this service.
     */
    public void setRequest(ClearApplicationRequest request) {
        this.request = request;
    }
    public ClearApplicationResult clearApplication(ClearApplicationRequest request) throws DataAccessException {

        ClearApplicationResult result = new ClearApplicationResult();
        GameDAO game_accessor = new GameDAO();
        AuthDAO auth_accessor = new AuthDAO();
        UserDAO user_accessor = new UserDAO();
        try (Connection conn = Database.getInstance().getConnection()) {
            game_accessor.clear(conn);
            auth_accessor.clear(conn);
            user_accessor.clear(conn);
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

        return result;

    }
}

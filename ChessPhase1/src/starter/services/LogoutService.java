package services;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.Database;
import requests.LogoutRequest;
import results.LogoutResult;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Service for handling a logout request.
 */
public class LogoutService {

    /** The LogoutRequest object associated with this service. */
    private LogoutRequest request;

    /**
     * Constructs a new LogoutService with a null request.
     */
    public LogoutService() {
        request = null;
    }

    /**
     * Constructs a new LogoutService with the specified request.
     *
     * @param request The LogoutRequest associated with this service.
     */
    public LogoutService(LogoutRequest request) {
        this.request = request;
    }

    /**
     * Gets the LogoutRequest associated with this service.
     *
     * @return The LogoutRequest associated with this service.
     */
    public LogoutRequest getRequest() {
        return request;
    }

    /**
     * Sets the LogoutRequest associated with this service.
     *
     * @param request The LogoutRequest to set for this service.
     */
    public void setRequest(LogoutRequest request) {
        this.request = request;
    }

    public LogoutResult logout(String token) {

        LogoutResult result = new LogoutResult();
        AuthDAO accessor = new AuthDAO();

        try (Connection conn = Database.getInstance().getConnection()) {
            accessor.DeleteAuthTokenSQL(conn, token);
        } catch(DataAccessException e) {
            result.setMessage(e.getMessage());
            return result;
        } catch (SQLException e) {
            result.setMessage("Error: unauthorized");
            return result;
        }
        result.setMessage(null);

        return result;

    }
}

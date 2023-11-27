package services;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.UserDAO;
import requests.LoginRequest;
import results.LoginResult;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Service for handling a login request.
 */
public class LoginService {

    /** The LoginRequest object associated with this service. */
    private LoginRequest request;

    /**
     * Constructs a new LoginService with a null request.
     */
    public LoginService() {
        request = null;
    }

    /**
     * Constructs a new LoginService with the specified request.
     *
     * @param request The LoginRequest associated with this service.
     */
    public LoginService(LoginRequest request) {
        this.request = request;
    }

    /**
     * Processes the login request and returns the result.
     *
     * @return The result of the login request (LoginResult).
     */
    public LoginResult login(LoginRequest request) {

        LoginResult result = new LoginResult();
        UserDAO accessor_user = new UserDAO();
        AuthDAO accessor_auth = new AuthDAO();

        try (Connection conn = Database.getInstance().getConnection()) {
            result.setUsername(accessor_user.FindUserSQL(conn, request.getUsername(), request.getPassword()));
            result.setAuthToken(accessor_auth.CreateAuthTokenSQL(conn, request.getUsername()));
        } catch (DataAccessException e) {
            result.setMessage(e.getMessage());
            result.setAuthToken(null);
            result.setUsername(null);
            return result;
        } catch (SQLException e) {
            result.setMessage(e.getMessage());
        }

        return result;
    }

    /**
     * Gets the LoginRequest associated with this service.
     *
     * @return The LoginRequest associated with this service.
     */
    public LoginRequest getRequest() {
        return request;
    }

    /**
     * Sets the LoginRequest associated with this service.
     *
     * @param request The LoginRequest to set for this service.
     */
    public void setRequest(LoginRequest request) {
        this.request = request;
    }

}

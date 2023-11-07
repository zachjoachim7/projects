package services;

import dataAccess.DataAccessException;
import requests.RegisterRequest;
import results.RegisterResult;
import dataAccess.UserDAO;
import dataAccess.AuthDAO;
import dataAccess.Database;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Service for handling a registration request.
 */
public class RegisterService {

    /** The RegisterRequest object associated with this service. */
    private RegisterRequest request;

    /**
     * Constructs a new RegisterService with a null request.
     */
    public RegisterService() {
        request = null;
    }

    /**
     * Constructs a new RegisterService with the specified request.
     *
     * @param request The RegisterRequest associated with this service.
     */
    public RegisterService(RegisterRequest request) {
        this.request = request;
    }

    /**
     * Gets the RegisterRequest associated with this service.
     *
     * @return The RegisterRequest associated with this service.
     */
    public RegisterRequest getRequest() {
        return request;
    }

    /**
     * Sets the RegisterRequest associated with this service.
     *
     * @param request The RegisterRequest to set for this service.
     */
    public void setRequest(RegisterRequest request) {
        this.request = request;
    }

    public RegisterResult register(RegisterRequest request) {

        UserDAO accessor = new UserDAO();
        AuthDAO accessor2 = new AuthDAO();
        RegisterResult result = new RegisterResult();
        try (Connection conn = Database.getInstance().getConnection()){
            accessor.CreateUserSQL(conn, request.getUsername(), request.getPassword(), request.getEmail());
            result.setUsername(request.getUsername());
            result.setAuthToken(accessor2.CreateAuthTokenSQL(conn, request.getUsername()));

        } catch (SQLException e) {
            result.setMessage(e.getMessage());
        }
        catch (DataAccessException e) {
            result.setMessage(e.getMessage());
            result.setAuthToken(null);
            result.setUsername(null);
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.setAuthToken(null);
            result.setUsername(null);
        }
        return result;
    }
}

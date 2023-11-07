package dataAccess;

import models.Authtoken;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

/**
 * Data Access Object (DAO) for managing authentication tokens in the database.
 */
public class AuthDAO {

    private static final List<Authtoken> authTokens = new ArrayList<>();

    public String CreateAuthTokenSQL(Connection conn, String username) throws DataAccessException {

        String tokenString = UUID.randomUUID().toString();
        try {
            var createStatement = conn.prepareStatement("INSERT INTO chess.authtokens (authToken, username) VALUES(?, ?)");
            createStatement.setString(1, tokenString);
            createStatement.setString(2, username);
            createStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return tokenString;
    }

    public Authtoken FindAuthTokenSQL(Connection conn, String tokenString) throws DataAccessException {

        Authtoken token = new Authtoken();
        try {
            var findStatement = conn.prepareStatement("SELECT * FROM chess.authtokens WHERE authToken = ?");
            findStatement.setString(1, tokenString);
            try (var result = findStatement.executeQuery()) {
                if (result.next()) {
                    token.setAuthToken(result.getString("authToken"));
                    token.setUsername(result.getString("username"));
                }
                else {
                    throw new DataAccessException("Error: unauthorized");
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return token;
    }

    public List<Authtoken> getAuthTokens() {
        return authTokens;
    }

    public void DeleteAuthTokenSQL(Connection conn, String token) throws DataAccessException {

        try {
            FindAuthTokenSQL(conn, token);
        } catch (DataAccessException e) {
            throw new DataAccessException(e.getMessage());
        }
        try {
            var deleteStatement = conn.prepareStatement("DELETE FROM chess.authtokens WHERE authToken = ?");
            deleteStatement.setString(1, token);
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
            var clearStatement = conn.prepareStatement("DELETE FROM chess.authtokens");
            clearStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}

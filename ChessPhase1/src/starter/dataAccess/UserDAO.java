package dataAccess;

import java.util.List;
import java.util.ArrayList;
import models.User;
import java.sql.*;

/**
 * Data Access Object (DAO) for managing User entities in the database.
 */
public class UserDAO {

    private final static List<User> Users = new ArrayList<>();

    public List<User> getUsers() {
        return Users;
    }

    public void CreateUserSQL(Connection conn, String username, String password, String email) throws DataAccessException {

        if (username == null || password == null || email == null) {
            throw new IllegalArgumentException("Error: bad request");
        }
        try {
            var findStatement = conn.prepareStatement("SELECT username FROM chess.users WHERE username = ?");
            findStatement.setString(1, username);
            try (var result = findStatement.executeQuery()) {
                if (result.next()) {
                    throw new DataAccessException("Error: already taken");
                }
                else {
                    var createStatement = conn.prepareStatement("INSERT INTO chess.users (username, password, email) VALUES (?, ?, ?)");
                    createStatement.setString(1, username);
                    createStatement.setString(2, password);
                    createStatement.setString(3, email);
                    createStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public String FindUserSQL(Connection conn, String username, String password) throws DataAccessException {

        if (username == null || password == null) {
            throw new IllegalArgumentException("Error: bad request");
        }
        User user = new User();
        try {
            var findStatement = conn.prepareStatement("SELECT * FROM chess.users WHERE username = ? AND password = ?");
            findStatement.setString(1, username);
            findStatement.setString(2, password);
            try (var result = findStatement.executeQuery()) {
                if (result.next()) {
                    user.setUsername(result.getString("username"));
                    user.setPassword(result.getString("password"));
                    user.setEmail((result.getString("email")));
                }
                else {
                    throw new DataAccessException("Error: unauthorized");
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return user.getUsername();
    }

    public void DeleteUserSQL(Connection conn, User u) throws DataAccessException {

        try {
            var deleteStatement = conn.prepareStatement("DELETE FROM chess.users WHERE username = ? AND password = ? AND email = ?");
            deleteStatement.setString(1, u.getUsername());
            deleteStatement.setString(2, u.getPassword());
            deleteStatement.setString(3, u.getEmail());
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
            var clearStatement = conn.prepareStatement("DELETE FROM chess.users");
            clearStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}

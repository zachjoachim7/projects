package passoffTests;

import chess.ChessGame;
import dataAccess.DataAccessException;
import models.Authtoken;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import requests.*;
import results.*;
import services.*;
import dataAccess.*;
import java.sql.Connection;
import java.sql.SQLException;

public class MyTests {

    @Test
    public void positiveRegisterTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            UserDAO user_accessor = new UserDAO();
            user_accessor.clear(conn);
            RegisterRequest register_req = new RegisterRequest();
            register_req.setUsername("zachjoachim");
            register_req.setPassword("Hatters123");
            register_req.setEmail("yuh.com");
            RegisterService register_service = new RegisterService();
            RegisterResult register_res = register_service.register(register_req);
            Assertions.assertEquals("zachjoachim", register_res.getUsername());
            Assertions.assertNull(register_res.getMessage());
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }

    @Test
    public void negativeRegisterTest() throws DataAccessException {


        RegisterRequest register_req = new RegisterRequest();
        register_req.setUsername("zachjoachim");
        register_req.setPassword("Hatters123");
        register_req.setEmail("yuh.com");
        RegisterService register_service = new RegisterService();
        RegisterResult register_res = register_service.register(register_req);
        RegisterRequest register_req2 = new RegisterRequest();
        register_req.setUsername("zachjoachim");
        register_req.setPassword("Hatters123");
        register_req.setEmail("yuh.com");
        RegisterService register_service2 = new RegisterService();
        RegisterResult register_res2 = register_service2.register(register_req2);

        Assertions.assertNotEquals(register_res2, register_res);

    }

    @Test
    public void positiveLoginTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            UserDAO user_accessor = new UserDAO();
            user_accessor.clear(conn);
            AuthDAO auth_accessor = new AuthDAO();
            auth_accessor.clear(conn);
            user_accessor.CreateUserSQL(conn, "username", "password", "email");
            auth_accessor.CreateAuthTokenSQL(conn, "username");
            LoginRequest login_request = new LoginRequest();
            login_request.setUsername("username");
            login_request.setPassword("password");
            LoginService login_service = new LoginService();
            LoginResult login_result = login_service.login(login_request);
            Assertions.assertEquals(login_result.getUsername(), "username");
            Assertions.assertNull(login_result.getMessage());
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }

    @Test
    public void negativeLoginTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            UserDAO user_accessor = new UserDAO();
            user_accessor.clear(conn);
            AuthDAO auth_accessor = new AuthDAO();
            auth_accessor.clear(conn);
            user_accessor.CreateUserSQL(conn, "username", "password", "email");
            auth_accessor.CreateAuthTokenSQL(conn, "username");
            LoginRequest login_request = new LoginRequest();
            login_request.setUsername("usernamEr");
            login_request.setPassword("passworDer");
            LoginService login_service = new LoginService();
            LoginResult login_result = login_service.login(login_request);
            Assertions.assertEquals(login_result.getMessage(), "Error: unauthorized");
            Assertions.assertNull(login_result.getAuthToken());
            Assertions.assertNull(login_result.getUsername());
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Test
    public void positiveLogoutTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            UserDAO user_accessor = new UserDAO();
            user_accessor.clear(conn);
            AuthDAO auth_accessor = new AuthDAO();
            auth_accessor.clear(conn);
            user_accessor.CreateUserSQL(conn, "zachjoachim", "password", "yuh.com");
            LogoutRequest request = new LogoutRequest();
            Authtoken token = new Authtoken();
            token.setUsername("zachjoachim");
            token.setAuthToken(auth_accessor.CreateAuthTokenSQL(conn, "zachjoachim"));
            request.setAuthToken(token);
            LogoutService service = new LogoutService();
            LogoutResult result = service.logout(request.getAuthToken().getAuthToken());
            Assertions.assertNull(result.getMessage());
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }

    @Test
    public void negativeLogoutTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            UserDAO user_accessor = new UserDAO();
            user_accessor.clear(conn);
            AuthDAO auth_accessor = new AuthDAO();
            auth_accessor.clear(conn);
            user_accessor.CreateUserSQL(conn, "zachjoachim", "password", "yuh.com");
            auth_accessor.CreateAuthTokenSQL(conn, "zachjoachim");
            LogoutRequest request = new LogoutRequest();
            LogoutService service = new LogoutService();
            LogoutResult result = service.logout(request.getAuthToken().getAuthToken());
            Assertions.assertEquals("Error: unauthorized", result.getMessage());
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }

    @Test
    public void positiveListGamesTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            AuthDAO auth_accessor = new AuthDAO();
            auth_accessor.clear(conn);
            GameDAO game_accessor = new GameDAO();
            game_accessor.clear(conn);
            UserDAO user_accessor = new UserDAO();
            user_accessor.clear(conn);
            game_accessor.CreateGameSQL(conn, "whiterules", "blackrules", "game1");
            game_accessor.CreateGameSQL(conn, "blacksucks", "whitesucks", "game2");
            user_accessor.CreateUserSQL(conn, "zachjoachim", "password", "yuh.com");
            ListGamesRequest request = new ListGamesRequest();
            Authtoken token = new Authtoken();
            token.setUsername("zachjoachim");
            token.setAuthToken(auth_accessor.CreateAuthTokenSQL(conn, "zachjoachim"));
            request.setAuthToken(token);
            ListGamesService service = new ListGamesService();
            ListGamesResult result = service.listGames(request.getAuthToken().getAuthToken());
            Assertions.assertEquals(2, result.getGames().size());
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }

    @Test
    public void negativeListGamesTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            AuthDAO auth_accessor = new AuthDAO();
            auth_accessor.clear(conn);
            GameDAO game_accessor = new GameDAO();
            game_accessor.clear(conn);
            UserDAO user_accessor = new UserDAO();
            user_accessor.clear(conn);
            game_accessor.CreateGameSQL(conn, "whiterules", "blackrules", "game1");
            game_accessor.CreateGameSQL(conn, "blacksucks", "whitesucks", "game2");
            user_accessor.CreateUserSQL(conn, "zachjoachim", "password", "yuh.com");
            auth_accessor.CreateAuthTokenSQL(conn, "zachjoachim");
            ListGamesRequest request = new ListGamesRequest();
            ListGamesService service = new ListGamesService();
            ListGamesResult result = service.listGames(request.getAuthToken().getAuthToken());
            Assertions.assertEquals("Error: unauthorized", result.getMessage());
            Assertions.assertEquals(0, result.getGames().size());
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }

    @Test
    public void positiveJoinGameTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            AuthDAO auth_accessor = new AuthDAO();
            auth_accessor.clear(conn);
            GameDAO game_accessor = new GameDAO();
            game_accessor.clear(conn);
            UserDAO user_accessor = new UserDAO();
            user_accessor.clear(conn);
            game_accessor.CreateGameSQL(conn, null, "blackrules", "game1");
            user_accessor.CreateUserSQL(conn, "zachjoachim", "password", "yuh.com");
            JoinGameRequest request = new JoinGameRequest();
            request.setPlayerColor(ChessGame.TeamColor.WHITE);
            Authtoken token = new Authtoken();
            token.setUsername("zachjoachim");
            token.setAuthToken(auth_accessor.CreateAuthTokenSQL(conn, "zachjoachim"));
            request.setGameID(game_accessor.FindGameSQL(conn, "game1").getGameID());
            request.setAuthToken(token.getAuthToken());
            JoinGameService service = new JoinGameService();
            JoinGameResult result = service.joinGame(request);
            Assertions.assertNull(result.getMessage());
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }

    @Test
    public void negativeJoinGameTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            AuthDAO auth_accessor = new AuthDAO();
            auth_accessor.clear(conn);
            GameDAO game_accessor = new GameDAO();
            game_accessor.clear(conn);
            UserDAO user_accessor = new UserDAO();
            user_accessor.clear(conn);
            game_accessor.CreateGameSQL(conn, "whiterules", "blackrules", "game1");
            user_accessor.CreateUserSQL(conn, "zachjoachim", "password", "yuh.com");
            JoinGameRequest request = new JoinGameRequest();
            request.setPlayerColor(ChessGame.TeamColor.WHITE);
            Authtoken token = new Authtoken();
            token.setUsername("zachjoachim");
            token.setAuthToken(auth_accessor.CreateAuthTokenSQL(conn, "zachjoachim"));
            request.setGameID(game_accessor.FindGameSQL(conn, "game1").getGameID());
            request.setAuthToken(token.getAuthToken());
            JoinGameService service = new JoinGameService();
            JoinGameResult result = service.joinGame(request);
            Assertions.assertEquals("Error: already taken", result.getMessage());
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }

    @Test
    public void positiveCreateGameTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            AuthDAO auth_accessor = new AuthDAO();
            auth_accessor.clear(conn);
            GameDAO game_accessor = new GameDAO();
            game_accessor.clear(conn);
            UserDAO user_accessor = new UserDAO();
            user_accessor.clear(conn);
            user_accessor.CreateUserSQL(conn, "zachjoachim", "password", "yuh.com");
            auth_accessor.CreateAuthTokenSQL(conn, "zachjoachim");
            CreateGameRequest request = new CreateGameRequest();
            Authtoken token = new Authtoken();
            token.setUsername("zachjoachim");
            token.setAuthToken(auth_accessor.CreateAuthTokenSQL(conn, "zachjoachim"));
            request.setAuthToken(token.getAuthToken());
            request.setGameName("game1");
            CreateGameService service = new CreateGameService();
            CreateGameResult result = service.createGame(request);
            Assertions.assertNull(result.getMessage());
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }

    @Test
    public void negativeCreateGameTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            AuthDAO auth_accessor = new AuthDAO();
            auth_accessor.clear(conn);
            GameDAO game_accessor = new GameDAO();
            game_accessor.clear(conn);
            UserDAO user_accessor = new UserDAO();
            user_accessor.clear(conn);
            user_accessor.CreateUserSQL(conn, "zachjoachim", "password", "yuh.com");
            CreateGameRequest request = new CreateGameRequest();
            Authtoken token = new Authtoken();
            token.setUsername("zachjoachim");
            token.setAuthToken(auth_accessor.CreateAuthTokenSQL(conn, "zachjoachim"));
            request.setAuthToken(token.getAuthToken());
            CreateGameService service = new CreateGameService();
            CreateGameResult result = service.createGame(request);
            Assertions.assertNotNull(result.getMessage());
            Assertions.assertEquals("Error: bad request", result.getMessage());
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }

    @Test
    public void positiveClearApplicationTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            GameDAO game_accessor = new GameDAO();
            game_accessor.clear(conn);
            AuthDAO auth_accessor = new AuthDAO();
            auth_accessor.clear(conn);
            UserDAO user_accessor = new UserDAO();
            user_accessor.clear(conn);
            user_accessor.CreateUserSQL(conn, "username", "password", "email");
            user_accessor.CreateUserSQL(conn, "usernamE1", "passworD", "email");
            user_accessor.CreateUserSQL(conn, "usernaME2", "passworD2", "email");
            auth_accessor.CreateAuthTokenSQL(conn, "username");
            auth_accessor.CreateAuthTokenSQL(conn, "usernamE1");
            auth_accessor.CreateAuthTokenSQL(conn, "usernaME2");
            game_accessor.CreateGameSQL(conn, "", "", "game1");
            game_accessor.CreateGameSQL(conn, "", "", "game2");
            game_accessor.CreateGameSQL(conn, "", "", "game3");
            game_accessor.clear(conn);
            auth_accessor.clear(conn);
            user_accessor.clear(conn);
            Assertions.assertEquals(0, game_accessor.getGames().size());
            Assertions.assertEquals(0, auth_accessor.getAuthTokens().size());
            Assertions.assertEquals(0, user_accessor.getUsers().size());
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }

    @Test
    public void positiveCreateUserSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            UserDAO userAccessor = new UserDAO();
            userAccessor.clear(conn);
            userAccessor.CreateUserSQL(conn, "zachjoachim", "password", "yuh.com");
            Assertions.assertEquals("zachjoachim", userAccessor.FindUserSQL(conn, "zachjoachim", "password"));
        } catch (SQLException | DataAccessException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    @Test
    public void negativeCreateUserSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            UserDAO userAccessor = new UserDAO();
            userAccessor.clear(conn);
            userAccessor.CreateUserSQL(conn, "zachjoachim", "password", "yuh.com");
            Assertions.assertThrows(DataAccessException.class, () -> {
                userAccessor.CreateUserSQL(conn, "zachjoachim", "password", "yuh.com");
            });

        } catch (SQLException | DataAccessException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
    @Test
    public void positiveFindUserSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            UserDAO userAccessor = new UserDAO();
            userAccessor.clear(conn);
            userAccessor.CreateUserSQL(conn, "zachjoachim", "password", "yuh.com");
            Assertions.assertEquals("zachjoachim", userAccessor.FindUserSQL(conn, "zachjoachim", "password"));
        } catch (SQLException | DataAccessException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    @Test
    public void negativeFindUserSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            UserDAO userAccessor = new UserDAO();
            userAccessor.clear(conn);
            userAccessor.CreateUserSQL(conn, "zachjoachim", "password", "yuh.com");
            Assertions.assertThrows(DataAccessException.class, () -> {
                userAccessor.FindUserSQL(conn, "zachjoachim17", "password_yuh");
            });

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
    @Test
    public void positiveDeleteUserSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            UserDAO userAccessor = new UserDAO();
            userAccessor.clear(conn);
            userAccessor.CreateUserSQL(conn, "zachjoachim", "password", "yuh.com");
            userAccessor.DeleteUserSQL(conn, new User("zachjoachim", "password", "yuh.com"));
            var confirmStatement = conn.prepareStatement("SELECT COUNT(*) FROM chess.users");
            try (var result = confirmStatement.executeQuery()) {
                if (result.next()) {
                    Assertions.assertEquals(0, result.getInt(1));
                } else {
                    Assertions.fail("Did not delete user");
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    @Test
    public void negativeDeleteUserSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            UserDAO userAccessor = new UserDAO();
            userAccessor.clear(conn);
            userAccessor.CreateUserSQL(conn, "zachjoachim", "password", "yuh.com");
            userAccessor.DeleteUserSQL(conn, new User("zachjoachim17", "password_yuh", "email.com"));
            var queryStatement = conn.prepareStatement("SELECT COUNT(*) FROM chess.users");
            try (var result = queryStatement.executeQuery()) {
                if (result.next()) {
                    Assertions.assertEquals(1, result.getInt(1));
                } else {
                    Assertions.fail("Deleted user");
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    @Test
    public void positiveClearUsersSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {

            UserDAO user_accessor = new UserDAO();
            user_accessor.clear(conn);
            user_accessor.CreateUserSQL(conn, "username", "password", "email");
            user_accessor.CreateUserSQL(conn, "usernamE1", "passworD", "email");
            user_accessor.CreateUserSQL(conn, "usernaME2", "passworD2", "email");
            var getSizeStatement = conn.prepareStatement("SELECT COUNT(*) FROM chess.users");
            try (var result = getSizeStatement.executeQuery()) {
                if (result.next()) {
                    Assertions.assertEquals(3, result.getInt(1));
                } else {
                    Assertions.fail("Did not clear users table");
                }
            } catch (SQLException e) {
                throw new DataAccessException(e.getMessage());
            }
            user_accessor.clear(conn);
            var getSizeAfterDelete = conn.prepareStatement("SELECT COUNT(*) FROM chess.users");
            try (var result = getSizeAfterDelete.executeQuery();) {
                if (result.next()) {
                    Assertions.assertEquals(0, result.getInt(1));
                } else {
                    Assertions.fail("Did not clear users table");
                }
            } catch (SQLException e) {
                throw new DataAccessException(e.getMessage());
            }

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    @Test
    public void positiveCreateGameSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            GameDAO gameAccessor = new GameDAO();
            gameAccessor.clear(conn);
            gameAccessor.CreateGameSQL(conn, "", "", "gameName");
            var queryStatement = conn.prepareStatement("SELECT COUNT(*) FROM chess.games");
            try (var result = queryStatement.executeQuery()) {
                if (result.next()) {
                    Assertions.assertEquals(1, result.getInt(1));
                } else {
                    Assertions.fail("Did not create game");
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    @Test
    public void negativeCreateGameSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            GameDAO gameAccessor = new GameDAO();
            gameAccessor.clear(conn);
            Assertions.assertThrows(DataAccessException.class, () -> {
                gameAccessor.CreateGameSQL(conn, null, null, null);
            });
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    @Test
    public void positiveFindGameSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            GameDAO gameAccessor = new GameDAO();
            gameAccessor.clear(conn);
            gameAccessor.CreateGameSQL(conn, "", "", "game1");
            gameAccessor.CreateGameSQL(conn, "", "", "game2");
            gameAccessor.CreateGameSQL(conn, "", "", "game3");
            Assertions.assertNotNull(gameAccessor.FindGameSQL(conn, "game1"));

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
    @Test
    public void negativeFindGameSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            GameDAO gameAccessor = new GameDAO();
            gameAccessor.clear(conn);
            gameAccessor.CreateGameSQL(conn, "", "", "gameName1");
            gameAccessor.CreateGameSQL(conn, "", "", "gameName2");
            gameAccessor.CreateGameSQL(conn, "", "", "gameName3");
            Assertions.assertThrows(DataAccessException.class, () -> {
                gameAccessor.FindGameSQL(conn, "random_game");
            });

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
    @Test
    public void positiveFindGamebyIDSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            GameDAO gameAccessor = new GameDAO();
            gameAccessor.clear(conn);
            gameAccessor.CreateGameSQL(conn, "", "", "game1");
            Assertions.assertNotNull(gameAccessor.FindGamebyIDSQL(conn, gameAccessor.FindGameSQL(conn, "game1").getGameID()));

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
    @Test
    public void negativeFindGamebyIDSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            GameDAO gameAccessor = new GameDAO();
            gameAccessor.clear(conn);
            gameAccessor.CreateGameSQL(conn, "", "", "gameName1");
            Assertions.assertThrows(DataAccessException.class, () -> {
                gameAccessor.FindGamebyIDSQL(conn, 11);
            });

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
    @Test
    public void positiveDeleteGameSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            GameDAO gameAccessor = new GameDAO();
            gameAccessor.clear(conn);
            gameAccessor.CreateGameSQL(conn, null, null, "game1");
            gameAccessor.DeleteGameSQL(conn, gameAccessor.FindGameSQL(conn, "game1").getGameID());
            var checkZeroSize = conn.prepareStatement("SELECT COUNT(*) FROM chess.games");
            try(var result = checkZeroSize.executeQuery()) {
                if (result.next()) {
                    Assertions.assertEquals(0, result.getInt(1));
                } else {
                    Assertions.fail("Did not delete game");
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    @Test
    public void negativeDeleteGameSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            GameDAO gameAccessor = new GameDAO();
            gameAccessor.clear(conn);
            gameAccessor.CreateGameSQL(conn, "", "", "game1");
            gameAccessor.DeleteGameSQL(conn, 11);
            var queryStatement = conn.prepareStatement("SELECT COUNT(*) FROM chess.games");
            try (var result = queryStatement.executeQuery()) {
                if (result.next()) {
                    Assertions.assertEquals(1, result.getInt(1));
                } else {
                    Assertions.fail("Deleted game");
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    @Test
    public void positiveClearGamesSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            GameDAO gameAccessor = new GameDAO();
            gameAccessor.clear(conn);
            gameAccessor.CreateGameSQL(conn, "", "", "game1");
            gameAccessor.CreateGameSQL(conn, "", "", "game2");
            gameAccessor.CreateGameSQL(conn, "", "", "game3");
            var checkSizeStatement = conn.prepareStatement("SELECT COUNT(*) FROM chess.games");
            try (var result = checkSizeStatement.executeQuery()) {
                if (result.next()) {
                    int count = result.getInt(1);
                    Assertions.assertEquals(3, count);
                } else {
                    Assertions.fail("No rows found in the ResultSet");
                }
            }
            gameAccessor.clear(conn);
            var checkZeroSize = conn.prepareStatement("SELECT COUNT(*) FROM chess.games");
            try (var result = checkZeroSize.executeQuery()) {
                if (result.next()) {
                    int count = result.getInt(1);
                    Assertions.assertEquals(0, count);
                } else {
                    Assertions.fail("Still rows left");
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    @Test
    public void positiveCreateAuthtokenSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            AuthDAO authAccessor = new AuthDAO();
            authAccessor.clear(conn);
            authAccessor.CreateAuthTokenSQL(conn, "zachjoachim");
            var checkSize = conn.prepareStatement("SELECT COUNT(*) FROM chess.authtokens");
            try (var result = checkSize.executeQuery()) {
                if (result.next()) {
                    Assertions.assertEquals(1, result.getInt(1));
                } else {
                    Assertions.fail("Did not create token");
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    @Test
    public void negativeCreateAuthtokenSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            AuthDAO authAccessor = new AuthDAO();
            authAccessor.clear(conn);
            Assertions.assertThrows(DataAccessException.class, () -> {
                authAccessor.CreateAuthTokenSQL(conn, null);
            });

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    @Test
    public void positiveFindAuthtokenSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            AuthDAO authAccessor = new AuthDAO();
            authAccessor.clear(conn);
            Assertions.assertEquals("user1", authAccessor.FindAuthTokenSQL(conn, authAccessor.CreateAuthTokenSQL(conn, "user1")).getUsername());
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    @Test
    public void negativeFindAuthtokenSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            AuthDAO authAccessor = new AuthDAO();
            authAccessor.clear(conn);
            authAccessor.CreateAuthTokenSQL(conn, "user1");
            authAccessor.CreateAuthTokenSQL(conn, "user2");
            authAccessor.CreateAuthTokenSQL(conn, "user3");
            Assertions.assertThrows(DataAccessException.class, () -> {
                authAccessor.FindAuthTokenSQL(conn, "yuh_string");
            });
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());

        }

    }
    @Test
    public void positiveDeleteAuthtokenSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            AuthDAO authAccessor = new AuthDAO();
            authAccessor.clear(conn);
            String token = authAccessor.CreateAuthTokenSQL(conn, "zachjoachim");
            authAccessor.CreateAuthTokenSQL(conn, "user1");
            authAccessor.CreateAuthTokenSQL(conn, "user2");
            authAccessor.DeleteAuthTokenSQL(conn, token);
            var checkZeroSizeStatement = conn.prepareStatement("SELECT COUNT(*) FROM chess.authtokens");
            try (var result = checkZeroSizeStatement.executeQuery()) {
                if (result.next()) {
                    int count = result.getInt(1);
                    Assertions.assertEquals(2, count);
                } else {
                    Assertions.fail("Wrong, didn't return 2");
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    @Test
    public void negativeDeleteAuthtokenSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            AuthDAO authAccessor = new AuthDAO();
            authAccessor.clear(conn);
            authAccessor.CreateAuthTokenSQL(conn, "zachjoachim");
            Assertions.assertThrows(DataAccessException.class, () -> {
                authAccessor.DeleteAuthTokenSQL(conn, "1234");
            });
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    @Test
    public void positiveClearAuthtokenSQLTest() throws DataAccessException {

        try (Connection conn = Database.getInstance().getConnection()) {
            AuthDAO authAccessor = new AuthDAO();
            authAccessor.clear(conn);
            authAccessor.CreateAuthTokenSQL(conn, "username1");
            authAccessor.CreateAuthTokenSQL(conn, "username2");
            authAccessor.CreateAuthTokenSQL(conn, "username3");
            authAccessor.CreateAuthTokenSQL(conn, "username4");
            var checkSizeStatement = conn.prepareStatement("SELECT COUNT(*) FROM chess.authtokens");
            try (var result = checkSizeStatement.executeQuery()) {
                if (result.next()) {
                    int count = result.getInt(1);
                    Assertions.assertEquals(4, count);
                } else {
                    Assertions.fail("No rows found in the ResultSet");
                }
            }
            authAccessor.clear(conn);
            var checkZeroSize = conn.prepareStatement("SELECT COUNT(*) FROM chess.authtokens");
            try (var result = checkZeroSize.executeQuery()) {
                if (result.next()) {
                    int count = result.getInt(1);
                    Assertions.assertEquals(0, count);
                } else {
                    Assertions.fail("Still rows left");
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
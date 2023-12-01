import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.ServerFacade;
import java.sql.Connection;
import java.sql.SQLException;
import chess.ChessGame;
import models.Authtoken;
import models.User;
import requests.*;
import results.*;


public class ServerFacadeTests {

    ServerFacade facade = new ServerFacade("http://localhost:8080");

    @Test
    public void positiveClearAppTest() {

        // Do I need to make a connection to database
        // Do I need to set up a url connection
        // What is the way of adding things to the database to test against
        ClearApplicationRequest request = new ClearApplicationRequest();
        facade.ClearApplication(request);

    }

    @Test
    public void negativeClearAppTest() {

        ClearApplicationRequest request = new ClearApplicationRequest();
        facade.ClearApplication(request);

    }

    @Test
    public void positiveCreateGameTest() {

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("hello");
        registerRequest.setPassword("world");
        registerRequest.setEmail("yer");
        RegisterResult registerResult = facade.Register(registerRequest);
        CreateGameRequest request = new CreateGameRequest();
        request.setAuthToken(registerResult.getAuthToken());
        request.setGameName("game");
        CreateGameResult result = facade.CreateGame(request);
        Assertions.assertNotEquals(0, result.getGameID());
        ClearApplicationRequest clearRequest = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest);

    }

    @Test
    public void negativeCreateGameTest() {

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("hello");
        registerRequest.setPassword("world");
        registerRequest.setEmail("yer");
        RegisterResult registerResult = facade.Register(registerRequest);
        CreateGameRequest request = new CreateGameRequest();
        request.setAuthToken("1234");
        request.setGameName("not_a_game");
        CreateGameResult result = facade.CreateGame(request);
        Assertions.assertNotNull(result.getMessage());

    }

    @Test
    public void positiveJoinGameTest() {

        ClearApplicationRequest clearRequest = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest);
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("hello");
        registerRequest.setPassword("world");
        registerRequest.setEmail("yer");
        RegisterResult registerResult = facade.Register(registerRequest);
        CreateGameRequest createRequest = new CreateGameRequest();
        createRequest.setAuthToken(registerResult.getAuthToken());
        createRequest.setGameName("game");
        CreateGameResult createResult = facade.CreateGame(createRequest);
        JoinGameRequest request = new JoinGameRequest();
        request.setGameID(createResult.getGameID());
        request.setPlayerColor(ChessGame.TeamColor.WHITE);
        request.setAuthToken(registerResult.getAuthToken());
        JoinGameResult result = facade.JoinGame(request);
        Assertions.assertNull(result.getMessage());
        ClearApplicationRequest clearRequest2 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest2);

    }

    @Test
    public void negativeJoinGameTest() {

        JoinGameRequest request = new JoinGameRequest();
        request.setGameID(5678);
        request.setPlayerColor(ChessGame.TeamColor.BLACK);
        request.setAuthToken("yuh");
        JoinGameResult result = facade.JoinGame(request);
        Assertions.assertNotNull(result.getMessage());

    }

    @Test
    public void positiveListTest() {

        ListGamesRequest request = new ListGamesRequest();
        Authtoken token = new Authtoken();
        token.setAuthToken("74b8dd58-0518-40b6-8ea0-fe38a5671e15");
        request.setAuthToken(token);
        ListGamesResult result = facade.ListGames(request);
        Assertions.assertNotEquals(0, result.getGames().size());

    }

    @Test
    public void negativeListTest() {

        ListGamesRequest request = new ListGamesRequest();
        Authtoken token = new Authtoken();
        token.setAuthToken("yuh");
        request.setAuthToken(token);
        ListGamesResult result = facade.ListGames(request);
        Assertions.assertEquals(0, result.getGames().size());

    }

    @Test
    public void positiveLogoutTest() {

        LogoutRequest request = new LogoutRequest();
        Authtoken token = new Authtoken();
        token.setAuthToken("");
        request.setAuthToken(token);
        LogoutResult result = facade.Logout(request);
        Assertions.assertEquals("", result.getMessage());

    }

    @Test
    public void negativeLogoutTest() {

        LogoutRequest request = new LogoutRequest();
        Authtoken token = new Authtoken();
        token.setAuthToken("yuh");
        request.setAuthToken(token);
        LogoutResult result = facade.Logout(request);
        Assertions.assertNotEquals("", result.getMessage());

    }

    @Test
    public void positiveLoginTest() {

        LoginRequest request = new LoginRequest();
        request.setUsername("joe");
        request.setPassword("smoe");
        LoginResult result = facade.Login(request);
        Assertions.assertEquals("joe", result.getUsername());

    }

    @Test
    public void negativeLoginTest() {

        LoginRequest request = new LoginRequest();
        request.setUsername("tom");
        request.setPassword("jerry");
        LoginResult result = facade.Login(request);
        Assertions.assertEquals("", result.getUsername());

    }

    @Test
    public void positiveRegisterTest() {

        ClearApplicationRequest clearRequest = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest);
        RegisterRequest request = new RegisterRequest();
        request.setUsername("hello");
        request.setPassword("world");
        request.setEmail("yer");
        RegisterResult result = facade.Register(request);
        Assertions.assertNotEquals("", result.getAuthToken());
        ClearApplicationRequest clearRequest2 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest2);

    }

    @Test
    public void negativeRegisterTest() {

        ClearApplicationRequest clearRequest = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest);
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setUsername("spongebob");
        registerRequest1.setPassword("patrick");
        registerRequest1.setEmail("gary");
        RegisterResult result1 = facade.Register(registerRequest1);
        RegisterRequest registerRequest2 = new RegisterRequest();
        registerRequest2.setUsername("spongebob");
        registerRequest2.setPassword("patrick");
        registerRequest2.setEmail("gary");
        //Assertions.assertNotNull(result2.getMessage());
        Assertions.assertThrows(Exception.class, () -> facade.Register(registerRequest2));
        ClearApplicationRequest clearRequest2 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest2);

    }
}




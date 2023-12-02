import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.ServerFacade;
import chess.ChessGame;
import models.Authtoken;
import requests.*;
import results.*;


public class ServerFacadeTests {

    ServerFacade facade = new ServerFacade("http://localhost:8080");
/*
    @Test
    public void positiveClearAppTest() {

        ClearApplicationRequest request = new ClearApplicationRequest();
        facade.ClearApplication(request);

    }

    @Test
    public void negativeClearAppTest() {

        ClearApplicationRequest request = new ClearApplicationRequest();
        facade.ClearApplication(request);

    }

 */

    @Test
    public void positiveCreateGameTest() {

        ClearApplicationRequest clearRequest1 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest1);
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
        ClearApplicationRequest clearRequest2 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest2);

    }

    @Test
    public void negativeCreateGameTest() {

        ClearApplicationRequest clearRequest1 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest1);
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
        ClearApplicationRequest clearRequest2 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest2);

    }

    @Test
    public void positiveJoinGameTest() {

        ClearApplicationRequest clearRequest1 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest1);
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("bruce");
        registerRequest.setPassword("willis");
        registerRequest.setEmail("christmas");
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

        ClearApplicationRequest clearRequest1 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest1);
        JoinGameRequest request = new JoinGameRequest();
        request.setGameID(5678);
        request.setPlayerColor(ChessGame.TeamColor.BLACK);
        request.setAuthToken("yuh");
        JoinGameResult result = facade.JoinGame(request);
        Assertions.assertNotNull(result.getMessage());
        ClearApplicationRequest clearRequest2 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest2);

    }

    @Test
    public void positiveListTest() {

        ClearApplicationRequest clearRequest1 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest1);
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("bruce");
        registerRequest.setPassword("willis");
        registerRequest.setEmail("christmas");
        RegisterResult registerResult = facade.Register(registerRequest);
        CreateGameRequest createRequest = new CreateGameRequest();
        createRequest.setAuthToken(registerResult.getAuthToken());
        createRequest.setGameName("game");
        CreateGameResult createResult = facade.CreateGame(createRequest);
        ListGamesRequest request = new ListGamesRequest();
        Authtoken token = new Authtoken();
        token.setAuthToken(registerResult.getAuthToken());
        request.setAuthToken(token);
        ListGamesResult result = facade.ListGames(request);
        Assertions.assertNotEquals(0, result.getGames().size());
        ClearApplicationRequest clearRequest2 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest2);

    }

    @Test
    public void negativeListTest() {

        ClearApplicationRequest clearRequest1 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest1);
        ListGamesRequest request = new ListGamesRequest();
        Authtoken token = new Authtoken();
        token.setAuthToken("yuh");
        request.setAuthToken(token);
        ListGamesResult result = facade.ListGames(request);
        Assertions.assertEquals(0, result.getGames().size());
        ClearApplicationRequest clearRequest2 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest2);

    }

    @Test
    public void positiveLogoutTest() {

        ClearApplicationRequest clearRequest1 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest1);
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("joe");
        registerRequest.setPassword("smoe");
        registerRequest.setEmail("yer");
        facade.Register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("joe");
        loginRequest.setPassword("smoe");
        LoginResult loginResult = facade.Login(loginRequest);
        LogoutRequest logoutRequest = new LogoutRequest();
        Authtoken token = new Authtoken();
        token.setAuthToken(loginResult.getAuthToken());
        logoutRequest.setAuthToken(token);
        LogoutResult result = facade.Logout(logoutRequest);
        Assertions.assertEquals("", result.getMessage());
        ClearApplicationRequest clearRequest2 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest2);

    }

    @Test
    public void negativeLogoutTest() {

        ClearApplicationRequest clearRequest1 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest1);
        LogoutRequest request = new LogoutRequest();
        Authtoken token = new Authtoken();
        token.setAuthToken("yuh");
        request.setAuthToken(token);
        LogoutResult result = facade.Logout(request);
        Assertions.assertNotEquals("", result.getMessage());
        ClearApplicationRequest clearRequest2 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest2);

    }

    @Test
    public void positiveLoginTest() {

        ClearApplicationRequest clearRequest1 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest1);
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("joe");
        registerRequest.setPassword("smoe");
        registerRequest.setEmail("yer");
        RegisterResult registerResult = facade.Register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("joe");
        loginRequest.setPassword("smoe");
        LoginResult loginResult = facade.Login(loginRequest);
        Assertions.assertEquals("joe", loginResult.getUsername());
        ClearApplicationRequest clearRequest2 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest2);

    }

    @Test
    public void negativeLoginTest() {

        ClearApplicationRequest clearRequest1 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest1);
        LoginRequest request = new LoginRequest();
        request.setUsername("tom");
        request.setPassword("jerry");
        LoginResult result = facade.Login(request);
        Assertions.assertEquals("", result.getUsername());
        ClearApplicationRequest clearRequest2 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest2);

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
        RegisterResult result2 = facade.Register(registerRequest2);
        Assertions.assertNotNull(result2.getMessage());
        ClearApplicationRequest clearRequest2 = new ClearApplicationRequest();
        facade.ClearApplication(clearRequest2);

    }
}




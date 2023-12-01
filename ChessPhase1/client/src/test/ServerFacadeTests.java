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

        CreateGameRequest request = new CreateGameRequest();
        request.setAuthToken();
        request.setGameName();
        CreateGameResult result = facade.CreateGame(request);

    }

    @Test
    public void negativeCreateGameTest() {

        CreateGameRequest request = new CreateGameRequest();
        request.setAuthToken();
        request.setGameName();
        CreateGameResult result = facade.CreateGame(request);

    }

    @Test
    public void positiveJoinGameTest() {

        JoinGameRequest request = new JoinGameRequest();
        request.setGameID();
        request.setPlayerColor();
        request.setAuthToken();
        JoinGameResult result = facade.JoinGame(request);

    }

    @Test
    public void negativeJoinGameTest() {

        JoinGameRequest request = new JoinGameRequest();
        request.setGameID();
        request.setPlayerColor();
        request.setAuthToken();
        JoinGameResult result = facade.JoinGame(request);

    }

    @Test
    public void positiveListTest() {

        ListGamesRequest request = new ListGamesRequest();
        request.setAuthToken();
        ListGamesResult result = facade.ListGames(request);

    }

    @Test
    public void negativeListTest() {

        ListGamesRequest request = new ListGamesRequest();
        request.setAuthToken();
        ListGamesResult result = facade.ListGames(request);

    }

    @Test
    public void positiveLogoutTest() {

        LogoutRequest request = new LogoutRequest();
        request.setAuthToken();
        LogoutResult result = facade.Logout(request);

    }

    @Test
    public void negativeLogoutTest() {

        LogoutRequest request = new LogoutRequest();
        request.setAuthToken();
        LogoutResult result = facade.Logout(request);

    }

    @Test
    public void positiveLoginTest() {

        LoginRequest request = new LoginRequest();
        request.setUsername();
        request.setPassword();
        LoginResult result = facade.Login(request);

    }

    @Test
    public void negativeLoginTest() {

        LoginRequest request = new LoginRequest();
        request.setUsername();
        request.setPassword();
        LoginResult result = facade.Login(request);

    }

    @Test
    public void positiveRegisterTest() {

        RegisterRequest request = new RegisterRequest();
        request.setUsername();
        request.setPassword();
        request.setEmail();
        RegisterResult result = facade.Register(request);

    }

    @Test
    public void negativeRegisterTest() {

        RegisterRequest request = new RegisterRequest();
        request.setUsername();
        request.setPassword();
        request.setEmail();
        RegisterResult result = facade.Register(request);

    }
}


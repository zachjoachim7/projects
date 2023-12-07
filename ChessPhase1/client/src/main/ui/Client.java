package ui;

import java.util.Scanner;
import chess.*;
import models.Authtoken;
import models.Game;
import requests.*;
import results.CreateGameResult;
import results.LoginResult;
import results.LogoutResult;
import results.RegisterResult;

import java.util.List;

public class Client {

    private boolean isLoggedIn = false;
    private final ServerFacade facade;
    private final Authtoken authToken = new Authtoken();

    public Client(ServerFacade facade) {
        this.facade = facade;
    }

    public static void main(String[] args) {

        Client client = new Client(new ServerFacade("http://localhost:8080"));
        Scanner scanner = new Scanner(System.in);


        while (true) {
            if (!client.isLoggedIn) {
                System.out.print("[LOGGED_OUT] >>> ");
            } else {
                System.out.print("[LOGGED_IN] >>> ");
            }

            String input = scanner.nextLine();
            if ("quit".equalsIgnoreCase(input)) {
                break;
            }

            client.handleCommand(input, client.isLoggedIn);
        }

        scanner.close();

    }

    private void handleCommand(String command, boolean loggedIn) {

        String[] brokenDownCommand = command.split("\\s+");
        if (brokenDownCommand.length == 0) {
            return;
        }

        String firstPart = brokenDownCommand[0].toLowerCase();
        switch (firstPart) {
            case "register":
                if (brokenDownCommand.length == 4) {
                    RegisterResult result = register(brokenDownCommand[1], brokenDownCommand[2], brokenDownCommand[3], loggedIn);
                    authToken.setUsername(result.getUsername());
                    authToken.setAuthToken(result.getAuthToken());
                    isLoggedIn = true;
                }
                else {
                    System.out.println("Needs more arguments");
                }
                break;
            case "login":
                if (brokenDownCommand.length == 3) {
                    LoginResult result = login(brokenDownCommand[1], brokenDownCommand[2], isLoggedIn);
                    if (result.getMessage() != null) {
                        authToken.setAuthToken(result.getAuthToken());
                        isLoggedIn = true;
                    }
                }
                else {
                    System.out.println("Needs more arguments");
                }
                break;
            case "help":
                help(loggedIn);
                break;
            case "create":
                if (brokenDownCommand.length == 2) {
                    CreateGameResult result = createGame(brokenDownCommand[1], isLoggedIn);
                }
                else {
                    System.out.println("Need a game name");
                }
                break;
            case "join":
                if (brokenDownCommand.length == 3) {
                    joinGame(brokenDownCommand[1], brokenDownCommand[2], isLoggedIn);
                }
                else {
                    System.out.println("Invalid Arguments");
                }
                break;
            case "quit":
                quit();
                break;
            case "list":
                if (brokenDownCommand.length == 1) {
                    List<Game> games = list(isLoggedIn);
                    for (Game game : games) {
                        System.out.println("Game ID: " + game.getGameID());
                        System.out.println("Game Name: " + game.getGameName());
                        System.out.println("White Username: " + game.getWhiteUsername());
                        System.out.println("Black Username: " + game.getBlackUsername());
                    }
                }
                else {
                    System.out.println("Invalid Arguments");
                }
                break;
            case "observe":
                if (brokenDownCommand.length == 2) {
                    observeGame(brokenDownCommand[1], isLoggedIn);
                }
                else {
                    System.out.println("Invalid Arguments");
                }
                break;
            case "logout":
                if (brokenDownCommand.length == 1) {
                    logout(isLoggedIn);
                    isLoggedIn = false;

                }
                else {
                    System.out.println("Invalid Arguments");
                }
                break;
        }

    }

    private void help(boolean loggedIn) {

        if (loggedIn) {
            System.out.println("create <NAME> - create a new Chess game");
            System.out.println("list - list all games being played");
            System.out.println("join <ID> [WHITE | BLACK | <empty> ] - join an existing game");
            System.out.println("observe <ID> - watch an existing game");
            System.out.println("logout - to bring you back to the login screen");
            System.out.println("quit - quit the application");
            System.out.println("help - list available commands");
        }
        else {
            System.out.println("register <USERNAME> <PASSWORD> <EMAIL> - use to register an account");
            System.out.println("login <USERNAME> <PASSWORD> - to login if you already have an account");
            System.out.println("quit - quit the application");
            System.out.println("help - list available commands");
        }

    }

    private RegisterResult register(String username, String password, String email, boolean loggedIn) {
        if (loggedIn) {
            throw new IllegalArgumentException("Already logged in");
        }
        else {
            RegisterRequest request = new RegisterRequest();
            request.setUsername(username);
            request.setPassword(password);
            request.setEmail(email);
            return facade.Register(request);
        }
    }

    private LoginResult login(String username, String password, boolean loggedIn) {
        if (loggedIn) {
            throw new IllegalArgumentException("Already logged in");
        }
        else {
            LoginRequest request = new LoginRequest();
            request.setUsername(username);
            request.setPassword(password);
            return facade.Login(request);
        }
    }

    private CreateGameResult createGame(String gameName, boolean loggedIn) {
        if (!loggedIn) {
            throw new IllegalArgumentException("Not logged in");
        }
        else {
            MyChessBoard board = new MyChessBoard();
            board.resetBoard();
            System.out.println("Game created: " + gameName);
            CreateGameRequest request = new CreateGameRequest();
            request.setAuthToken(authToken.getAuthToken());
            request.setGameName(gameName);
            return facade.CreateGame(request);
        }
    }

    private void joinGame(String gameId, String color, boolean loggedIn) {

        if (!loggedIn) {
            throw new IllegalArgumentException("Need to login");
        }
        else {
            JoinGameRequest request = new JoinGameRequest();
            request.setAuthToken(authToken.getAuthToken());
            request.setGameID(Integer.parseInt(gameId));
            if (color.equals("WHITE")) {
                request.setPlayerColor(ChessGame.TeamColor.WHITE);
            }
            else if (color.equals("BLACK")) {
                request.setPlayerColor(ChessGame.TeamColor.BLACK);
            }

            ChessBoardPrinter printer = new ChessBoardPrinter(facade);
            MyChessBoard board = new MyChessBoard();
            board.resetBoard();

            facade.JoinGame(request);
        }

    }

    private void observeGame(String gameId, boolean loggedIn) {

        if (!loggedIn) {
            throw new IllegalArgumentException("Need to login");
        }
        else {
            JoinGameRequest request = new JoinGameRequest();
            request.setGameID(Integer.parseInt(gameId));
            request.setAuthToken(authToken.getAuthToken());

            ChessBoardPrinter printer = new ChessBoardPrinter(facade);
            MyChessBoard board = new MyChessBoard();
            board.resetBoard();

            facade.JoinGame(request);
        }

    }

    private LogoutResult logout(boolean loggedIn) {

        if (!loggedIn) {
            throw new IllegalArgumentException("Need to login first");
        }
        else {
            LogoutRequest request = new LogoutRequest();
            request.setAuthToken(authToken);
            return facade.Logout(request);
        }
    }

    private void quit() {

        System.out.println("Quitting application.");
        System.exit(0); // Terminate the program
    }

    private List<Game> list(boolean loggedIn) {

        if (!loggedIn) {
            throw new IllegalArgumentException("Need to login first");
        }
        else {
            ListGamesRequest request = new ListGamesRequest();
            request.setAuthToken(authToken);
            return facade.ListGames(request).getGames();
        }

    }

}

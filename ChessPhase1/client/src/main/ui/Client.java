package ui;

import java.util.Scanner;
import chess.ChessGame;
import models.ServerFacade;
import requests.*;
import results.RegisterResult;

public class Client {

    private boolean isLoggedIn = false;
    private final ServerFacade facade = new ServerFacade("http://localhost:8080");

    public static void main(String[] args) {

        Client client = new Client();
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
                    register(brokenDownCommand[1], brokenDownCommand[2], brokenDownCommand[3], loggedIn);
                    isLoggedIn = true;
                }
                else {
                    System.out.println("Needs more arguments");
                }
                break;
            case "login":
                if (brokenDownCommand.length == 3) {
                    login(brokenDownCommand[1], brokenDownCommand[2], isLoggedIn);
                    isLoggedIn = true;
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
                    createGame(brokenDownCommand[1], isLoggedIn);
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
                    list(isLoggedIn);
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

    private void register(String username, String password, String email, boolean loggedIn) {
        if (loggedIn) {
            throw new IllegalArgumentException("Already logged in");
        }
        else {
            RegisterRequest request = new RegisterRequest();
            request.setUsername(username);
            request.setPassword(password);
            request.setEmail(email);
            RegisterResult result = facade.Register(request);
        }
    }

    private void login(String username, String password, boolean loggedIn) {
        if (loggedIn) {
            throw new IllegalArgumentException("Already logged in");
        }
        else {
            LoginRequest request = new LoginRequest();
            request.setUsername(username);
            request.setPassword(password);
            facade.Login(request);
        }
    }

    private void createGame(String gameName, boolean loggedIn) {
        if (!loggedIn) {
            throw new IllegalArgumentException("Not logged in");
        }
        else {
            String[][] initialBoard = {
                    {"\u001b[;100;1m ", "\u001b[30;100;1m  h", "\u001b[30;100;1m g", "\u001b[30;100;1m f", "\u001b[30;100;1m e",
                            "\u001b[30;100;1m d", "\u001b[30;100;1m c", "\u001b[30;100;1m b", "\u001b[30;100;1m a", "\u001b[;100;1m "},
                    {"\u001b[;100;1m 1", "\033[32;47;1m \u265C", "\033[32;40;1m \u265E", "\033[32;47;1m \u265D", "\033[32;40;1m \u265A",
                            "\033[32;47;1m \u265B", "\033[32;40;1m \u265D", "\033[32;47;1m \u265E", "\033[32;40;1m \u265C", "\u001b[;100;1m 1"},
                    {"\u001b[;100;1m 2", "\033[32;40;1m \u265F", "\033[32;47;1m \u265F", "\033[32;40;1m \u265F", "\033[32;47;1m \u265F",
                            "\033[32;40;1m \u265F", "\033[32;47;1m \u265F", "\033[32;40;1m \u265F", "\033[32;47;1m \u265F", "\u001b[;100;1m 2"},
                    {"\u001b[;100;1m 3", "\033[;47;1m  ", "\033[;40;1m  ", "\033[;47;1m  ", "\033[;40;1m  ", "\033[;47;1m  ",
                            "\033[;40;1m  ", "\033[;47;1m  ", "\033[;40;1m  ", "\u001b[;100;1m 3"},
                    {"\u001b[;100;1m 4", "\033[;40;1m  ", "\033[;47;1m  ", "\033[;40;1m  ", "\033[;47;1m  ", "\033[;40;1m  ",
                            "\033[;47;1m  ", "\033[;40;1m  ", "\033[;47;1m  ", "\u001b[;100;1m 4"},
                    {"\u001b[;100;1m 5", "\033[;47;1m  ", "\033[;40;1m  ", "\033[;47;1m  ", "\033[;40;1m  ", "\033[;47;1m  ",
                            "\033[;40;1m  ", "\033[;47;1m  ", "\033[;40;1m  ", "\u001b[;100;1m 5"},
                    {"\u001b[;100;1m 6", "\033[;40;1m  ", "\033[;47;1m  ", "\033[;40;1m  ", "\033[;47;1m  ", "\033[;40;1m  ",
                            "\033[;47;1m  ", "\033[;40;1m  ", "\033[;47;1m  ", "\u001b[;100;1m 6"},
                    {"\u001b[;100;1m 7", "\033[31;47;1m \u265F", "\033[31;40;1m \u265F", "\033[31;47;1m \u265F", "\033[31;40;1m \u265F",
                            "\033[31;47;1m \u265F", "\033[31;40;1m \u265F", "\033[31;47;1m \u265F", "\033[31;40;1m \u265F", "\u001b[;100;1m 7"},
                    {"\u001b[;100;1m 8", "\033[31;40;1m \u265C", "\033[31;47;1m \u265E", "\033[31;40;1m \u265D", "\033[31;47;1m \u265A",
                            "\033[31;40;1m \u265B", "\033[31;47;1m \u265D", "\033[31;40;1m \u265E", "\033[31;47;1m \u265C", "\u001b[;100;1m 8"},
                    {"\u001b[;100;1m ", "\u001b[30;100;1m  h", "\u001b[30;100;1m g", "\u001b[30;100;1m f", "\u001b[30;100;1m e",
                            "\u001b[30;100;1m d", "\u001b[30;100;1m c", "\u001b[30;100;1m b", "\u001b[30;100;1m a", "\u001b[;100;1m "}
            };

            for (int i = 0; i < initialBoard.length; i++) {
                for (int j = 0; j < initialBoard[i].length; j++) {
                    String piece = initialBoard[i][j];
                    System.out.print(piece + " ");
                }
                System.out.print(EscapeSequences.RESET_BG_COLOR);
                System.out.println();
            }
            for (int i = initialBoard.length - 1; i >= 0; i--) {
                for (int j = initialBoard.length - 1; j >= 0; j--) {
                    String piece = initialBoard[i][j];
                    System.out.print(piece + " ");
                }
                System.out.print(EscapeSequences.RESET_BG_COLOR);
                System.out.println();
            }
            System.out.println("Game created: " + gameName);
            CreateGameRequest request = new CreateGameRequest();
            request.setGameName(gameName);
            facade.CreateGame(request);
        }
    }


    private void joinGame(String gameId, String color, boolean loggedIn) {

        if (!loggedIn) {
            throw new IllegalArgumentException("Need to login");
        }
        else {
            JoinGameRequest request = new JoinGameRequest();
            request.setGameID(Integer.parseInt(gameId));
            if (color.equals("WHITE")) {
                request.setPlayerColor(ChessGame.TeamColor.WHITE);
            }
            else if (color.equals("BLACK")) {
                request.setPlayerColor(ChessGame.TeamColor.BLACK);
            }
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
            facade.JoinGame(request);
        }

    }

    private void logout(boolean loggedIn) {

        if (!loggedIn) {
            throw new IllegalArgumentException("Need to login first");
        }
        else {
            LogoutRequest request = new LogoutRequest();
            facade.Logout(request);
        }

    }

    private void quit() {
        System.out.println("Quitting application.");
        System.exit(0); // Terminate the program
    }

    private void list(boolean loggedIn) {

        if (!loggedIn) {
            throw new IllegalArgumentException("Need to login first");
        }
        else {
            ListGamesRequest request = new ListGamesRequest();
            facade.ListGames(request);
        }

    }

}

package ui;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
import chess.*;
import models.Authtoken;
import models.Game;
import requests.*;
import results.LoginResult;
import results.LogoutResult;
import java.util.List;

public class Client {

    private boolean isLoggedIn = false;
    private final ServerFacade facade = new ServerFacade("http://localhost:8080");
    private final Authtoken authToken = new Authtoken();

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
                    LoginResult result = login(brokenDownCommand[1], brokenDownCommand[2], isLoggedIn);
                    authToken.setAuthToken(result.getAuthToken());
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
                    List<Game> games = list(isLoggedIn);
                    for (Game game : games) {
                        System.out.println(game.toString());
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
                    LogoutResult result = logout(isLoggedIn);
                    if (result == null) {
                        isLoggedIn = false;
                    }
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
            facade.Register(request);
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

    private void createGame(String gameName, boolean loggedIn) {
        if (!loggedIn) {
            throw new IllegalArgumentException("Not logged in");
        }
        else {
            Map<ChessPiece.PieceType, String> pieceMap = new HashMap<>();
            pieceMap.put(ChessPiece.PieceType.ROOK, "R");
            pieceMap.put(ChessPiece.PieceType.KNIGHT, "N");
            pieceMap.put(ChessPiece.PieceType.BISHOP, "R");
            pieceMap.put(ChessPiece.PieceType.QUEEN, "Q");
            pieceMap.put(ChessPiece.PieceType.KING, "K");
            pieceMap.put(ChessPiece.PieceType.PAWN, "P");

            MyChessBoard board = new MyChessBoard();
            printBoardBlack(board, pieceMap);
            printBoardWhite(board, pieceMap);
            System.out.println("Game created: " + gameName);
            CreateGameRequest request = new CreateGameRequest();
            request.setAuthToken(authToken.getAuthToken());
            request.setGameName(gameName);
            facade.CreateGame(request);
        }
    }

    private void printBoardBlack(MyChessBoard gameBoard, Map<ChessPiece.PieceType, String> map) {
        // Print the top letter row with blue background
        printLetterRow();

        for (int i = 8; i >= 1; i--) {
            // Apply blue background before row number
            System.out.print("\u001b[104m " + i + " ");
            for (int j = 1; j <= 8; j++) {
                // Print the actual board squares
                printBoardSquare(gameBoard, i, j, map);
            }
            // Apply blue background after row number
            System.out.print("\u001b[104m " + i + " ");
            System.out.println(EscapeSequences.RESET_BG_COLOR); // Reset the background color at the end of each line
        }

        // Print the bottom letter row with blue background
        printLetterRow();
    }

    private void printLetterRow() {
        // Apply blue background for the whole row
        System.out.print("\u001b[104m  "); // Start with two spaces to align with the board
        for (char letter = 'a'; letter <= 'h'; letter++) {
            System.out.print("  " + letter);
        }
        System.out.println(" \u001b[0m"); // End with two spaces and reset the color
    }

    private void printBoardSquare(MyChessBoard gameBoard, int i, int j, Map<ChessPiece.PieceType, String> map) {
        ChessPiece piece = gameBoard.getPiece(new MyChessPosition(i, j));
        String pieceSymbol = (piece != null) ? map.get(piece.getPieceType()) : "  ";
        int bgColorCode = ((i + j) % 2 == 0) ? 94 : 101; // Choose color based on position
        String colorCode = (piece != null && piece.getTeamColor() == ChessGame.TeamColor.BLACK) ? "\u001b[30m" : "";
        System.out.print("\u001b[48;5;" + bgColorCode + "m" + colorCode + pieceSymbol + " ");
    }

    private void printBoardWhite(MyChessBoard gameBoard, Map<ChessPiece.PieceType, String> map) {

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
            return facade.ListGames(request);
        }

    }

}

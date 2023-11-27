package ui;

import java.util.Scanner;
import ui.BoardPrinter;

public class Client {

    private final boolean isLoggedIn = false;

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
                break;
            case "login":
                break;
            case "help":
                help(loggedIn);
                break;
            case "create":
                if (brokenDownCommand[1].equals("")) {
                    System.out.println("You need a game name");
                    break;
                }
                createGame(brokenDownCommand[1]);
                break;
            case "join":
                break;
            case "quit":
                quit();
                break;
            case "list":
                break;
            case "observe":
                break;
            case "logout":
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
        // TODO: Implement registration logic
        System.out.println("Registering user: " + username);
    }

    private void login(String username, String password) {
        // TODO: Implement login logic
        //isLoggedIn = true;
        System.out.println("User logged in: " + username);
    }

    private void printHelp() {
        // TODO: Print out help information
        System.out.println("Available commands: register, login, help, create, join, observe, logout, quit");
    }

    private void createGame(String gameName) {
        String[][] initialBoard = {
                {"\u001b[;100;m 1", "R", "N", "B", "Q", "K", "B", "N", "R", "\u001b[;100;m 1"},
                {"\u001b[;100;m 2", "P", "P", "P", "P", "P", "P", "P", "P", "\u001b[;100;m 2"},
                {"\u001b[;100;m 3", " ", " ", " ", " ", " ", " ", " ", " ", "\u001b[;100;m 3"},
                {"\u001b[;100;m 4", " ", " ", " ", " ", " ", " ", " ", " ", "\u001b[;100;m 4"},
                {"\u001b[;100;m 5", " ", " ", " ", " ", " ", " ", " ", " ", "\u001b[;100;m 5"},
                {"\u001b[;100;m 6", " ", " ", " ", " ", " ", " ", " ", " ", "\u001b[;100;m 6"},
                {"\u001b[;100;m 7", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u001b[;100;m 7"},
                {"\u001b[;100;m 8", "\033[31;40;m \u265C", "\033[31;47;m \u265E", "\033[31;40;m \u265D", "\033[31;47;m \u265B",
                        "\033[31;40;m \u265A", "\033[31;47;m \u265D", "\033[31;40;m \u265E", "\033[31;47;m \u265C", "\u001b[;100;m 8"}
        };
        String[] unicodePieces = {
                "r", "n", "b", "q", "k", "p", "R", "N", "B", "Q", "K", "P", "."
        };
        String[] unicodePiecesCharacters = {
                "\u265C", "\u265E", "\u265D", "\u265B", "\u265A", "\u265F",
                "\u2656", "\u2658", "\u2657", "\u2655", "\u2654", "\u2659", " "
        };
        for (int i = 0; i < initialBoard.length; i++) {
            for (int j = 0; j < initialBoard[i].length; j++) {
                String piece = initialBoard[i][j];
                System.out.print(piece + " ");
            }
            System.out.println();
        }
        System.out.println("Game created: " + gameName);
    }



    private void joinGame(String gameId, String color) {
        // TODO: Implement game joining logic
        System.out.println("Joining game: " + gameId + " as " + color);
    }

    private void observeGame(String gameId) {
        // TODO: Implement game observation logic
        System.out.println("Observing game: " + gameId);
    }

    private void logout() {
        // TODO: Implement logout logic
        //isLoggedIn = false;
        System.out.println("User logged out.");
    }

    private void quit() {
        // TODO: Implement application quit logic
        System.out.println("Quitting application.");
        System.exit(0); // Terminate the program
    }

}

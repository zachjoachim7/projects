import spark.*;
import handlers.*;

/**
 * The Server class sets up a Spark server and defines routes for handling web requests.
 */
public class Server {
    /**
     * The entry point of the application.
     *
     * @param args Command-line arguments; expects a port number as the first argument.
     */
    public static void main(String[] args) {
        int port = 8080;
        Spark.port(port);
        new Server().run();
    }

    /**
     * Initializes the Spark server by specifying the static file location and creating routes.
     * Handles potential exceptions when parsing command-line arguments.
     */
    private void run() {
        try {
            Spark.externalStaticFileLocation("/Users/zachjoachim/Desktop/java/ChessPhase1/src/web");
            createRoutes();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            System.err.println("Specify the port number as a command line parameter");
        }
    }

    /**
     * Defines the web routes for the server.
     * - "/hello": Responds with "Hello World" for a GET request.
     * - "/user": Handles registration requests using the RegisterHandler for a POST request.
     * - "/session": Handles login requests using the LoginHandler for a POST request.
     * - "/session": Handles logout requests using the LogoutHandler for a DELETE request.
     * - "/db": Handles requests to clear the application database using ClearApplicationHandler for a DELETE request.
     * - "/game": Handles listing games using ListGamesHandler for a GET request.
     * - "/game": Handles creating games using CreateGameHandler for a POST request.
     * - "/game": Handles joining games using JoinGameHandler for a PUT request.
     */
    private void createRoutes() {
        Spark.get("/hello", (req, res) -> "Hello World");
        Spark.post("/user", (req, res) -> new RegisterHandler().handleRequest(req, res));
        Spark.post("/session", (req, res) -> new LoginHandler().handleRequest(req, res));
        Spark.delete("/session", (req, res) -> new LogoutHandler().handleRequest(req, res));
        Spark.delete("/db", (req, res) -> new ClearApplicationHandler().handleRequest(req, res));
        Spark.get("game", (req, res) -> new ListGamesHandler().handleRequest(req, res));
        Spark.post("game", (req, res) -> new CreateGameHandler().handleRequest(req, res));
        Spark.put("game", (req, res) -> new JoinGameHandler().handleRequest(req, res));
    }
}

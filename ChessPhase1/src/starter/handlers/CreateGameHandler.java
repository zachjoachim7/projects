package handlers;

import requests.CreateGameRequest;
import results.CreateGameResult;
import services.CreateGameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class CreateGameHandler {

    public Object handleRequest(Request request, Response response) {
        CreateGameRequest req = new CreateGameRequest();
        req.setGameName(request.body());
        req.setAuthToken(request.headers("Authorization"));
        CreateGameService service = new CreateGameService();
        CreateGameResult result = service.createGame(req);

        String message = result.getMessage();

        if (message == null) {
            return new Gson().toJson(result);
        }
        else if (message.equals("Error: bad request")) {
            response.status(400);
        }
        else if (message.equals("Error: unauthorized")) {
            response.status(401);
        }

        return new Gson().toJson(result);
    }

}

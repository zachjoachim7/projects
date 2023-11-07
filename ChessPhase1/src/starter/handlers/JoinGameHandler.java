package handlers;

import com.google.gson.Gson;
import requests.JoinGameRequest;
import results.JoinGameResult;
import services.JoinGameService;
import spark.Request;
import spark.Response;

public class JoinGameHandler {

    public Object handleRequest(Request request, Response response) {
        JoinGameRequest req = new Gson().fromJson(request.body(), JoinGameRequest.class);
        req.setAuthToken(request.headers("Authorization"));
        JoinGameService service = new JoinGameService();
        JoinGameResult result = service.joinGame(req);

        if (result.getMessage() == null) {
            return new Gson().toJson(result);
        }
        else if (result.getMessage().equals("Error: bad request")) {
            response.status(400);
        }
        else if (result.getMessage().equals("Error: unauthorized")) {
            response.status(401);
        }
        else if (result.getMessage().equals("Error: already taken")) {
            response.status(403);
        }
        else {
            response.status(500);
        }

        return new Gson().toJson(result);
    }

}

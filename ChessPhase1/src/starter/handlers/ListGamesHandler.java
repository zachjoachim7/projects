package handlers;

import com.google.gson.Gson;
import results.ListGamesResult;
import services.ListGamesService;
import spark.Request;
import spark.Response;

public class ListGamesHandler {

    public Object handleRequest(Request request, Response response) {

        ListGamesService service = new ListGamesService();
        ListGamesResult result = service.listGames(request.headers("Authorization"));

        if (result.getMessage() == null) {
            return new Gson().toJson(result);
        }
        if (result.getMessage().equals("Error: unauthorized")) {
            response.status(401);
        }

        return new Gson().toJson(result);

    }

}



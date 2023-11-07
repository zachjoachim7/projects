package handlers;

import com.google.gson.Gson;
import results.LogoutResult;
import services.LogoutService;
import spark.Request;
import spark.Response;

public class LogoutHandler {

    public Object handleRequest(Request request, Response response) {
        LogoutService service = new LogoutService();
        LogoutResult result = service.logout(request.headers("Authorization"));

        if (result.getMessage() == null) {
            response.body(new Gson().toJson(result));
            return new Gson().toJson(result);
        }
        if (result.getMessage().equals("Error: unauthorized")) {
            response.status(401);
        }
        return new Gson().toJson(result);
    }

}

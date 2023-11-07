package handlers;

import com.google.gson.Gson;
import requests.LoginRequest;
import results.LoginResult;
import services.LoginService;
import spark.Request;
import spark.Response;

public class LoginHandler {

    public Object handleRequest(Request request, Response response) {
        LoginRequest req = new Gson().fromJson(request.body(), LoginRequest.class);
        LoginService service = new LoginService();
        LoginResult result = service.login(req);

        if (result.getMessage() == null) {
            return new Gson().toJson(result);
        }

        if (result.getMessage().equals("Error: unauthorized")) {
            response.status(401);
        }

        return new Gson().toJson(result);
    }

}

package handlers;

import services.RegisterService;
import requests.RegisterRequest;
import results.RegisterResult;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class RegisterHandler {


    public Object handleRequest(Request request, Response response) {
        RegisterRequest req = new Gson().fromJson(request.body(), RegisterRequest.class);
        RegisterService service = new RegisterService();
        RegisterResult result = service.register(req);

        String message = result.getMessage();

        if (message == null) {
            return new Gson().toJson(result);
        }
        else if (result.getMessage().equals("Error: already taken")) {
            response.status(403);
            return new Gson().toJson(result);
        }
        else if (result.getMessage().equals("Error: bad request")) {
            response.status(400);
            return new Gson().toJson(result);
        }

        result.setMessage("Error: description");
        response.status(500);

        return new Gson().toJson(result);

    }
}

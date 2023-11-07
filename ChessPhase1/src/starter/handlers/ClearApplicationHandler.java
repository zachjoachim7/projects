package handlers;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import requests.ClearApplicationRequest;
import results.ClearApplicationResult;
import services.ClearApplicationService;
import spark.Request;
import spark.Response;

public class ClearApplicationHandler {

    public Object handleRequest(Request request, Response response) throws DataAccessException {
        ClearApplicationRequest req = new ClearApplicationRequest();
        ClearApplicationService service = new ClearApplicationService();
        ClearApplicationResult result = service.clearApplication(req);

        return new Gson().toJson(result);
    }

}

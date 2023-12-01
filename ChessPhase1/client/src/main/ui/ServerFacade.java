package ui;

import models.Game;
import requests.*;
import results.*;
import java.net.*;
import java.io.*;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServerFacade {

    private final String serverUrl;

    public ServerFacade(String url) {
        serverUrl = url;
    }

    public <T> T makeRequest(String method, String path, Object request, Class<T> responseClass) {
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);

            if (request != null) {
                http.addRequestProperty("Content-Type", "application/json");
                String reqData = new Gson().toJson(request);
                try (OutputStream reqBody = http.getOutputStream()) {
                    reqBody.write(reqData.getBytes());
                }
            }
            http.connect();
            var status = http.getResponseCode();
            if (status != 200) {
                throw new Exception();
            }

            T response = null;
            if (http.getContentLength() < 0) {
                try (InputStream respBody = http.getInputStream()) {
                    InputStreamReader reader = new InputStreamReader(respBody);
                    if (responseClass != null) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        response = gsonBuilder.create().fromJson(reader, responseClass);
                    }
                }
            }
            return response;

        } catch (Exception e) {
            System.out.println("You did something wrong");
        }
        return null;
    }

    public <T> T makeLogoutRequest(String method, String path, LogoutRequest request, Class<T> responseClass) {

        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);

            if (request != null) {
                http.addRequestProperty("Authorization", request.getAuthToken().getAuthToken());
            }
            http.connect();
            var status = http.getResponseCode();
            if (status != 200) {
                T response = null;

                try (InputStream respBody = http.getInputStream()) {
                    InputStreamReader reader = new InputStreamReader(respBody);
                    if (responseClass != null) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        response = gsonBuilder.create().fromJson(reader, responseClass);
                    }
                }
                return response;
            }

        } catch (Exception e) {
            System.out.println("You did something wrong");
        }
        return null;
    }

    public <T> T makeCreateRequest(String method, String path, CreateGameRequest request, Class<T> responseClass) {

        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);
            if (request != null) {
                http.addRequestProperty("Authorization", request.getAuthToken());
                http.addRequestProperty("Content-Type", "application/json");
                String reqData = new Gson().toJson(request);
                try (OutputStream reqBody = http.getOutputStream()) {
                    reqBody.write(reqData.getBytes());
                }
            }

            http.connect();
            var status = http.getResponseCode();
            if (status != 200) {
                throw new Exception();
            }
            T response = null;
            if (http.getContentLength() < 0) {
                try (InputStream respBody = http.getInputStream()) {
                    InputStreamReader reader = new InputStreamReader(respBody);
                    if (responseClass != null) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        response = gsonBuilder.create().fromJson(reader, responseClass);
                    }
                }
            }
            return response;

        } catch (Exception e) {
            System.out.println("You did something wrong on create request");
        }
        return null;
    }

    public <T> T makeListRequest(String method, String path, ListGamesRequest request, Class<T> responseClass) {
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);

            if (request != null) {
                http.addRequestProperty("Authorization", request.getAuthToken().getAuthToken());
            }
            http.connect();
            var status = http.getResponseCode();
            if (status != 200) {
                throw new Exception();
            }
            T response = null;

            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader reader = new InputStreamReader(respBody);
                if (responseClass != null) {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    response = gsonBuilder.create().fromJson(reader, responseClass);
                }
            }
            return response;

        } catch (Exception e) {
            System.out.println("You did something wrong with listing");
        }
        return null;
    }

    public <T> T makeJoinRequest(String method, String path, JoinGameRequest request, Class<T> responseClass) {
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);
            if (request != null) {
                http.addRequestProperty("Authorization", request.getAuthToken());
                http.addRequestProperty("Content-Type", "application/json");
                String reqData = new Gson().toJson(request);
                try (OutputStream reqBody = http.getOutputStream()) {
                    reqBody.write(reqData.getBytes());
                }
            }
            http.connect();
            var status = http.getResponseCode();
            if (status != 200) {
                T response = null;

                try (InputStream respBody = http.getInputStream()) {
                    InputStreamReader reader = new InputStreamReader(respBody);
                    if (responseClass != null) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        response = gsonBuilder.create().fromJson(reader, responseClass);
                    }
                }
                return response;
            }

        } catch (Exception e) {
            System.out.println("You did something wrong on join request");
        }
        return null;
    }


    public void ClearApplication(ClearApplicationRequest request) {

        var path = "/db";
        this.makeRequest("DELETE", path, request, ClearApplicationResult.class);

    }

    public CreateGameResult CreateGame(CreateGameRequest request) {

        var path = "/game";
        return this.makeCreateRequest("POST", path, request, CreateGameResult.class);

    }

    public JoinGameResult JoinGame(JoinGameRequest request) {

        var path = "/game";
        return this.makeJoinRequest("PUT", path, request, JoinGameResult.class);

    }

    public ListGamesResult ListGames(ListGamesRequest request) {

        var path = "/game";
        return this.makeListRequest("GET", path, request, ListGamesResult.class);

    }

    public LoginResult Login(LoginRequest request) {

        var path = "/session";
        return this.makeRequest("POST", path, request, LoginResult.class);

    }

    public LogoutResult Logout(LogoutRequest request) {

        var path = "/session";
        return this.makeLogoutRequest("DELETE", path, request, LogoutResult.class);

    }

    public RegisterResult Register(RegisterRequest request) {

        var path = "/user";
        return this.makeRequest("POST", path, request, RegisterResult.class);

    }

}

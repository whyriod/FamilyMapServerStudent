package handler.database;

import java.io.*;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import result.ClearResult;
import service.ClearService;

public class fillHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
        exchange.getResponseBody().close();
    }
}
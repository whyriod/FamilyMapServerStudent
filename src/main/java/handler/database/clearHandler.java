package handler.database;

import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import result.ClearResult;
import service.ClearService;

public class clearHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        //Try to handle the request
        try {
            //If its a post request
            if(exchange.getRequestMethod().toLowerCase().equals("post")) {
                //Access the Clear service.
                ClearService clear = new ClearService();
                ClearResult result = clear.clearDatabase();

                //Send Response: 200
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                Writer respBody = new OutputStreamWriter(exchange.getResponseBody());
                Gson gson = new Gson();
                gson.toJson(result, respBody);
                respBody.close();
            }
            //Faulty request
            else{
                //Send Response: 404
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                exchange.getResponseBody().close();
            }
        }
        //Internal Error
        catch(IOException e) {
            //Send Response: 500
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
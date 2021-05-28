package handler.User;

import cereal.Cereal;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.LoadRequest;
import request.RegisterRequest;
import result.LoadResult;
import result.RegisterResult;
import service.LoadService;
import service.RegisterService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

public class registerHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        //Try to handle the request
        try {
            //If its a post request
            if(exchange.getRequestMethod().toLowerCase().equals("post")) {

                // Get the request body input stream
                InputStream reqBody = exchange.getRequestBody();
                // Read JSON string from the input stream
                Cereal cereal = new Cereal();
                String reqData = cereal.readString(reqBody);

                //Create the request and attempt the service
                Gson gson = new Gson();
                RegisterRequest request = gson.fromJson(reqData,RegisterRequest.class);
                RegisterService register = new RegisterService();
                RegisterResult result = register.registerUser(request);

                //Send Response: 200
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                Writer respBody = new OutputStreamWriter(exchange.getResponseBody());
                gson.toJson(result, respBody);
                respBody.close();
            }

            //Faulty Request
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
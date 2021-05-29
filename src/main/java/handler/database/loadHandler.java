package handler.database;

import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import request.LoadRequest;
import result.LoadResult;
import service.LoadService;
import cereal.*;

public class loadHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) {

        //IOException Block
        try{
            //Try to handle the request
            try {

                //If its a post request
                if(exchange.getRequestMethod().toLowerCase().equals("post")) {

                    InputStream reqBody = exchange.getRequestBody();
                    Cereal cereal = new Cereal();
                    String reqData = cereal.readString(reqBody);

                    //Create the request and attempt the service
                    Gson gson = new Gson();
                    LoadRequest request = (LoadRequest) gson.fromJson(reqData,LoadRequest.class);
                    LoadService load = new LoadService();
                    LoadResult result = load.loadDatabase(request);

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
        catch(IOException e){
            System.out.println("IOException in clearHandler: " + e);
            e.printStackTrace();
        }
    }
}
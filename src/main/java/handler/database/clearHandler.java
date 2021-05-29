package handler.database;

import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import result.ClearResult;
import service.ClearService;

/***
 * Handles clearing all Database table data
 */
public class clearHandler implements HttpHandler {

    /***
     * Attempts to clear all DB data.
     *
     * @param exchange - Exchange object from Server
     */
    @Override
    public void handle(HttpExchange exchange) {


        try {
            try {
                //If its a post request
                if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                    //Access the Clear service.
                    ClearService clear = new ClearService();
                    ClearResult result = clear.clearDatabase();

                    //Clear: Success
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    Writer respBody = new OutputStreamWriter(exchange.getResponseBody());
                    Gson gson = new Gson();
                    gson.toJson(result, respBody);
                    respBody.close();
                }

                //Faulty request
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                    exchange.getResponseBody().close();
                }
            }
            //Internal Error
            catch (IOException e) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
                exchange.getResponseBody().close();
                e.printStackTrace();
            }
        }
        //IOException
        catch(IOException e){
            System.out.println("IOException in clearHandler: " + e);
            e.printStackTrace();
        }
    }
}
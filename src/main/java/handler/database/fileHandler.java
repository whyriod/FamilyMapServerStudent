package handler.database;

import java.io.*;
import java.net.*;
import java.nio.file.Files;

import com.sun.net.httpserver.*;

public class fileHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        //Try to handle the request
        try {
            //If its a get request
            if(exchange.getRequestMethod().toLowerCase().equals("get")) {
                //Get the URL
                String urlPath = exchange.getRequestURI().toString();

                //If the URL is null or root, set it to index.html
                if(urlPath.equals("/") || urlPath.equals(null)){
                    urlPath = "/index.html";
                }
                //Append the web directory to the front.
                String filePath = "web" + urlPath;
                File file = new File(filePath);

                //File Found
                if(file.exists()){
                    //Send Response: 200
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    OutputStream respBody = exchange.getResponseBody();
                    Files.copy(file.toPath(),respBody);
                    respBody.close();
                }
                else{
                    //Send Response: 404
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                    exchange.getResponseBody().close();
                }
            }
            //Faulty request
            else{
                //Send Response: 400
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
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
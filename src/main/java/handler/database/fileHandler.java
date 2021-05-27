package handler.database;

import java.io.*;
import java.net.*;
import java.nio.file.Files;

import com.sun.net.httpserver.*;

public class fileHandler implements HttpHandler {
    ////GET

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        //Assume the worst
        boolean success = false;

        try {
            //If its a get request
            if(exchange.getRequestMethod().toLowerCase().equals("get")) {
                //Get the URL
                String urlPath = exchange.getRequestURI().toString();

                //If the URL is null or root, set it to index.html
                if(urlPath == null || urlPath == "/"){
                    urlPath = "/index.html";
                }
                //Append the web directory to the front.
                String filePath = "web" + urlPath;
                File file = new File(filePath);

                //If the web/{urlPath} is found.
                if(file.exists()){
                    OutputStream respBody = exchange.getResponseBody();
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    Files.copy(file.toPath(),respBody);
                    respBody.close();
                }
                //Otherwise, couldnt find the file.
                else{
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                    exchange.getResponseBody().close();
                }
            }
            //It is not a get, so ignore it.
            else{
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }
        }
        //Internal Error
        catch(IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }

    /*
        The writeString method shows how to write a String to an OutputStream.
    */
    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
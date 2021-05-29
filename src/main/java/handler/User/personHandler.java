package handler.User;

import cereal.Cereal;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.GetPersonRequest;
import request.AuthRequest;
import result.AuthResult;
import service.GetPersonService;
import service.AuthService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

public class personHandler implements HttpHandler {
    ////GET
    @Override
    public void handle(HttpExchange exchange) {

        try {
            try {
                //If its a post request
                if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                    //AuthToken
                    Headers header = exchange.getResponseHeaders();

                    //If It has an Authtoken
                    if (header.containsKey("Authorization")) {
                        String token = header.getFirst("Authorization");

                        AuthRequest request = new AuthRequest(token);
                        AuthService service = new AuthService();
                        AuthResult result = service.getUser(request);

                        //Failed to authenticate
                        if(result == null){
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                            exchange.getResponseBody().close();
                        }

                        //Authentication successful
                        else{
                            //Get the URL parameters
                            String urlPath = exchange.getRequestURI().toString();
                            String[] segments = urlPath.split("/");
                            int urlParams = segments.length;

                            // Get the request body
                            InputStream reqBody = exchange.getRequestBody();
                            Cereal cereal = new Cereal();
                            String reqData = cereal.readString(reqBody);
                            Gson gson = new Gson();

                            //Get All Persons related to Current User.
                            if (urlParams == 1) {
                                //Get all who are related to said user.
                                GetAllPersonsR
                                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                                Writer respBody = new OutputStreamWriter(exchange.getResponseBody());
                                gson.toJson(result, respBody);
                                respBody.close();
                            }
                            //Get Specific User.
                            else if (urlParams == 2) {
                                String personID = segments[1];
                                GetPersonRequest request = gson.fromJson(reqData, GetPersonRequest.class);
                                GetPersonService person = new GetPersonService();

                                //Send Response: 200
                                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                                Writer respBody = new OutputStreamWriter(exchange.getResponseBody());
                                gson.toJson(result, respBody);
                                respBody.close();
                            }
                        }
                    }
                    //No AuthToken
                    else {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        exchange.getResponseBody().close();
                    }
                }
                //Faulty Request
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
            System.out.println("IOException in registerHandler: " + e);
            e.printStackTrace();
        }
    }
}

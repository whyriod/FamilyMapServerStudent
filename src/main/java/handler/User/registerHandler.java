package handler.User;

import cereal.Cereal;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.RegisterRequest;
import result.RegisterResult;
import service.RegisterService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

/***
 * Creates a new user for person.
 */
public class registerHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            try {
                //If its a post request
                if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                    // Get the request body
                    InputStream reqBody = exchange.getRequestBody();
                    Cereal cereal = new Cereal();
                    String reqData = cereal.readString(reqBody);

                    //Create the request and attempt the service
                    Gson gson = new Gson();
                    RegisterRequest request = gson.fromJson(reqData, RegisterRequest.class);
                    RegisterService register = new RegisterService();
                    RegisterResult result = register.registerUser(request);

                    //Register: Success
                    if(result.isSuccess()){
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    }
                    //Register: Failure
                    else{
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }

                    Writer respBody = new OutputStreamWriter(exchange.getResponseBody());
                    gson.toJson(result, respBody);
                    respBody.close();
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
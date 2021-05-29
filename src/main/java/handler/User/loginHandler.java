package handler.User;

import cereal.Cereal;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.LoginRequest;
import result.LoginResult;
import service.LoginService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

/***
 * Logs in already registered user
 */
public class loginHandler implements HttpHandler {

    /***
     * Attempts to log in already registered user.
     * @param exchange
     */
    @Override
    public void handle(HttpExchange exchange) {

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
                    LoginRequest request = gson.fromJson(reqData, LoginRequest.class);
                    LoginService login = new LoginService();
                    LoginResult result = login.loginUser(request);

                    //Login: Success
                    if (result.isSuccess()) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    }
                    //Login: Failure
                    else {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }
                    Writer respBody = new OutputStreamWriter(exchange.getResponseBody());
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
            System.out.println("IOException in loginHandler: " + e);
            e.printStackTrace();
        }
    }
}

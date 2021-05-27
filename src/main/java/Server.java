import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;
import handler.database.clearHandler;
import handler.database.fileHandler;
import handler.database.fillHandler;
import handler.database.loadHandler;
import handler.User.eventHandler;
import handler.User.loginHandler;
import handler.User.personHandler;
import handler.User.registerHandler;


public class Server {


    private static final int MAX_WAITING_CONNECTIONS = 12;
    private HttpServer server;


    private void run(String portNumber) {

        System.out.println("Initializing HTTP Server");

        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);

        System.out.println("Creating contexts");

        ////////// Database Handlers \\\\\\\\\\
        //Serve Index.html
        server.createContext("/", new fileHandler());
        //Delete all rows from databse
        server.createContext("/clear", new clearHandler());
        //Load provided User, Person, and Event data
        server.createContext("/load", new loadHandler());
        //Populate X generations for provided user. Default 4
        server.createContext("/fill", new fillHandler());


        ////////// User Handlers \\\\\\\\\\
//        server.createContext("/", new loginHandler());
//        server.createContext("/", new registerHandler());
//        server.createContext("/", new eventHandler());
//        server.createContext("/", new personHandler());


        System.out.println("Starting server");

        server.start();

        System.out.println("Server started");
    }

    public static void main(String[] args) {
        String portNumber = args[0];
        new Server().run(portNumber);
    }
}

import dao.DataAccessException;
import request.AuthRequest;
import request.LoginRequest;
import request.RegisterRequest;
import result.EventsResult;
import result.LoginResult;
import result.PersonsResult;
import result.RegisterResult;

public class FakeRequest {
    /***
     * Main argument. Setup server tables and then runs server.
     * @param args - Command Line args. The first is the port.
     */
    public static void main(String[] args) {

        ServerProxy proxy = new ServerProxy("localhost",8080);
        RegisterRequest r = new RegisterRequest("caleb","caleb","caleb","Caleb," +
                "Caleb","Caleb","M");
        RegisterResult rr = proxy.register(r);
        System.out.println(rr.getMessage());

        LoginRequest lr = new LoginRequest("caleb","caleb");
        LoginResult l = proxy.login(lr);
        System.out.println(l.getAuthtoken());

        AuthRequest ar = new AuthRequest(l.getAuthtoken());
        PersonsResult pr = proxy.persons(ar);
        System.out.println(pr.getData().size());

        EventsResult er = proxy.events(ar);
        System.out.println(er.getData().size());

    }
}

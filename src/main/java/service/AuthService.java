package service;

import dao.*;
import model.Authtoken;
import model.Person;
import model.User;
import request.AuthRequest;
import result.AuthResult;
import result.RegisterResult;
import java.sql.Connection;

public class AuthService {

    private Database db;
    private AuthTokenDAO aDAO;
    private EventDAO eDAO;
    private PersonDAO pDAO;
    private UserDAO uDAO;

    public void setUp() throws DataAccessException, ClassNotFoundException {
        final String driver = "org.sqlite.JDBC";
        Class.forName(driver);

        db = new Database();
        Connection c = db.getConnection();
        aDAO = new AuthTokenDAO(c);
        pDAO = new PersonDAO(c);
        uDAO = new UserDAO(c);
    }

    /***
     * Creates a new user account, generates 4 generations of ancestor data for the new user,
     * logs the user in, and returns an auth token.
     * @param r registerRequest Object
     * @return registerResult Object
     */
    public AuthResult getUser(AuthRequest r){

        AuthResult result = null;

        try{
            try{
                setUp();
                Authtoken token = aDAO.fetchToken(r.getAuthtoken());

                //They are authenticated
                if(token != null){
                    User user = uDAO.fetchUser(token.getusername());
                    Person person = pDAO.fetchPerson(user.getPersonID());

                    result = new AuthResult(user.getUsername(),person.getPersonID());
                }

                //Commit changes
                db.closeConnection(false);
            }
            //Clear Failed
            catch (ClassNotFoundException | DataAccessException e) {
                //Rollback changes
                db.closeConnection(false);
                e.printStackTrace();
            }
        }
        //Connection close failed
        catch(DataAccessException e){
            e.printStackTrace();
        }
        return result;
    }
}

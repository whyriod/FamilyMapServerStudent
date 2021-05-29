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
    private PersonDAO pDAO;
    private UserDAO uDAO;

    public void setUp() throws DataAccessException, ClassNotFoundException {

        db = new Database();
        Connection c = db.getConnection();
        aDAO = new AuthTokenDAO(c);
        pDAO = new PersonDAO(c);
        uDAO = new UserDAO(c);
    }

    /***
     * Authenticate User with their Authtoken.
     *
     * @param r - The authRequest containing the Authtoken.
     * @return - AuthResult Object.
     */
    public AuthResult authenticateUser(AuthRequest r){

        AuthResult result;

        try{
            try{
                setUp();
                Authtoken token = aDAO.fetchToken(r.getAuthtoken());

                //They are authenticated
                if(token != null){
                    User user = uDAO.fetchUser(token.getusername());
                    Person person = pDAO.fetchPerson(user.getPersonID());

                    result = new AuthResult(user.getUsername(),person.getPersonID(),
                            r.getAuthtoken(),true);
                }
                else{
                    result = new AuthResult("Unable to Authenticate",false);
                }
                //Commit changes
                db.closeConnection(false);
                return result;
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

        return null;
    }
}

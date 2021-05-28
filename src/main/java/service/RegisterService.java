package service;


import dao.*;
import model.Authtoken;
import model.Person;
import model.User;
import request.RegisterRequest;
import result.ClearResult;
import result.RegisterResult;

import java.sql.Connection;
import java.util.UUID;

public class RegisterService {

    //Notes
    //String str = exchange.getRequestURI.toString();
    //Get sTring array segments -  .split("/");
    //Check lengths, this lets you know whats in there.

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
    public RegisterResult registerUser(RegisterRequest r){

        RegisterResult register;

        try{
            //Attempt to register
            try{
                setUp();

                User exist = uDAO.fetchUser(r.getUsername(),r.getPassword());

                //New User
                if(exist == null){
                    User newUser = new User(UUID.randomUUID().toString(),r.getUsername(),
                                    r.getPassword(),r.getEmail(),r.getFirstName(),
                                    r.getLastName(),Character.toString(r.getGender()));
                    uDAO.insertUser(newUser);
                    Person newPerson = new Person(newUser.getPersonID(),newUser.getUsername(),
                                    newUser.getFirstName(), newUser.getLastName(), newUser.getGender(),
                                    null,null,null);
                    pDAO.insertPerson(newPerson);
                    Authtoken newToken = new Authtoken(UUID.randomUUID().toString(),newUser.getUsername());
                    aDAO.insertToken(newToken);

                    register = new RegisterResult(newToken.getauthtoken(),newUser.getUsername(),
                            newPerson.getPersonID(),true);
                }
                //Pre Existing User
                else{
                    register = new RegisterResult("Error: User already registered", false);
                }

                //Commit changes
                db.closeConnection(true);
            }
            //Clear Failed
            catch (ClassNotFoundException | DataAccessException e) {
                //Rollback changes
                db.closeConnection(false);
                register = new RegisterResult(("Error: " + e.getMessage()),false);
                e.printStackTrace();
            }
        }
        //Connection close failed
        catch(DataAccessException e){
            register = new RegisterResult(("Error: " + e.getMessage()), false);
            e.printStackTrace();
        }
        return register;
    }
}

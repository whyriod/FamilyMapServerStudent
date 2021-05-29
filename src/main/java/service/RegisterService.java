package service;


import dao.*;
import model.Authtoken;
import model.Person;
import model.User;
import request.RegisterRequest;
import result.RegisterResult;

import java.sql.Connection;
import java.util.UUID;

/***
 * Attempts to add User to User and Person table, and to
 * create a new authtoken. If it succeeds, they register.
 * If not, they fail.
 */
public class RegisterService {

    private Database db;
    private AuthTokenDAO aDAO;
    private EventDAO eDAO;
    private PersonDAO pDAO;
    private UserDAO uDAO;



    /***
     * Setups Database Connection, and initialize needed DAO's.
     *
     * @throws DataAccessException - Database Connection errors
     */
    public void setUp() throws DataAccessException, ClassNotFoundException {

        db = new Database();
        Connection c = db.getConnection();
        aDAO = new AuthTokenDAO(c);
        pDAO = new PersonDAO(c);
        uDAO = new UserDAO(c);
    }



    /***
     * Creates a new User, Person, and Authtoken row.
     * @param r registerRequest Object
     * @return registerResult Object
     */
    public RegisterResult registerUser(RegisterRequest r){

        RegisterResult register;

        try{
            try{
                //Setup DB connections and find user
                setUp();
                User exist = uDAO.fetchUser(r.getUsername(),r.getPassword());

                //New User
                if(exist == null){
                    //Create User, Person, and Authtoken objects
                    User newUser =
                            new User(UUID.randomUUID().toString(),r.getUsername(),
                                    r.getPassword(),r.getEmail(),r.getFirstName(),
                                    r.getLastName(),r.getGender());

                    Person newPerson =
                            new Person(newUser.getPersonID(),newUser.getUsername(),
                                    newUser.getFirstName(), newUser.getLastName(), newUser.getGender(),
                                    null,null,null);

                    Authtoken newToken =
                            new Authtoken(UUID.randomUUID().toString(),newUser.getUsername());

                    //Insert and commit changes.
                    uDAO.insertUser(newUser);
                    pDAO.insertPerson(newPerson);
                    aDAO.insertToken(newToken);

                    register = new RegisterResult(newToken.getauthtoken(),newUser.getUsername(),
                            newPerson.getPersonID(),true);
                }
                //!New User
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

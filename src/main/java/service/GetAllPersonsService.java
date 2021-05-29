package service;

import dao.*;
import model.Authtoken;
import model.Person;
import model.User;
import request.GetAllEventsRequest;
import request.GetPersonRequest;
import result.GetAllPersonsResult;
import result.RegisterResult;

import java.sql.Connection;
import java.util.UUID;

public class GetAllPersonsService {

    private Database db;
    private AuthTokenDAO aDAO;
    private EventDAO eDAO;
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
     * Description: Returns the single Person object with the specified ID.
     * @param r getAllEventsRequest
     * @return getAllEventsResult object
     */
    private GetAllPersonsService getAllEvents (GetPersonRequest r){
        return null;
//        GetAllPersonsResult result;
//
//        try{
//            try{
//                setUp();
//                Person[] persons;
//
//                //New User
//                if(exist == null){
//                    User newUser = new User(UUID.randomUUID().toString(),r.getUsername(),
//                            r.getPassword(),r.getEmail(),r.getFirstName(),
//                            r.getLastName(),r.getGender());
//                    uDAO.insertUser(newUser);
//
//                    Person newPerson = new Person(newUser.getPersonID(),newUser.getUsername(),
//                            newUser.getFirstName(), newUser.getLastName(), newUser.getGender(),
//                            null,null,null);
//
//                    pDAO.insertPerson(newPerson);
//                    Authtoken newToken = new Authtoken(UUID.randomUUID().toString(),newUser.getUsername());
//                    aDAO.insertToken(newToken);
//
//                    register = new RegisterResult(newToken.getauthtoken(),newUser.getUsername(),
//                            newPerson.getPersonID(),true);
//                }
//                //Pre Existing User
//                else{
//                    register = new RegisterResult("Error: User already registered", false);
//                }
//
//                //Commit changes
//                db.closeConnection(true);
//            }
//            //Clear Failed
//            catch (ClassNotFoundException | DataAccessException e) {
//                //Rollback changes
//                db.closeConnection(false);
//                register = new RegisterResult(("Error: " + e.getMessage()),false);
//                e.printStackTrace();
//            }
//        }
//        //Connection close failed
//        catch(DataAccessException e){
//            register = new RegisterResult(("Error: " + e.getMessage()), false);
//            e.printStackTrace();
//        }
//        return register;
//    }
    }
}

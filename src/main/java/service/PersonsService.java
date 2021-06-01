package service;

import dao.*;
import model.Person;
import request.PersonsEvent;
import result.PersonsResult;
import service.shared.Relatives;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Set;

/***
 * Uses a recursive function to get all persons who are related to said person.
 */
public class PersonsService {

    private Database db;
    private PersonDAO pDAO;



    /***
     * Sets up Database Connection, and initialize needed DAO's.
     *
     * @throws DataAccessException - Database Connection errors
     */
    private void setUp() throws DataAccessException, ClassNotFoundException {

        db = new Database();
        Connection c = db.getConnection();
        pDAO = new PersonDAO(c);
    }



    /***
     * Description: Returns the single Person object with the specified ID.
     * @param r getAllEventsRequest
     * @return getAllEventsResult object
     */
    public PersonsResult getAllPersons (PersonsEvent r){

        PersonsResult result;

        try{
            try{
                //Setup and get relatives
                setUp();
                Person person = pDAO.fetchPerson(r.getPersonID());
                Relatives get = new Relatives();
                Set<Person> relatives = get.getRelatives(person, pDAO);

                //Convert Set to Array
                ArrayList<Person> family = new ArrayList<Person>(relatives);
                result = new PersonsResult(family,true);

                //Close Connection
                db.closeConnection(true);
            }
            //Persons Failed
            catch (ClassNotFoundException | DataAccessException e) {
                //Rollback changes
                db.closeConnection(false);
                result = new PersonsResult(("Error: " + e.getMessage()),false);
                e.printStackTrace();
            }
        }
        //Connection close failed
        catch(DataAccessException e){
            result = new PersonsResult(("Error: " + e.getMessage()), false);
            e.printStackTrace();
        }
        return result;
    }
}

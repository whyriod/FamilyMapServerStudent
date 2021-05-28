package service;

import com.google.gson.Gson;
import dao.*;
import model.Event;
import model.Person;
import model.User;
import request.LoadRequest;
import result.ClearResult;
import result.LoadResult;

import java.sql.Connection;

public class LoadService {

    Database db;
    EventDAO eDAO;
    PersonDAO pDAO;
    UserDAO uDAO;


    public void setUp() throws DataAccessException, ClassNotFoundException {
        final String driver = "org.sqlite.JDBC";
        Class.forName(driver);

        db = new Database();
        Connection c = db.getConnection();
        eDAO = new EventDAO(c);
        pDAO = new PersonDAO(c);
        uDAO = new UserDAO(c);
    }


    /***
     * Description: Clears all data from the database (just like the /clear API), and then
     * loads the posted user, person, and event data into the database.
     * @param l loadRequest Object
     * @return loadResult Objest
     */
    public LoadResult loadDatabase(LoadRequest l){

        LoadResult load;

        try {
            //Try to load events, persons, and users into the database.
            try {
                setUp();
                Event[] events = l.getEvents();
                Person[] persons = l.getPersons();
                User[] users = l.getUsers();
                int eventCount = 0;
                int personCount = 0;
                int userCount = 0;

                //Load events
                for (int i = 0; i < events.length; i++) {
                    eDAO.insertEvent(events[i]);
                    eventCount++;
                }

                //Load persons
                for (int i = 0; i < persons.length; i++) {
                    pDAO.insertPerson(persons[i]);
                    personCount++;
                }

                //Load users
                for (int i = 0; i < users.length; i++) {
                    uDAO.insertUser(users[i]);
                    userCount++;
                }
                //Commit changes
                db.closeConnection(true);

                //Create result object
                load = new LoadResult("Successfully added " +
                        userCount + " users, " +
                        personCount + " persons, and " +
                        eventCount + " events to the database.", true);
            }

            //Internal Error
            catch (DataAccessException | ClassNotFoundException e) {
                //Rollback
                db.closeConnection(false);
                load = new LoadResult(("Error: " + e.getMessage()), false);
                e.printStackTrace();
            }
        }

        //Connection close failed
        catch(DataAccessException e){
            load = new LoadResult(("Error: " + e.getMessage()), false);
            e.printStackTrace();
        }

        return load;
    }
}

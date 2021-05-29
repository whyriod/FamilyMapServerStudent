package service;

import dao.*;
import result.ClearResult;
import java.sql.Connection;

/***
 * Creates a database connection and DAO objects for each table.
 * Clears each table and report the result.
 */
public class ClearService {

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
    public void setUp() throws DataAccessException {

        db = new Database();
        Connection c = db.getConnection();

        aDAO = new AuthTokenDAO(c);
        eDAO = new EventDAO(c);
        pDAO = new PersonDAO(c);
        uDAO = new UserDAO(c);
    }



    /***
     * Description: Deletes ALL data from the database: user, authTokens,
     * persons, and events.
     *
     * @return clearResult object.
     */
    public ClearResult clearDatabase() {

        ClearResult clear;

        try{
            //Attempt to Clear
            try{
                //Setup and clear tables
                setUp();
                aDAO.clear();
                eDAO.clear();
                pDAO.clear();
                uDAO.clear();

                //Commit changes
                db.closeConnection(true);
                clear = new ClearResult("Clear succeeded.",true);
            }
            //Clear Failed
            catch (DataAccessException e) {
                //Rollback changes
                db.closeConnection(false);
                clear = new ClearResult(("Error: " + e.getMessage()),false);
                e.printStackTrace();
            }
        }
        //Connection close failed
        catch(DataAccessException e){
            clear = new ClearResult(("Error: " + e.getMessage()), false);
            e.printStackTrace();
        }
        return clear;
    }
}

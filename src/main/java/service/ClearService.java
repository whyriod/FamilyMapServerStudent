package service;

import dao.*;
import result.ClearResult;

import javax.xml.crypto.Data;
import java.sql.Connection;

public class ClearService {

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
        eDAO = new EventDAO(c);
        pDAO = new PersonDAO(c);
        uDAO = new UserDAO(c);
    }

    /***
     * Description: Deletes ALL data from the database, including user accounts, auth tokens,
     * person data, and event data
     * @return clearResult object
     */
    public ClearResult clearDatabase() {

        ClearResult clear;

        try{
            //Attempt to Clear
            try{
                //Setup DB connections and clear tables
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
            catch (ClassNotFoundException | DataAccessException e) {
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

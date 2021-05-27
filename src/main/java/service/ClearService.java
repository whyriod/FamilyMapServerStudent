package service;

import dao.*;
import result.ClearResult;

import javax.xml.crypto.Data;
import java.sql.Connection;

public class ClearService {

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
            try{
                setUp();
                aDAO.clear();
                eDAO.clear();
                pDAO.clear();
                uDAO.clear();
                db.closeConnection(true);
                clear = new ClearResult("Clear succeeded. Changes committed",true);
            }
            catch (ClassNotFoundException | DataAccessException e) {
                e.printStackTrace();
                db.closeConnection(false);
                clear = new ClearResult("Clear Failed. Changes rolled back",false);
            }
        }
        catch(DataAccessException e){
            clear = new ClearResult("Clear Failed. Unable to close DB connection", false);
        }
        return clear;
    }
}

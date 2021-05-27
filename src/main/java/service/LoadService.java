package service;

import com.google.gson.Gson;
import dao.*;
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
        LoadResult load = new LoadResult("Yay",true);
//        try{
//            try{
//                System.out.println("YAYA");
//                db.closeConnection(true);
//                load = new LoadResult("Load succeeded. Changes committed",true);
//            }
//            catch (DataAccessException e) {
//                e.printStackTrace();
//                db.closeConnection(false);
//                load = new LoadResult("Load Failed. Changes rolled back",false);
//            }
//        }
//        catch(DataAccessException e){
//            load = new LoadResult("Load Failed. Unable to close DB connection", false);
//        }
        return load;
    }
}

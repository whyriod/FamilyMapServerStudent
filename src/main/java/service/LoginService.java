package service;

import dao.*;
import model.Authtoken;
import model.User;
import request.LoginRequest;
import result.ClearResult;
import result.LoginResult;

import java.sql.Connection;
import java.util.UUID;

public class LoginService {

    private Database db;
    private AuthTokenDAO aDAO;
    private UserDAO uDAO;

    public void setUp() throws DataAccessException, ClassNotFoundException {
        final String driver = "org.sqlite.JDBC";
        Class.forName(driver);

        db = new Database();
        Connection c = db.getConnection();
        aDAO = new AuthTokenDAO(c);
        uDAO = new UserDAO(c);
    }

    /***
     * Description: Logs in the user and returns an auth token.
     * @param r loginRequest
     * @return loginResult object
     */
    public LoginResult loginUser(LoginRequest r){

        LoginResult login;

        try{
            //Attempt to login
            try{
                //Setup DB connections and find user, and then authtoken.
                setUp();
                User user = uDAO.fetchUser(r.getUsername(),r.getPassword());

                if(user != null){
                    //Create new Token
                    Authtoken newToken = new Authtoken(UUID.randomUUID().toString(),r.getUsername());
                    aDAO.insertToken(newToken);

                    //Return token
                    login = new LoginResult(newToken.getauthtoken(),newToken.getusername(),
                            user.getPersonID(),true);
                }
                else{
                    login = new LoginResult("Error: Invalid username or password",false);
                }
                //Commit changes
                db.closeConnection(true);
            }

            //Login Failed
            catch (ClassNotFoundException | DataAccessException e) {
                //Rollback changes
                db.closeConnection(false);
                login = new LoginResult(("Error: " + e.getMessage()),false);
                e.printStackTrace();
            }
        }
        //Connection close failed
        catch(DataAccessException e){
            login = new LoginResult(("Error: " + e.getMessage()),false);
            e.printStackTrace();
        }
        return login;
    }
}

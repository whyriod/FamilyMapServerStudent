package dao;

import model.Authtoken;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/***
 * Performs authtoken operations on the authtoken table
 */
public class AuthTokenDAO {

    private final Connection c;

    public AuthTokenDAO(Connection c)
    {
        this.c = c;
    }

    /***
     * Authenticate the authtoken.
     * @param authToken
     */
    private Authtoken fetch(Authtoken authToken){
        return null;
    }

    /***
     * Clears all authtokens from the database
     * @return
     */
    public void clear() throws DataAccessException {
        try (Statement stmt = c.createStatement()){
            String sql = "DELETE FROM Authtoken";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing User Table");
        }
    }
}

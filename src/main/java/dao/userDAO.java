package dao;

import model.Person;
import model.User;

import java.sql.*;

public class userDAO {

    private final Connection c;

    public userDAO(Connection c)
    {
        this.c = c;
    }

    /***
     * Create a new user.
     * @param user
     */
    public void insert(User user) throws DataAccessException{

        String sql = "INSERT INTO User (PersonID, Username, Password, Email, " +
                "FirstName, LastName, Gender) VALUES(?,?,?,?,?,?,?);";

        try (PreparedStatement stmt = c.prepareStatement(sql)) {

            stmt.setString(1, user.getPersonID());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getFirstName());
            stmt.setString(6, user.getLastName());
            stmt.setString(7, user.getGender());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the User database");
        }
    }

    /***
     * Take a username and password, and login the user
     * @param username
     * @param password
     */
    public User fetch(String username, String password) throws DataAccessException{
        User user;
        ResultSet ur = null;
        String sql = "SELECT * FROM User WHERE Username = ? AND Password = ?;";
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ur = stmt.executeQuery();
            if (ur.next()) {
                user = new User(ur.getString("PersonID"), ur.getString("Username"),
                        ur.getString("Password"), ur.getString("Email"),
                        ur.getString("FirstName"), ur.getString("LastName"),
                        ur.getString("Gender"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding User");
        } finally {
            if(ur != null) {
                try {
                    ur.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    /***
     * Delete all users from the db
     */
    public void clear() throws DataAccessException{
        try (Statement stmt = c.createStatement()){
            String sql = "DELETE FROM User";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing User Table");
        }
    }
}

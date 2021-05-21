package dao;

import model.Person;
import java.sql.*;

public class personDAO {

    private final Connection c;

    public personDAO(Connection c)
    {
        this.c = c;
    }

    /***
     * Create a Person.
     */
    public void insert(Person person) throws DataAccessException {

        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Person (PersonID, AssociatedUsername, FirstName, LastName, " +
                "Gender, FatherID, MotherID, SpouseID) VALUES(?,?,?,?,?,?,?,?);";

        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the Person database");
        }
    }

    /***
     * Find a Person. Return a person object.
     * @param personID
     */
    public Person fetch(String personID) throws DataAccessException {
        Person person;
        ResultSet pr = null;
        String sql = "SELECT * FROM Person WHERE PersonID = ?;";
            try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, personID);
            pr = stmt.executeQuery();
            if (pr.next()) {
                person = new Person(pr.getString("PersonID"), pr.getString("AssociatedUsername"),
                        pr.getString("FirstName"), pr.getString("LastName"),
                        pr.getString("Gender"), pr.getString("FatherID"),
                        pr.getString("MotherID"), pr.getString("SpouseID"));
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding person");
        } finally {
            if(pr != null) {
                try {
                    pr.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
            return null;
    }


//    /***
//     * Find all related persons
//     * @param personID
//     * @return - person[] of persons related to the personID
//     */
//    private person[] fetchRelatedPersons(String personID){
//        return null;
//    }

    /***
     * Delete all Persons in the database
     * @return whether the operation finished.
     */
    public void clear() throws DataAccessException{
        try (Statement stmt = c.createStatement()){
            String sql = "DELETE FROM Person";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing Person Table");
        }
    }
}

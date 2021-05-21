package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/***
 * Credit where Credit is due: This is from the Example Dao class:
 * DAOJunit5Example
 */

public class Database {
    private Connection c;

    //Whenever we want to make a change to our database we will have to open a connection and use
    //Statements created by that connection to initiate transactions
    public Connection openConnection() throws DataAccessException {
        try{

            //The Structure for this Connection is driver:language:path
            //The path assumes you start in the root of your project unless given a non-relative path
            final String CONNECTION_URL = "jdbc:sqlite:FamilyMap.sqlite";
            c = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            c.setAutoCommit(false);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to open connection to database");
        }
        return c;
    }

    public Connection getConnection() throws DataAccessException {
        if(c == null) {
            return openConnection();
        } else {
            return c;
        }
    }

    //When we are done manipulating the database it is important to close the connection. This will
    //End the transaction and allow us to either commit our changes to the database or rollback any
    //changes that were made before we encountered a potential error.

    //IMPORTANT: IF YOU FAIL TO CLOSE A CONNECTION AND TRY TO REOPEN THE DATABASE THIS WILL CAUSE THE
    //DATABASE TO LOCK. YOUR CODE MUST ALWAYS INCLUDE A CLOSURE OF THE DATABASE NO MATTER WHAT ERRORS
    //OR PROBLEMS YOU ENCOUNTER
    public void closeConnection(boolean commit) throws DataAccessException {
        try {
            if (commit) {
                //This will commit the changes to the database
                c.commit();
            } else {
                //If we find out something went wrong, pass a false into closeConnection and this
                //will rollback any changes we made during this connection
                c.rollback();
            }

            c.close();
            c = null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to close database connection");
        }
    }

    public void clearTables() throws DataAccessException {
        try (Statement stmt = c.createStatement()) {
            String sqlA = "Truncate Table Authtoken";
            String sqlE = "Truncate Table Event";
            String sqlP = "Truncate Table Person";
            String sqlU = "Truncate Table User";

            stmt.executeUpdate(sqlA);
            stmt.executeUpdate(sqlE);
            stmt.executeUpdate(sqlP);
            stmt.executeUpdate(sqlU);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while truncating tables");
        }
    }

    public void dropTables() throws DataAccessException{
        try (Statement stmt = c.createStatement()){
            String sqlA = "Drop Table Authtoken";
            String sqlE = "Drop Table Event";
            String sqlP = "Drop Table Person";
            String sqlU = "Drop Table User";

            stmt.executeUpdate(sqlA);
            stmt.executeUpdate(sqlE);
            stmt.executeUpdate(sqlP);
            stmt.executeUpdate(sqlU);
        }
        catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while dropping tables");
        }
    }

    public void createTables() throws DataAccessException{

        try (Statement stmt = c.createStatement()){
            String sqlA =
            "Create Table IF NOT EXISTS Authtoken(\n" +
            "AuthToken              varchar(20) UNIQUE NOT NULL,\n" +
            "AssociatedUsername     varchar(20) NOT NULL);";

            stmt.executeUpdate(sqlA);
        }
        catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while creating AuthToken Table");
        }

        try (Statement stmt = c.createStatement()){
            String sqlE =
            "Create Table IF NOT EXISTS Event(\n" +
            "EventID                varChar(20) PRIMARY KEY NOT NULL,\n" +
            "EventType              varChar(20) check(EventType in " +
                    "('birth', 'baptism', 'christening', 'marriage', 'death')),\n" +
            "PersonID               varchar(20) NOT NULL Check (PersonID != \"\"),\n" +
            "AssociatedUsername     varchar(20) NOT NULL,\n" +
            "Year                   Integer NOT NULL,\n" +
            "Country                varchar(50) NOT NULL,\n" +
            "City                   varchar(50) NOT NULL,\n" +
            "Latitude               float NOT NULL,\n" +
            "Longitude              float NOT NULL);";

            stmt.executeUpdate(sqlE);
        }
        catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while creating Event Table");
        }

        try (Statement stmt = c.createStatement()){
            String sqlP =
            "Create Table IF NOT EXISTS Person(\n" +
            "PersonID             varChar(20) PRIMARY KEY check (PersonID != \"\"),\n" +
            "AssociatedUsername   varchar(20) NOT NULL UNIQUE DEFAULT \"\",\n" +
            "FirstName            varchar(40) NOT NULL,\n" +
            "LastName             varChar(40) NOT NULL,\n" +
            "Gender               varchar(1) NOT NULL Check (Gender in ('M','F')),\n" +
            "FatherID             varChar(20),\n" +
            "MotherID             varChar(20),\n" +
            "SpouseID             varChar(20));";

            stmt.executeUpdate(sqlP);
        }
        catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while creating Person Table");
        }

        try (Statement stmt = c.createStatement()){
            String sqlA =
            "Create TABLE IF NOT EXISTS User (\n"+
            "PersonID           varChar(20) UNIQUE NOT NULL check (PersonID != \"\"),\n"+
            "Username           varchar(20) PRIMARY KEY,\n"+
            "Password           varchar(20) NOT NULL,\n"+
            "Email              varchar(40) NOT NULL,\n"+
            "FirstName          varchar(40) NOT NULL,\n"+
            "LastName           varchar(40) NOT NULL,\n"+
            "Gender             varchar(1) NOT NULL Check (Gender in ('M','F')));";

            stmt.executeUpdate(sqlA);
        }
        catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while creating User Table");
        }
    }
}

package dao;

import model.Event;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EventDAO {

    private final Connection c;

    public EventDAO(Connection c)
    {
        this.c = c;
    }

    /***
     * Get single event from specific eventID
     * @param eventID
     * @return
     */
    private Event fetchEvent(String eventID){
        return null;
    }

    /***
     * Get all events for specific personID
     * @param personID
     * @return event[] of all events of personID type
     */
    private Event[] fetchAllEvents(String personID){
        return null;
    }

    /***
     * Create an event in the database, and return the created event object
     * @param eventID
     * @return
     */
    private Event insertEvent(String eventID){
        return null;
    }

    /***
     * Delete all events in the database
     * @return
     */
    public void clear() throws DataAccessException {
        try (Statement stmt = c.createStatement()){
            String sql = "DELETE FROM Event";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing Event Table");
        }
    }
}

package request;

/***
 * Creates a request to get a specific event by its eventID.
 * EventID found in the URL.
 */
public class GetEventRequest {

    private String eventID;

    /***
     * Constructor
     * @param eventID - ID of the requested event
     */
    public GetEventRequest(String eventID) {
        this.eventID = eventID;
    };

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
}

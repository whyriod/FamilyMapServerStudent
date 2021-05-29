package request;

/***
 * Creates a request to get all events related to a personID based on the
 * provided AuthToken. This is found in the reqBody.
 */
public class GetAllEventsRequest {

    private String personID;



    /***
     * Constructor.
     * 
     * @param personID - The personID passed in.
     */
    public GetAllEventsRequest(String personID) {
        this.personID = personID;
    }

    public String getpersonID() {
        return personID;
    }

    public void setpersonID(String personID) {
        this.personID = personID;
    }
}

package request;

/***
 * Creates a request to get all events related to a username based on the
 * provided AuthToken. This is found in the reqBody.
 */
public class GetAllEventsRequest {

    private String username;

    /***
     * Constructor
     * @param username - The username passed in
     */
    public GetAllEventsRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

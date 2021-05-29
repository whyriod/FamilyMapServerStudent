package result;

/***
 * Creates a request object for validating a username based on authtoken.
 * This is found in the reqBody.
 */
public class AuthResult {

    private String username;
    private String personID;

    /***
     * Constructor.
     * @param username - Username of person
     * @param personID - ID of person
     */
    public AuthResult(String username, String personID) {
        this.username = username;
        this.personID = personID;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}

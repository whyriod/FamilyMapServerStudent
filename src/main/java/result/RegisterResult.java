package result;

/***
 * Creates a RegisterResult object to return an Authtoken, username, and personID
 * based on db operation.
 */
public class RegisterResult {

//    Success Response Body:
//    {
//       "authtoken": "cf7a368f",	// Non-empty auth token string
//       "username": "susan",		// Username passed in with request
//       "personID": "39f9fe46",		// Non-empty string containing the Person ID of the user’s generated Person object
//       "success": true		// Boolean identifier
//    }
//    Error Response Body:
//    {
//       “message”: “Error: [Description of the error]”
//       “success”:false		// Boolean identifier
//    }


    private String authtoken;
    private String username;
    private String personID;
    private boolean success;
    private String message;

    /***
     * Success Constructor
     * @param authtoken - Authtoken of new user
     * @param username - Username of new user.
     * @param personID - PersonID of new user
     * @param success - Boolean of Success
     */
    public RegisterResult(String authtoken, String username, String personID, boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
    }

    /***
     * Error Constructor
     * @param message - “Error: [Description of the error]”
     * @param success - Boolean of success
     */
    public RegisterResult(String message,boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package request;

/***
 * Creates a request to get a specific person based on the personID.
 * PersonID found in the URL.
 */
public class GetPersonRequest {

    private String personID;

    /***
     * Constructor
     * @param personID - PersonID of the requested person
     */
    public GetPersonRequest(String personID) {
        this.personID = personID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}

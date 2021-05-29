package request;

/***
 * Creates a request to get a all persons based on the personID.
 * PersonID found through authentication
 */
public class GetAllPersonsRequest {

    private String personID;



    /***
     * Constructor.
     *
     * @param personID - PersonID of the requested person.
     */
    public GetAllPersonsRequest(String personID) {
        this.personID = personID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}

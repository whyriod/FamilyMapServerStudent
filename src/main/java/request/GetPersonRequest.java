package request;

public class GetPersonRequest {

    //personId from URL

    private String personID;

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

package result;

/***
 * Creates a GetPersonResult object to return a specific person
 * based on db operation.
 */
public class GetPersonResult {

//    Success Response Body:
//    {
//       "associatedUsername": "susan",	// Name of user account this person belongs to
//       "personID": "7255e93e",	// Person’s unique ID
//       "firstName": "Stuart",		// Person’s first name
//       "lastName": "Klocke",		// Person’s last name
//       "gender": "m",			// Person’s gender (“m” or “f”)
//       “fatherID”: “7255e93e”		// ID of person’s father [OPTIONAL, can be missing]
//       “motherID”: “d3gz214j”	// ID of person’s mother [OPTIONAL, can be missing]
//       "spouseID":"f42126c8"	// ID of person’s spouse [OPTIONAL, can be missing]
//       “success”:true		// Boolean identifier
//    }
//    Error Response Body:
//    {
//	     “message”: “Error: [Description of the error]”
//       “success”:false		// Boolean identifier
//    }

    private String personID;
    private String associatedUsername;
    private String firstName;
    private String lastName;
    private char gender;
    private String fatherID;
    private String motherID;
    private String spouseID;
    private boolean success;
    private String message;

    /***
     * Success Constructor
     * @param personID - PersonID of person
     * @param associatedUsername - Username of person
     * @param firstName - Firstname of person
     * @param lastName - Lastname of person
     * @param gender - Gender of person
     * @param fatherID - ID of father
     * @param motherID - ID of mother
     * @param spouseID - ID of spouse
     * @param success - Boolean of success
     */
    public GetPersonResult(String personID, String associatedUsername, String firstName, String lastName, char gender,
                           String fatherID, String motherID, String spouseID, boolean success) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
        this.success = success;
    }

    /***
     * Error Constructor
     * @param message - “Error: [Description of the error]”
     * @param success - Boolean of success
     */
    public GetPersonResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
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


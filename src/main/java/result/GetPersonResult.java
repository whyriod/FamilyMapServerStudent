package result;

public class GetPersonResult {

//    Success Response Body:
//    {
//        "associatedUsername": "susan",	// Name of user account this person belongs to
//            "personID": "7255e93e",	// Person’s unique ID
//            "firstName": "Stuart",		// Person’s first name
//            "lastName": "Klocke",		// Person’s last name
//            "gender": "m",			// Person’s gender (“m” or “f”)
//            “fatherID”: “7255e93e”		// ID of person’s father [OPTIONAL, can be missing]
//            “motherID”: “d3gz214j”	// ID of person’s mother [OPTIONAL, can be missing]
//            "spouseID":"f42126c8"	// ID of person’s spouse [OPTIONAL, can be missing]
//             “success”:true		// Boolean identifier
//    }
//    Error Response Body:
//    {
//	    “message”: “Error: [Description of the error]”
//        “success”:false		// Boolean identifier
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
     */
    public GetPersonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}


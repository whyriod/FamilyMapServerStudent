package result;

public class getPersonResult {
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
    public getPersonResult(String personID, String associatedUsername, String firstName, String lastName, char gender,
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
    public getPersonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}


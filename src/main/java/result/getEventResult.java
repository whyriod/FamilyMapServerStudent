package result;

public class getEventResult {
    private String eventID;
    private String eventType;
    private String personID;
    private String associatedUsername;
    private int year;
    private String country;
    private String city;
    private float Latitude;
    private float Longitude;
    private String message;
    private boolean success;

    /***
     * Success Constructor
     */
    public getEventResult(String eventID, String eventType, String personID, String associatedUsername, int year,
                          String country, String city, float latitude, float longitude, boolean success) {
        this.eventID = eventID;
        this.eventType = eventType;
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.year = year;
        this.country = country;
        this.city = city;
        Latitude = latitude;
        Longitude = longitude;
        this.success = success;
    }

    /***
     * Error Constructor
     */
    public getEventResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}

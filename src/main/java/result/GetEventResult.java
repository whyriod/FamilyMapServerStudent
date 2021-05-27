package result;

public class GetEventResult {

//    Success Response Body:
//    {
//        "associatedUsername": "susan"  // Username of user account this event belongs to (non-empty string)
//        "eventID": "251837d7",	// Event’s unique ID (non-empty string)
//        "personID": "7255e93e",	// ID of the person this event belongs to (non-empty string)
//        "latitude": 65.6833,		// Latitude of the event’s location (number)
//        "longitude": -17.9,		// Longitude of the event’s location (number)
//        "country": "Iceland",		// Name of country where event occurred (non-empty string)
//        "city": "Akureyri",		// Name of city where event occurred (non-empty string)
//        "eventType": "birth",		// Type of event (“birth”, “baptism”, etc.) (non-empty string)
//        "year": 1912,			// Year the event occurred (integer)
//        “success”:true		// Boolean identifier
//    }
//    Error Response Body:
//    {
//	    “message”: “Error: [Description of the error]”
//        “success”:false		// Boolean identifier
//    }

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
    public GetEventResult(String eventID, String eventType, String personID, String associatedUsername, int year,
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
    public GetEventResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}

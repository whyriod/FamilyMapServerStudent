package result;

/***
 * Creates a GetEventResult object to return a specific event
 * based on db operation.
 */
public class GetEventResult {

//    Success Response Body:
//    {
//       "associatedUsername": "susan"  // Username of user account this event belongs to (non-empty string)
//       "eventID": "251837d7",	// Event’s unique ID (non-empty string)
//       "personID": "7255e93e",	// ID of the person this event belongs to (non-empty string)
//       "latitude": 65.6833,		// Latitude of the event’s location (number)
//       "longitude": -17.9,		// Longitude of the event’s location (number)
//       "country": "Iceland",		// Name of country where event occurred (non-empty string)
//       "city": "Akureyri",		// Name of city where event occurred (non-empty string)
//       "eventType": "birth",		// Type of event (“birth”, “baptism”, etc.) (non-empty string)
//       "year": 1912,			// Year the event occurred (integer)
//       “success”:true		// Boolean identifier
//    }
//    Error Response Body:
//    {
//	     “message”: “Error: [Description of the error]”
//       “success”:false		// Boolean identifier
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
     * @param eventID - Id of event.
     * @param eventType - EventType of event
     * @param personID - PersonID of event
     * @param associatedUsername - Username of event
     * @param year - Year event occurred
     * @param country - Country of event
     * @param city - City of event
     * @param latitude - Coordinates of event
     * @param longitude - Coordinates of event
     * @param success - Boolean of success
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
     * @param message - “Error: [Description of the error]”
     * @param success - Boolean of success
     */
    public GetEventResult(String message,boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getLatitude() {
        return Latitude;
    }

    public void setLatitude(float latitude) {
        Latitude = latitude;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setLongitude(float longitude) {
        Longitude = longitude;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

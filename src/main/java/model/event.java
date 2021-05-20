package model;

public class event {
    private String eventID;
    private String eventType;
    private String personID;
    private String associatedUsername;
    private int year;
    private String country;
    private String city;
    private float latitude;
    private float longitude;

    public event() {
    }

    public event(String eventID, String eventType, String personID, String associatedUsername,
                 int year, String country, String city, float latitude, float longitude) {
        this.eventID = eventID;
        this.eventType = eventType;
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.year = year;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String geteventID() {
        return eventID;
    }

    public void seteventID(String eventID) {
        eventID = eventID;
    }

    public String geteventType() {
        return eventType;
    }

    public void seteventType(String eventType) {
        eventType = eventType;
    }

    public String getpersonID() {
        return personID;
    }

    public void setpersonID(String personID) {
        personID = personID;
    }

    public String getassociatedUsername() {
        return associatedUsername;
    }

    public void setassociatedUsername(String associatedUsername) {
        associatedUsername = associatedUsername;
    }

    public int getyear() {
        return year;
    }

    public void setyear(int year) {
        year = year;
    }

    public String getcountry() {
        return country;
    }

    public void setcountry(String country) {
        country = country;
    }

    public String getcity() {
        return city;
    }

    public void setcity(String city) {
        city = city;
    }

    public float getlatitude() {
        return latitude;
    }

    public void setlatitude(float latitude) {
        latitude = latitude;
    }

    public float getlongitude() {
        return longitude;
    }

    public void setlongitude(float longitude) {
        longitude = longitude;
    }
}

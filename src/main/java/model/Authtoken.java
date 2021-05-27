package model;

public class Authtoken {
    private String username;
    private String authtoken;

    public Authtoken() {
    }

    public Authtoken(String associatedUsername, String authtoken) {
        this.username = associatedUsername;
        this.authtoken = authtoken;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getauthtoken() {
        return authtoken;
    }

    public void setauthtoken(String authtoken) {
        this.authtoken = authtoken;
    }
}

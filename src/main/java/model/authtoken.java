package model;

public class authtoken {
    private String associatedUsername;
    private String authtoken;

    public authtoken() {
    }

    public authtoken(String associatedUsername, String authtoken) {
        this.associatedUsername = associatedUsername;
        this.authtoken = authtoken;
    }

    public String getassociatedUsername() {
        return associatedUsername;
    }

    public void setassociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getauthtoken() {
        return authtoken;
    }

    public void setauthtoken(String authtoken) {
        this.authtoken = authtoken;
    }
}

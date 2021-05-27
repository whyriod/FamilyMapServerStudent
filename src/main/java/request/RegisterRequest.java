package request;

public class RegisterRequest {

//    {
//        "username": "susan",		// Non-empty string
//        "password": "mysecret",	// Non-empty string
//        "email": "susan@gmail.com",	// Non-empty string
//        "firstName": "Susan",		// Non-empty string
//        "lastName": "Ellis",		// Non-empty string
//        "gender": "f"			// “f” or “m”
//    }

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private char gender;

    public RegisterRequest(String username, String password, String email, String firstName, String lastName, char gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}

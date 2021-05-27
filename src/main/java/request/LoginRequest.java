package request;

public class LoginRequest {

//    Request Body:
//    {
//        "username": "susan",		// Non-empty string
//        "password": "mysecret"	// Non-empty string
//    }

    private String username;
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
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
}
